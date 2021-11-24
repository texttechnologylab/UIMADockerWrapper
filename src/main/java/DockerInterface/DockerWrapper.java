package DockerInterface;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

import DockerInterface.modules.DockerWrapperModuleErrorImplementation;
import DockerInterface.modules.IDockerWrapperModule;
import DockerInterface.remote.InContainerEngineProcessor;
import DockerInterface.util.DockerWrapperUtil;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.uima.UIMAException;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.*;
import org.apache.uima.analysis_engine.metadata.SofaMapping;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.SerialFormat;
import org.apache.uima.cas.admin.CASMgr;
import org.apache.uima.cas.impl.XCASSerializer;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.*;
import org.hucompute.reproannotationnlp.ReproducibleAnnotationHash;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

/**
 * The basic implementation of the DockerWrapper for the UIMA AnalysisEngineDescription, this annotator builds on request
 * a container which executes the wrapped analysis engine inside of that container.
 */
public class DockerWrapper extends JCasAnnotator_ImplBase {
    /**
     * The processor which is either null or the wrapped engine. Will be null when the remote container is used as analysis
     * engine implementation
     */
    private InContainerEngineProcessor _processor;

    /**
     * This is the configuration of the wrapped docker environment
     */
    private DockerWrappedEnvironment _configuration;

    /**
     * This is the interface to the docker daemon if it is needed. Will be null if run_in_container false is.
     */
    private DockerGeneralInterface _docker_interface;

    /**
     * The container id of the started container.
     */
    private String _containerid;
    /**
     * The url to access the REST container.
     */
    private String _containerurl;

    /**
     * A list of used modules.
     */
    private List<IDockerWrapperModule> _modules;

    public static final String PARAM_CFG = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_CFG";
    @ConfigurationParameter(name = PARAM_CFG, mandatory = true)
    private String _cfg_string;

    public static final String PARAM_CONFIRM_INTEGRITY = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_CONFIRM_INTEGRITY";
    @ConfigurationParameter(name = PARAM_CONFIRM_INTEGRITY, mandatory = true, defaultValue = "true")
    private boolean _confirm_integrity;

    public static final String PARAM_RUN_IN_CONTAINER = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_RUN_IN_CONTAINER";
    @ConfigurationParameter(name = PARAM_RUN_IN_CONTAINER, mandatory = true, defaultValue = "true")
    private boolean _run_in_container;


    public static final String PARAM_CONTAINER_NAME = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_CONTAINER_NAME";
    @ConfigurationParameter(name = PARAM_CONTAINER_NAME, mandatory = true, defaultValue = "")
    private String _container_name;

    public static final String PARAM_USE_GPU = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_USE_GPU";
    @ConfigurationParameter(name = PARAM_USE_GPU, mandatory = true, defaultValue = "true")
    private boolean _use_gpu;

    public static final String PARAM_AUTOREMOVE = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_AUTOREMOVE";
    @ConfigurationParameter(name = PARAM_AUTOREMOVE, mandatory = true, defaultValue = "true")
    private boolean _autoremove;

    public static final String PARAM_EXPORT_NAME = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_EXPORT_NAME";
    @ConfigurationParameter(name = PARAM_EXPORT_NAME, mandatory = true, defaultValue = "true")
    private String _export_name;

    public static final String PARAM_REUSE_CONTAINER = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_REUSE_CONTAINER";
    @ConfigurationParameter(name = PARAM_REUSE_CONTAINER, mandatory = true, defaultValue = "false")
    private boolean _reuse_container;

    public static final String PARAM_ADDITIONAL_MODULES = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_ADIITIONAL_MODULES";
    @ConfigurationParameter(name=PARAM_ADDITIONAL_MODULES, mandatory = false)
    private List<String> _additional_modules;

    public static final String PARAM_ADDITIONAL_MODULES_CONFIGURATION = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_ADIITIONAL_MODULES_CONFIG";
    @ConfigurationParameter(name=PARAM_ADDITIONAL_MODULES_CONFIGURATION, mandatory = false)
    private List<String> _additional_modules_config;

    public static final String PARAM_CONTAINER_TIMEOUT = "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_CONTAINER_TIMEOUT";
    @ConfigurationParameter(name=PARAM_CONTAINER_TIMEOUT, mandatory = false, defaultValue = "20")
    private int _container_initialise_timeout;

    public static final String PARAM_CONTAINER_ID= "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_CONTAINER_ID";
    @ConfigurationParameter(name=PARAM_CONTAINER_ID, mandatory = false, defaultValue = "")
    private String _container_unsafe_id;

    public static final String PARAM_MAP_DOECKER_DAEMON= "TEXTTECHNOLOGYLAB_GOETHE_UNIVERSITY_MAP_DAEMON";
    @ConfigurationParameter(name=PARAM_MAP_DOECKER_DAEMON, mandatory = false, defaultValue = "false")
    private boolean _unsafe_map_daemon;

    public static final String VIEW_NAME_CONFIRM_INTEGRITY = "_view_texttechnologylab_cas_integrity";

    /**
     * Uses the existing docker container and binds the container to the DockerWrapper
     * @param container_config The configuration of the container which only needs to set the export name, the container id and the module configurations
     * @return An AnalysisEngineDescription which will send any CAS document to the underlying docker container
     * @throws ResourceInitializationException
     * @throws IOException
     * @throws SAXException
     */
    public static AnalysisEngineDescription use_existing(DockerWrapperContainerConfiguration container_config) throws ResourceInitializationException, IOException, SAXException {
        String cfg = "{}";
        if(container_config.get_unsafe_running_container_id().equals("")) {
            throw new InvalidParameterException();
        }
        AnalysisEngineDescription temp = createEngineDescription(DockerWrapper.class,DockerWrapper.PARAM_CFG,cfg,
                DockerWrapper.PARAM_CONFIRM_INTEGRITY, container_config.get_confirm_integrity(),
                DockerWrapper.PARAM_AUTOREMOVE, false,
                DockerWrapper.PARAM_CONTAINER_NAME, "",
                DockerWrapper.PARAM_EXPORT_NAME, container_config.get_export_name(),
                DockerWrapper.PARAM_RUN_IN_CONTAINER, true,
                DockerWrapper.PARAM_REUSE_CONTAINER, false,
                DockerWrapper.PARAM_MAP_DOECKER_DAEMON, false,
                DockerWrapper.PARAM_CONTAINER_ID,container_config.get_unsafe_running_container_id(),
                DockerWrapper.PARAM_ADDITIONAL_MODULES, container_config.get_additional_modules(),
                DockerWrapper.PARAM_ADDITIONAL_MODULES_CONFIGURATION,container_config.get_module_configurations());
        return temp;
    }

    /**
     * Escapes a given string into valid XMI so that one can use the returned string in an XMI String
     * @param raw The raw XMI which is to be processed into escaped XMI
     * @return The escaped XMI version
     */
    static public String escape(String raw) {
        //Taken from https://newbedev.com/java-escape-json-string
        String escaped = raw;
        escaped = escaped.replace("\\", "\\\\");
        escaped = escaped.replace("\"", "\\\"");
        escaped = escaped.replace("\b", "\\b");
        escaped = escaped.replace("\f", "\\f");
        escaped = escaped.replace("\n", "\\n");
        escaped = escaped.replace("\r", "\\r");
        escaped = escaped.replace("\t", "\\t");
        // TODO: escape other non-printing characters using uXXXX notation
        return escaped;
    }

    /**
     * Prints the given CAS in the XML format, this is for debug purposes only!
     * @param jcas The JCas document to print
     * @throws IOException
     * @throws SAXException
     */
    public void print_cas(JCas jcas) throws IOException, SAXException {
            org.apache.commons.io.output.ByteArrayOutputStream out = new org.apache.commons.io.output.ByteArrayOutputStream();
            XCASSerializer ser = new XCASSerializer(jcas.getTypeSystem());
            XMLSerializer xmlSer = new XMLSerializer(out, false);
            ser.serialize(jcas.getCas(), xmlSer.getContentHandler());
            String result = new String(out.toByteArray(), "UTF-8");
            out.close();

            System.out.println(result);
            System.out.println(result.length());
    }

    /**
     *
     * @param a Converts a given string to a module from the classpath. The module needs to extend the IDockerWrapperModule
     * @return A implementation of the IDockerWrapperModule.
     */
    public static IDockerWrapperModule string_to_module(String a) {
        try {
            return ((Class<? extends IDockerWrapperModule>) Class.forName(a)).getConstructor().newInstance();
        } catch (InstantiationException e) {
            return new DockerWrapperModuleErrorImplementation(e);
        } catch (IllegalAccessException e) {
            return new DockerWrapperModuleErrorImplementation(e);
        } catch (InvocationTargetException e) {
            return new DockerWrapperModuleErrorImplementation(e);
        } catch (NoSuchMethodException e) {
            return new DockerWrapperModuleErrorImplementation(e);
        } catch (ClassNotFoundException e) {
            return new DockerWrapperModuleErrorImplementation(e);
        }
    }

    /**
     * Initialises the DockerWrapper from the given context, sets all parameters and build and starts the container if the
     * flag is set.
     * @param aContext The context to read the variables from
     * @throws ResourceInitializationException
     */
    @Override
    public void initialize(UimaContext aContext) throws ResourceInitializationException {
        super.initialize(aContext);

        try {
            _modules = _additional_modules.stream().map(DockerWrapper::string_to_module).collect(Collectors.toList());
            for(int i = 0; i < _modules.size(); i++) {
                _modules.get(i).onInitialize(aContext,_additional_modules_config.get(i));
            }
            if(!_container_unsafe_id.equals("")) {
                _docker_interface = new DockerGeneralInterface();
                _containerid = _container_unsafe_id;
                int dockerport = _docker_interface.extract_port_mapping(_container_unsafe_id);
                _containerurl = "http://127.0.0.1:"+String.valueOf(dockerport)+"/process";
                return;
            }
            _configuration = DockerWrappedEnvironment.from(_cfg_string);

            if(_run_in_container) {
                Path tempdir = Files.createTempDirectory("reproanno");
                System.out.printf("Tempdir %s\n",tempdir.toString());
                Files.write(Paths.get(tempdir.toString(),"dockerfile"),_configuration.get_dockerfile().getBytes(StandardCharsets.UTF_8));
                Files.write(Paths.get(tempdir.toString(),"pom.xml"),_configuration.get_pomfile().getBytes(StandardCharsets.UTF_8));
                Files.write(Paths.get(tempdir.toString(),"cfg"),_cfg_string.getBytes(StandardCharsets.UTF_8));
                _docker_interface = new DockerGeneralInterface();
                _containerid = _docker_interface.build_and_run(tempdir,_use_gpu,_autoremove,_reuse_container,_container_name
                ,_unsafe_map_daemon);
                System.out.printf("Container id: %s\n",_containerid);
                int dockerport = _docker_interface.extract_port_mapping(_containerid);
                _containerurl = String.format("http://%s:%s/process",_docker_interface.get_ip(),String.valueOf(dockerport));

                System.out.printf("Container url: %s\n",_containerurl);
                long seconds = 0;
                while(!_docker_interface.get_logs(_containerid).contains("Server started on port 9714")) {
                    Thread.sleep(1000);
                    seconds+=1;
                    if(seconds>=_container_initialise_timeout) {
                        throw new ResourceInitializationException();
                    }
                }
                System.out.println("Container up and running!");
                System.out.println("Setting typesystem now!");
                {
                    HttpClient httpclient = HttpClients.createDefault();
                    HttpPost httppost = new HttpPost(String.format("http://%s:%s/set_typesystem",_docker_interface.get_ip(),String.valueOf(dockerport)));
                    ByteArrayOutputStream arr = new ByteArrayOutputStream();
                    JCas aJCas = JCasFactory.createJCas();
                    CasIOUtils.save(aJCas.getCas(),arr, SerialFormat.COMPRESSED_TSI);
                    HttpEntity entity = new InputStreamEntity(new ByteArrayInputStream(arr.toByteArray()), ContentType.DEFAULT_BINARY);
                    httppost.setEntity(entity);


                    HttpResponse httpresp = httpclient.execute(httppost);
                    HttpEntity respentity = httpresp.getEntity();

                    if(httpresp.getStatusLine().getStatusCode() != 200) {
                        System.err.println("Could not set container typesystem!");
                        throw new ResourceInitializationException();
                    }
                }
            }
            else {
                _processor = new InContainerEngineProcessor(_cfg_string);
            }
        } catch (InvalidXMLException e) {
            e.printStackTrace();
            throw new ResourceInitializationException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResourceInitializationException(e);
        } catch (CASException e) {
            e.printStackTrace();
            throw new ResourceInitializationException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new ResourceInitializationException(e);
        } catch (UIMAException e) {
            e.printStackTrace();
            throw new ResourceInitializationException(e);
        }
    }

    @Override
    public void batchProcessComplete()
            throws AnalysisEngineProcessException {
        if(_run_in_container) {
        }
        else {
            _processor.get_analysis_engine().batchProcessComplete();
        }
    }

    @Override
    public void collectionProcessComplete()
            throws AnalysisEngineProcessException {
        if(_run_in_container) {

        }
        else {
            _processor.get_analysis_engine().collectionProcessComplete();
        }
    }

    @Override
    public void setResultSpecification(ResultSpecification aResultSpec) {
        if(_run_in_container) {
        }
        else {
            try {
                _processor.get_analysis_engine().setResultSpecification(aResultSpec);
            } catch (AnalysisEngineProcessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Processes the given CAS document by sending it either to the container or by running the embedded analysis engine
     * otherwise. Also runs the modules and skips the CAS document if one of them returns false. Runs also the integrity checks
     * on the CAS document
     * @param aJCas The JCas document to process
     * @throws AnalysisEngineProcessException
     */
    @Override
    public void process(JCas aJCas) throws AnalysisEngineProcessException {
        boolean move_on = true;
        for(IDockerWrapperModule module : _modules) {
            move_on &= module.onBeforeProcess(aJCas);
        }
        if(!move_on) {
            for(IDockerWrapperModule module : _modules) {
                module.onAfterProcess(aJCas);
            }
            return;
        }

        if(_confirm_integrity) {
            JCas view;
            try {
                view = aJCas.createView(DockerWrapper.VIEW_NAME_CONFIRM_INTEGRITY);
            }
            catch(Exception e) {
                try {
                    view = aJCas.getView(DockerWrapper.VIEW_NAME_CONFIRM_INTEGRITY);
                } catch (Exception es) {
                    throw new AnalysisEngineProcessException(es);
                }
            }
            if(!DockerWrapperUtil.checkJCasIntegrity(view)) {
                throw new AnalysisEngineProcessException();
            }
            for(ReproducibleAnnotationHash a : JCasUtil.select(view,ReproducibleAnnotationHash.class)) {
                a.removeFromIndexes();
            }
        }

        if(_run_in_container) {
            System.out.printf("RUN in container with sofa length %s\n",aJCas.getDocumentText().length());
            JSONObject js = new JSONObject();
            StringWriter typesystem = new StringWriter();
            try {
                HttpClient httpclient = HttpClients.createDefault();
                HttpPost httppost = new HttpPost(_containerurl);
                ByteArrayOutputStream arr = new ByteArrayOutputStream();
                CasIOUtils.save(aJCas.getCas(),arr, SerialFormat.SERIALIZED);
                HttpEntity entity = new InputStreamEntity(new ByteArrayInputStream(arr.toByteArray()), ContentType.DEFAULT_BINARY);
                httppost.setEntity(entity);


                HttpResponse httpresp = httpclient.execute(httppost);
                HttpEntity respentity = httpresp.getEntity();

                if(httpresp.getStatusLine().getStatusCode() != 200) {
                    System.err.println("Got an error state!!!");
                    ByteArrayOutputStream result = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    for (int length; (length = respentity.getContent().read(buffer)) != -1; ) {
                        result.write(buffer, 0, length);
                    }
                    // StandardCharsets.UTF_8.name() > JDK 7
                    System.err.println(result.toString("UTF-8"));
                    throw new AnalysisEngineProcessException();
                }
                if (respentity != null) {
                    InputStream instream = respentity.getContent();
                    byte []kk = IOUtils.toByteArray(instream);
                    CasIOUtils.load(new ByteArrayInputStream(kk),null,aJCas.getCas(),CasLoadMode.DEFAULT);
                }
                else {
                    throw new AnalysisEngineProcessException();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new AnalysisEngineProcessException();
            }
        }
        else {
            _processor.process(aJCas);
        }
        if(_confirm_integrity) {
            System.out.println("Running with confirm integrity.");
            ReproducibleAnnotationHash new_hash = null;
            try {
                new_hash = new ReproducibleAnnotationHash(aJCas.getView(DockerWrapper.VIEW_NAME_CONFIRM_INTEGRITY));
                new_hash.setConfiguration_crc32(DockerWrapperUtil.calculate_hash(aJCas));
                new_hash.addToIndexes();
            } catch (CASException e) {
                throw new AnalysisEngineProcessException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new AnalysisEngineProcessException(e);
            } catch (SAXException e) {
                e.printStackTrace();
                throw new AnalysisEngineProcessException(e);
            }
        }
        for(IDockerWrapperModule module : _modules) {
            module.onAfterProcess(aJCas);
        }
    }

    /**
     * Destroys the Annotator stops the container and optinally export the image of the used container.
     */
    @Override
    public void destroy() {
        if(_run_in_container) {
            _docker_interface.export_to_new_image(_containerid,_export_name);
            if(_container_unsafe_id.equals("")) {
                _docker_interface.stop_container(_containerid);
            }
            try {
                _docker_interface._docker.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            if(_processor._analysis_engine!=null) {
                _processor.destroy();
            }
        }
    }
}
