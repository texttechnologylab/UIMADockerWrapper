package DockerInterface;

import DockerInterface.base_env.DockerBaseJavaEnv;
import DockerInterface.base_env.IDockerBaseEnv;
import DockerInterface.util.AnnotatorDescription;
import com.bubelich.jBaseZ85;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.metadata.SofaMapping;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.SofaMappingFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.hucompute.reproannotationnlp.ReproducibleAnnotation;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;

/**
 * This class provides the basic environment for every DockerWrapper wrapped AnalysisEngineDescription. Here one can set
 * the experiment names, which one wants to preserve, the used dockerfile the pom dependencies and also the compression
 * method.
 */
public class DockerWrappedEnvironment {
    /**
     * The string representation of the dockerfile used to build the container
     */
    private String _dockerfile;

    /**
     * The string representation of the pom file used to install the dependencies in the maven file
     */
    private String _pom_file;

    /**
     * The string representation of the AnalysisEngineDescription
     */
    private String _engine_serialized;

    /**
     * The wrapped AnalysisEngineDescription itself
     */
    private AnalysisEngineDescription _engine;

    /**
     * The name of the environment, or experiment
     */
    private String _name;

    /**
     * The compression used, this must be a valid name from the apache common compressor package.
     */
    private String _compression;

    /**
     * This determines wo which view the reproducible annoation is written.
     */
    private String _to_view;

    long _timestamp;

    /**
     * Creates a new wrapped environment from 1..n AnalysisEngineDescriptions
     * @param base The base analysis engine this object must be at least constructed from one AnalysisEngineDescription
     * @param others An arbitary amount of other analysis engines to use along side.
     * @return The new instance of the DockerWrappedEnvironment
     * @throws ResourceInitializationException
     * @throws IOException
     * @throws SAXException
     */
    public static DockerWrappedEnvironment from(AnalysisEngineDescription base, AnalysisEngineDescription ...others) throws ResourceInitializationException, IOException, SAXException, InvalidXMLException {
        return new DockerWrappedEnvironment(base,others);
    }

    /**
     * Recreates the DockerWrappedEnvironment from the given string.
     * @param base The string to intialise the DockerWrappedEnvironment from
     * @return The new instance of the DockerWrappedEnvironment
     * @throws InvalidXMLException
     * @throws IOException
     */
    public static DockerWrappedEnvironment from(String base) throws InvalidXMLException, IOException {
        return new DockerWrappedEnvironment(base);
    }

    /**
     * The basic constructor initialises every variable except of the engine to a valid input. The default dockerfile is the java environment
     * with maven installed. The default pom file installs this library and the dependencies of it.
     */
    private DockerWrappedEnvironment(AnalysisEngineDescription base, AnalysisEngineDescription ...others) throws ResourceInitializationException, IOException, SAXException, InvalidXMLException {
        _dockerfile =   new DockerBaseJavaEnv().get_assembled_dockerfile();
        _to_view = "";
        _pom_file = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <groupId>org.hucompute</groupId>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <version>1.0.0</version>\n" +
                "    <artifactId>reproduciblea_annotations</artifactId>\n" +
                "\n" +
                "\n" +
                "    <properties>\n" +
                "        <maven.compiler.source>8</maven.compiler.source>\n" +
                "        <maven.compiler.target>8</maven.compiler.target>\n" +
                "      </properties>\n" +
                "        <repositories>\n" +
                "                <repository>\n" +
                "                        <id>central</id>\n" +
                "                        <name>Central Repository</name>\n" +
                "                        <url>https://repo.maven.apache.org/maven2</url>\n" +
                "                        <layout>default</layout>\n" +
                "                        <snapshots>\n" +
                "                                <enabled>false</enabled>\n" +
                "                        </snapshots>\n" +
                "                </repository>\n" +
                "  </repositories>\n" +
                "  <dependencies>\n"+
                "  </dependencies>\n" +
                "</project>";
        _name = "unnamed";
        _compression = CompressorStreamFactory.XZ;

        _timestamp = 0;
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(base);

        if(others.length!=0) {
            for(AnalysisEngineDescription x : others) {
                agg.add(x);
            }
        }
        _engine = agg.createAggregateDescription();
        //WARNING: POSSIBLY UNSAFE BUT REDUCES ANNOTATION SIZE BY FACTOR 20
        for(AnnotatorDescription d : get_recursive_descriptions()) {
            d.get_underlying_engine().getAnalysisEngineMetaData().setTypeSystem(TypeSystemDescriptionFactory.createTypeSystemDescription("types.ReproducibleAnnotation"));
            StringWriter xml = new StringWriter();
            d.get_underlying_engine().getAnalysisEngineMetaData().getTypeSystem().toXML(xml);
        }

        StringWriter wr = new StringWriter();
        _engine.toXML(wr);
        _engine_serialized = wr.getBuffer().toString();
    }

    /**
     * Gets the current saved pomfile from the wrapped environment
     * @return The string representation of the currently set pomfile
     */
    public String get_pomfile() {
        return _pom_file;
    }

    /**
     * Returns the string representation of the currently set dockerfile.
     * @return The string representation of the currently set dockerfile.
     */
    public String get_dockerfile() {
        return _dockerfile;
    }


    /**
     * Returns the name which was given to the particular environment, this can be used to remind onself what kind of pipelines or experiments
     * took place within the associated docker wrapper
     * @return The string representation of the set name the default is unnamed
     */
    public String get_name() {
        return _name;
    }

    /**
     * Returns the currently set compression method for the WrappedEnvironment object. The compression string is directly the
     * apache commons compression name for the compressor.
     * @return The string representation of the used compressor.
     */
    public String get_compression() {return _compression;}

    /**
     * Sets the compression method. Valid entries are either "" or the names from the apache common compressors library.
     * @param method
     * @return
     */
    public DockerWrappedEnvironment with_compression(String method) {_compression = method;
        return this;
    }

    /**
     * Sets the WrappedEnvironment name to reproduce it later on.
     * @param name The new name of the DockerWrappedEnvironment
     * @return A reference to itself for better function chaining
     */
    public DockerWrappedEnvironment with_name(String name) {
        _name = name;
        return this;
    }

    /**
     * Returns the string representation of the wrapped AnalysisEngineDescription
     * @return The string representation of the wrapped AnalysisEngineDescription
     */
    public String get_engine_string_description() throws IOException, SAXException {
        update();
        return _engine_serialized;
    }

    /**
     * Recreates the docker wrapped environment from the given string. The string must be uncompressed and in the JSON format
     * @param config The JSON representation of the DockerWrappedEnvironment object.
     * @throws InvalidXMLException
     * @throws IOException
     */
    private DockerWrappedEnvironment(String config) throws InvalidXMLException, IOException {
        JSONObject js = new JSONObject(config);
        _engine_serialized = js.getString("engine");
        _name = js.getString("name");
        _pom_file = js.getString("pom_file");
        _dockerfile = js.getString("dockerfile");
        _compression = js.getString("compression");
        _to_view = js.getString("to_view");
        Path tmpfile = Files.createTempFile("reproanno",".xml");
        Files.write(tmpfile, _engine_serialized.getBytes(StandardCharsets.UTF_8));
        _engine = AnalysisEngineFactory.createEngineDescriptionFromPath(tmpfile.toString());
    }

    /**
     * The key to set the configuration parameter of the DockerWrapper Annotator object.
     * @return The string representation of the DockerWrapper Annotator object.
     */
    public String key() {
        return DockerWrapper.PARAM_CFG;
    }

    /**
     * Checks if the wrapped engine has a sofa mapping for this SoFa
     * @param from The name of the SoFa
     * @return True if there is a mapping for this SoFa or false otherwise.
     */
    public boolean has_sofa_mapping(String from) {
        if(_engine.getSofaMappings()!=null) {
            for(SofaMapping x : _engine.getSofaMappings()) {
                if(x.getComponentSofaName().equals(from)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes all sofa mappings which map the view in the component
     * @param from The view which is mapped in the component
     * @throws IOException
     * @throws SAXException
     */
    public void remove_sofa_mappings(String from) throws IOException, SAXException {
        if(_engine.getSofaMappings()!=null) {
            List<SofaMapping> new_mappings = new LinkedList<>();
            new_mappings.addAll(Arrays.stream(_engine.getSofaMappings()).filter((a)->{return a.getComponentSofaName().equals(from);}).collect(Collectors.toList()));
            SofaMapping []arr = new SofaMapping[new_mappings.size()];
            new_mappings.toArray(arr);
            _engine.setSofaMappings(arr);
        }
    }

    /**
     * Removes all sofa mapping where the component view is mapped to a specific aggregate view
     * @param from The view from which to map the view
     * @param to The view to which the from view is mapped
     * @throws IOException
     * @throws SAXException
     */
    public void remove_sofa_mappings(String from,String to) throws IOException, SAXException {
        if(_engine.getSofaMappings()!=null) {
            List<SofaMapping> new_mappings = new LinkedList<>();
            new_mappings.addAll(Arrays.stream(_engine.getSofaMappings()).filter((a)->{return a.getComponentSofaName().equals(from)&&a.getAggregateSofaName().equals(to);}).collect(Collectors.toList()));
            SofaMapping []arr = new SofaMapping[new_mappings.size()];
            new_mappings.toArray(arr);
            _engine.setSofaMappings(arr);
        }
    }

    /**
     * Adds a new sofa mapping for all underlying AnalysisEngineDescriptions
     * @param from The view to map from
     * @param to The view to map to
     * @return A reference to itself for better function chaining.
     * @throws InvalidXMLException
     * @throws IOException
     * @throws SAXException
     */
    public DockerWrappedEnvironment with_sofa_mapping(String from, String to) throws InvalidXMLException, IOException, SAXException {
        List<SofaMapping> new_mappings = new LinkedList<>();
        if(_engine.getSofaMappings()!=null) {
            new_mappings.addAll(Arrays.stream(_engine.getSofaMappings()).collect(Collectors.toList()));
        }
            for(Map.Entry<String,ResourceSpecifier> x : _engine.getDelegateAnalysisEngineSpecifiers().entrySet()) {
                new_mappings.add(SofaMappingFactory.createSofaMapping(x.getKey(),from,to));
            }
            SofaMapping []arr = new SofaMapping[new_mappings.size()];
            new_mappings.toArray(arr);
            _engine.setSofaMappings(arr);
        return this;
    }

    /**
     * Sets the target view to which the implementation should write the reproducible annotation to.
     * @param view_name The view name to which to write the annotation to.
     * @return A reference to itself for better function chaining
     */
    public DockerWrappedEnvironment with_reproducible_annotation_target_view(String view_name) {
        _to_view = view_name;
        return this;
    }

    /**
     * Returns the name of the reproducible annotation target view
     * @return The name of the reproducible annotation target view
     */
    public String get_reproducible_annotation_target_view() {
        return _to_view;
    }


    /**
     * Builds an reproducible AnalysisEngineDescription from a given container configuration
     * @param container_config The container configuration from which to create the reproducible annotation
     * @return An reproducible AnalysisEngineDescription
     * @throws ResourceInitializationException
     * @throws IOException
     * @throws SAXException
     */
    public AnalysisEngineDescription build(DockerWrapperContainerConfiguration container_config) throws ResourceInitializationException, IOException, SAXException {
        String cfg = toJsonString();
        return createEngineDescription(DockerWrapper.class,DockerWrapper.PARAM_CFG,cfg,
                DockerWrapper.PARAM_CONFIRM_INTEGRITY, container_config.get_confirm_integrity(),
                DockerWrapper.PARAM_AUTOREMOVE, container_config.get_autoremove(),
                DockerWrapper.PARAM_CONTAINER_NAME, container_config.get_containername(),
                DockerWrapper.PARAM_EXPORT_NAME, container_config.get_export_name(),
                DockerWrapper.PARAM_RUN_IN_CONTAINER, container_config.get_run_in_container(),
                DockerWrapper.PARAM_REUSE_CONTAINER, container_config.get_reuse_container(),
                DockerWrapper.PARAM_ADDITIONAL_MODULES, container_config.get_additional_modules(),
                DockerWrapper.PARAM_MAP_DOECKER_DAEMON, container_config.get_unsafe_map_docker_daemon(),
                DockerWrapper.PARAM_ADDITIONAL_MODULES_CONFIGURATION,container_config.get_module_configurations());
    }

    /**
     * Sets the dockerfile string representation of this object
     * @param dockerfile The string representation of the dockerfile that is used to build the container
     * @return A reference to itself to chain the functions
     */
    public DockerWrappedEnvironment with_dockerfile(String dockerfile) {
        _dockerfile = dockerfile;
        return this;
    }

    /**
     * Sets the string representation of the dockerfile from the given DockerBase Environment object
     * @param env The environment used to construct the string representation of the underlying dockerfile
     * @return A reference to itself to better enable function chaining
     */
    public DockerWrappedEnvironment with_dockerfile(IDockerBaseEnv env) {
        _dockerfile = env.get_assembled_dockerfile();
        return this;
    }

    /**
     * Sets the string representation of the dockerfile from the given File object.
     * @param dockerfile The file which contains the dockerfile which should be used to build the container object
     * @return A reference to itself to better enable function chaining
     * @throws IOException
     */
    public DockerWrappedEnvironment with_dockerfile(File dockerfile) throws IOException {
        _dockerfile = new String(Files.readAllBytes(dockerfile.toPath()));
        return this;
    }

    /**
     * Sets the pomfile from the given string representation of the pom file.
     * @param pomfile The string representation of a valid pomfile which is used to install the java dependencies in the container
     * @return A reference to itself to better enable function chaining
     */
    public DockerWrappedEnvironment with_pomfile(String pomfile) {
        _pom_file = pomfile;
        return this;
    }

    private void get_descriptions_depth(AnalysisEngineDescription subject, List<AnnotatorDescription> lst) throws InvalidXMLException {
        if(subject.isPrimitive()) {
            lst.add(new AnnotatorDescription(subject));
        }
        else {
            Map<String, ResourceSpecifier> spec = subject.getDelegateAnalysisEngineSpecifiers();
            for(String x : spec.keySet()) {
                ResourceSpecifier res = spec.get(x);
                if (res instanceof AnalysisEngineDescription) {
                    get_descriptions_depth((AnalysisEngineDescription) res,lst);
                }
            }
        }
    }

    /**
     * Returns the wrapped AnalysisEngineDescription
     * @return the wrapped AnalysisEngineDescription
     */
    public AnalysisEngineDescription get_engine_description() {
        return _engine;
    }

    /**
     * Returns a list of primitive AnalysisEngineDescriptions which make up wrapped AnalysisEngineDescription
     * @return the list of primitive analysis engine descriptions wrapped in a utility class
     */
    public List<AnnotatorDescription> get_recursive_descriptions() throws InvalidXMLException {
        List<AnnotatorDescription> wrapper = new LinkedList<>();
        get_descriptions_depth(_engine,wrapper);
        return wrapper;
    }

    private DockerWrappedEnvironment set_timestamp(long timestamp) {
        _timestamp = timestamp;
        return this;
    }

    /**
     * Returns the original timestamp of creation: is only unequal to zero if this object was created with the fromAnnotation(...) method!!
     */
    public long get_timestamp() {
        return _timestamp;
    }

    /**
     * Creates the DockerWrappedEnvironment from the given reproducible annotation, this will decrompress the annotation and recreate the environment
     * @param repro The annotation to reproduce the environment from
     * @return The Wrapped Environment
     * @throws IOException
     * @throws InvalidXMLException
     * @throws CompressorException
     */
    static public DockerWrappedEnvironment fromAnnotation(ReproducibleAnnotation repro) throws IOException, InvalidXMLException, CompressorException {
        if(!repro.getCompression().equals("")) {
            ByteArrayInputStream str = new ByteArrayInputStream(jBaseZ85.decode(repro.getConfiguration()));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CompressorInputStream in = new CompressorStreamFactory().createCompressorInputStream(repro.getCompression(), str);
            IOUtils.copy(in, out);
            in.close();
            return new DockerWrappedEnvironment(new String(out.toByteArray(),StandardCharsets.UTF_8)).set_timestamp(repro.getTimestamp());
        }
        else {
            return new DockerWrappedEnvironment(new String(jBaseZ85.decode(repro.getConfiguration()),StandardCharsets.UTF_8)).set_timestamp(repro.getTimestamp());
        }
    }

    /**
     * Sets the pomfile representation from the given file object.
     * @param pomfile The pomfile object which contains a valid representation of a pomfile
     * @return A reference to itself to better enable function chaining.
     * @throws IOException
     */
    public DockerWrappedEnvironment with_pomfile(File pomfile) throws IOException {
        _pom_file = new String(Files.readAllBytes(pomfile.toPath()));
        return this;
    }

    private DockerWrappedEnvironment update() throws IOException, SAXException {
        StringWriter wr = new StringWriter();
        _engine.toXML(wr);
        _engine_serialized = wr.getBuffer().toString();
        return this;
    }

    /**
     * Constructs a JSONObject containing all necessary fields to recreate this object.
     * @return A JSON representation of this object.
     */
    public String toJsonString() throws IOException, SAXException {
        JSONObject js = new JSONObject();
        js.put("dockerfile",_dockerfile);
        js.put("pom_file",_pom_file);
        js.put("engine",get_engine_string_description());
        js.put("name",_name);
        js.put("compression",_compression);
        js.put("to_view",_to_view);
        return js.toString();
    }
}
