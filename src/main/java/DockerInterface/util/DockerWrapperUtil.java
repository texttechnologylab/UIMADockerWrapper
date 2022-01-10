package DockerInterface.util;

import DockerInterface.DockerWrappedEnvironment;
import DockerInterface.DockerWrapper;
import DockerInterface.DockerWrapperContainerConfiguration;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.metadata.SofaMapping;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.impl.XCASSerializer;
import org.apache.uima.collection.CasConsumerDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.NameValuePair;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.TypeSystemUtil;
import org.apache.uima.util.XMLSerializer;
import org.hucompute.reproannotationnlp.ReproducibleAnnotation;
import org.hucompute.reproannotationnlp.ReproducibleAnnotationHash;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.CRC32;

import static DockerInterface.util.SSHUtils.*;

/**
 * This is a utility class providing some tools for the usage
 */
public class DockerWrapperUtil {
    /**
     * Prints some of the information obtainable by this implementation
     * @param cas The CAS document to pull the information from.
     * @throws InvalidXMLException
     * @throws ResourceInitializationException
     * @throws CASException
     * @throws IOException
     * @throws SAXException
     */
    static public void describeJCasPipelines(JCas cas) throws InvalidXMLException, ResourceInitializationException, CASException, IOException, SAXException {
        for(DockerWrappedEnvironment env : DockerWrapperUtil.configurationsFromJCas(cas)) {
            System.out.printf("\nEnvironment name: %s\n",env.get_name());
            for(AnnotatorDescription d : env.get_recursive_descriptions()) {
                System.out.printf("\n  Annotator name: %s\n",d.get_name());
                for(AnnotatorParameterWrapper param : d.get_parameters()) {
                    System.out.println("");
                    param.debug_print(4);
                }
            }
        }
    }

    static public void add_param_to_json(AnnotatorParameterWrapper param, JSONObject add) {
        switch (param.get_type()) {
            case ConfigurationParameter.TYPE_FLOAT:
                if (param.get_multivalued()) {
                    add.put("value",(Float[]) param.get_value());
                } else {
                    add.put("value",(Float) param.get_value());
                }
                break;
            case ConfigurationParameter.TYPE_STRING:
                if (param.get_multivalued()) {
                    add.put("value",(String[]) param.get_value());
                } else {
                    add.put("value",(String) param.get_value());
                }
                break;
            case ConfigurationParameter.TYPE_BOOLEAN:
                if (param.get_multivalued()) {
                    add.put("value",(Boolean[]) param.get_value());
                } else {
                    add.put("value",(Boolean) param.get_value());
                }
                break;
            case ConfigurationParameter.TYPE_INTEGER:
                if (param.get_multivalued()) {
                    add.put("value",(Integer[]) param.get_value());
                } else {
                    add.put("value",(Integer) param.get_value());
                }
                break;
        }
    }

    static public void add_param_to_json(Object value, JSONObject add) {
        if(value instanceof Integer) {
            add.put("value",(Integer)value);
        }
        else if(value instanceof Integer[]) {
            add.put("value",(Integer[])value);
        }
        else if(value instanceof Boolean) {
            add.put("value",(Boolean) value);
        }
        else if(value instanceof Boolean[]) {
            add.put("value",(Boolean[]) value);
        }
        else if(value instanceof String) {
            add.put("value",(String)value);
        }
        else if(value instanceof String[]) {
            add.put("value",(String[])value);
        }
        else if(value instanceof Float) {
            add.put("value",(Float) value);
        }
        else if(value instanceof Float[]) {
            add.put("value",(Float[]) value);
        }
    }

    static private JSONObject add_all_subtypes(String root, List<TypeDescription> tocheck, Map<String,List<TypeDescription>> supertypes, TypeSystemDescription desc) {
        JSONObject obj = new JSONObject();
        JSONArray children = new JSONArray();
        if(tocheck!=null) {
            for (TypeDescription d : tocheck) {
                children.put(add_all_subtypes(d.getName(), supertypes.get(d.getName()), supertypes,desc));
            }
        }
        TypeDescription rootType = desc.getType(root);
        if(rootType!=null) {
            obj.put("name", rootType.getName());
            obj.put("description", rootType.getDescription());
        }
        else {
            obj.put("name",root);
        }
        obj.put("children", children);
        return obj;
    }

    static public JSONObject typesystemToJSON(TypeSystemDescription desc) {
        Map<String,List<TypeDescription>> types = new HashMap<>();
        Set<String> rootTypes= new HashSet<>();
        for(TypeDescription type : desc.getTypes()) {
            if(types.containsKey(type.getSupertypeName())) {
                types.get(type.getSupertypeName()).add(type);
            }
            else {
                List<TypeDescription> lst = new LinkedList<>();
                lst.add(type);
                types.put(type.getSupertypeName(),lst);
            }
        }

        for(Map.Entry<String,List<TypeDescription>> entry : types.entrySet()) {
            String rootName = entry.getKey();
            TypeDescription t = desc.getType(rootName);
            if(t==null) {
                //rootName must be supertype
                rootTypes.add(rootName);
            }
            else {
                while(t!=null) {
                    rootName = t.getSupertypeName();
                    t = desc.getType(t.getSupertypeName());
                }
                rootTypes.add(rootName);
            }
        }

        JSONObject ts = new JSONObject();
        JSONArray arr = new JSONArray();
        for(String root : rootTypes) {
            arr.put(DockerWrapperUtil.add_all_subtypes(root,types.get(root),types,desc));
        }
        ts.put("typesystem",arr);
        return ts;
    }

    static public JSONObject analysisEngineDescriptionToJson(AnalysisEngineDescription engine) throws InvalidXMLException {
        JSONObject obj = new JSONObject();
        if(engine.isPrimitive()) {
            JSONObject annotator = new JSONObject();
            JSONArray parameters = new JSONArray();
            AnnotatorDescription wrapper = new AnnotatorDescription(engine);
            for(AnnotatorParameterWrapper wrp : wrapper.get_parameters()) {
                JSONObject param = new JSONObject();
                param.put("name",wrp.get_name());
                param.put("description",wrp.get_description());
                param.put("type",wrp.get_type());
                param.put("mandatory",wrp.get_mandatory());
                param.put("multivalued",wrp.get_multivalued());
                add_param_to_json(wrp,param);
                parameters.put(param);
            }
            annotator.put("parameters",parameters);
            annotator.put("name",wrapper.get_name());

            JSONArray unlisted_params = new JSONArray();
            for(String wrp : wrapper.get_unlisted_parameters()) {
                JSONObject param = new JSONObject();
                param.put("name",wrp);
                add_param_to_json(wrapper.get_unlisted_parameter(wrp),param);
                unlisted_params.put(param);
            }
            annotator.put("unlisted_parameters",unlisted_params);
            obj.put("annotator",annotator);
        }
        else {
            SofaMapping[] mappings = engine.getSofaMappings();
            JSONArray analysis_engines = new JSONArray();
            Map<String, ResourceSpecifier> spec = engine.getDelegateAnalysisEngineSpecifiers();
            for(String x : spec.keySet()) {
                ResourceSpecifier res = spec.get(x);
                if (res instanceof AnalysisEngineDescription) {
                    JSONObject result = analysisEngineDescriptionToJson((AnalysisEngineDescription) res);
                    JSONArray sofa_mappings = new JSONArray();
                    if(mappings!=null) {
                        for (SofaMapping mapping : mappings) {
                            if(mapping.getComponentKey().equals(x)) {
                                JSONObject sf = new JSONObject();
                                sf.put("component_sofa",mapping.getComponentSofaName());
                                sf.put("aggregate_sofa",mapping.getAggregateSofaName());
                                sofa_mappings.put(sf);
                            }
                        }
                    }
                    result.put("sofa_mappings",sofa_mappings);
                    analysis_engines.put(result);
                }
            }
            obj.put("engines",analysis_engines);
            obj.put("sofa_mappings",new LinkedList<JSONObject>());
        }
        return obj;
    }

    static private String[] extractNames(AnalysisEngineDescription engine, int recursionDepth) throws InvalidXMLException {
        List<String> lst = new ArrayList<String>();
        String offset = "";
        for(int i = 0; i < recursionDepth; i++) {
            offset+="  ";
        }
        if(engine.isPrimitive()) {
            lst.add(offset+engine.getAnnotatorImplementationName());
            offset+=" ";
            Map<String,ConfigurationParameter> val = new HashMap<String,ConfigurationParameter>();

            for(ConfigurationParameter param : engine.getAnalysisEngineMetaData().getConfigurationParameterDeclarations().getConfigurationParameters()) {
                val.put(param.getName(), param);
            }

            for(NameValuePair valuesName : engine.getAnalysisEngineMetaData().getConfigurationParameterSettings().getParameterSettings()) {
                ConfigurationParameter param = val.get(valuesName.getName());
                if(param==null) {
                    continue;
                }
                lst.add(offset+"Name: "+param.getName());
                lst.add(offset+"Type: "+param.getType());
                lst.add(offset+"Description: "+param.getDescription());
                lst.add(offset+"Multivalued: "+param.isMultiValued());
                lst.add(offset+"Mandatory: "+param.isMandatory());
                Object result = valuesName.getValue();
                switch(param.getType()) {
                    case ConfigurationParameter.TYPE_FLOAT:
                        if(param.isMultiValued()) {
                            String serialized = "[";
                            for(float inner : (float[])result) {
                                serialized+=String.valueOf(inner);
                            }
                            serialized+="]";
                            lst.add(offset+"Value: "+serialized);
                        }
                        else {
                            lst.add(offset+"Value: "+String.valueOf((float)result));
                        }
                        break;
                    case ConfigurationParameter.TYPE_STRING:
                        if(param.isMultiValued()) {
                            String serialized = "[";
                            for(String inner : (String[])result) {
                                serialized+=inner;
                            }
                            serialized+="]";
                            lst.add(offset+"Value: "+serialized);
                        }
                        else {
                            lst.add(offset+"Value: "+(String)result);
                        }
                        break;
                    case ConfigurationParameter.TYPE_BOOLEAN:
                        if(param.isMultiValued()) {
                            String serialized = "[";
                            for(Boolean inner : (Boolean[])result) {
                                serialized+=String.valueOf(inner);
                            }
                            serialized+="]";
                            lst.add(offset+"Value: "+serialized);
                        }
                        else {
                            lst.add(offset+"Value: "+String.valueOf((Boolean)result));
                        }
                        break;
                    case ConfigurationParameter.TYPE_INTEGER:
                        if(param.isMultiValued()) {
                            String serialized = "[";
                            for(Integer inner : (Integer[])result) {
                                serialized+=String.valueOf(inner);
                            }
                            serialized+="]";
                            lst.add(offset+"Value: "+serialized);
                        }
                        else {
                            lst.add(offset+"Value: "+String.valueOf((Integer) result));
                        }
                        break;
                    default:
                        throw new InvalidXMLException();
                }
                lst.add("");
            }
        }
        else {
            Map<String, ResourceSpecifier> spec = engine.getDelegateAnalysisEngineSpecifiers();
            for(String x : spec.keySet()) {
                ResourceSpecifier res = spec.get(x);
                if (res instanceof AnalysisEngineDescription) {
                    for(String inner : DockerWrapperUtil.extractNames((AnalysisEngineDescription) res,recursionDepth+1)) {
                        lst.add(inner);
                    }
                    lst.add("");
                }
            }
        }
        String []arr = new String[lst.size()];
        lst.toArray(arr);
        return arr;
    }

    /**
     * Returns a list of all annotator names which were run on the CAS document in chronological order.
     * @param cas The CAS document to construct the annotator names from.
     * @return The list of annotator names.
     * @throws IOException
     * @throws InvalidXMLException
     * @throws ResourceInitializationException
     * @throws SAXException
     * @throws CompressorException
     * @throws CASException
     */
    static public String[] getAnnotatorNames(JCas cas) throws IOException, InvalidXMLException, ResourceInitializationException, SAXException, CompressorException, CASException {
        List<String> k = new LinkedList<>();
        get_sorted_reproducible_annotations(cas,null).stream().forEach((a)->{
            try {
                DockerWrappedEnvironment.fromAnnotation(a).get_recursive_descriptions().stream().forEach((b)->{
                    k.add(b.get_name());
                });
            } catch (InvalidXMLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CompressorException e) {
                e.printStackTrace();
            }
        });
        String[] arr = new String[k.size()];
        k.toArray(arr);
        return arr;
    }

    static private AnalysisEngineDescription add_wrapped_pipeline_to_builder(List<ReproducibleAnnotation> repo) throws IOException, InvalidXMLException, ResourceInitializationException, SAXException, CompressorException {
        AggregateBuilder agg = new AggregateBuilder();
        for(int i = 0; i < repo.size(); i++) {
            ReproducibleAnnotation a = repo.get(i);

            DockerWrappedEnvironment wrp = DockerWrappedEnvironment.fromAnnotation(a);
            agg.add(wrp.get_engine_description());
        }
        if(repo.size() == 0) {
            throw new InvalidParameterException("No annotation found to reproduce!");
        }
        return agg.createAggregateDescription();
    }

    static public List<ReproducibleAnnotation> get_sorted_reproducible_annotations(JCas cas, String viewName) throws CASException, ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        List<ReproducibleAnnotation> repo = new ArrayList<ReproducibleAnnotation>();
        if(viewName!=null) {
            repo.addAll(collect_reproducible_annotations(cas.getView(viewName)));
        }
        else
        {
            Iterator<JCas> jc = cas.getViewIterator();
            while (jc.hasNext()) {
                repo.addAll(collect_reproducible_annotations(jc.next()));
            }
        }
        Collections.sort(repo, new Comparator<ReproducibleAnnotation>(){
            public int compare(ReproducibleAnnotation o1, ReproducibleAnnotation o2)
            {
                return Long.compare(o1.getTimestamp(), o2.getTimestamp());
            }
        });
        return repo;
    }

    static public AnalysisEngineDescription wrappedPipelineFromJCas(JCas cas, String view) throws IOException, InvalidXMLException, ResourceInitializationException, SAXException, CompressorException, CASException {
        AggregateBuilder agg = new AggregateBuilder();
        List<ReproducibleAnnotation> repo = get_sorted_reproducible_annotations(cas,view);
        return add_wrapped_pipeline_to_builder(repo);
    }

    /**
     * Convers a cas document to its XMI representation
     * @param jcas The cas document to convert to a XMI string
     * @return The XMI representation of the CAS document.
     * @throws IOException
     * @throws SAXException
     */
    public static String cas_to_xmi(JCas jcas) throws IOException, SAXException {
        org.apache.commons.io.output.ByteArrayOutputStream out = new org.apache.commons.io.output.ByteArrayOutputStream();
        XCASSerializer ser = new XCASSerializer(jcas.getTypeSystem());
        XMLSerializer xmlSer = new XMLSerializer(out, true);
        ser.serialize(jcas.getCas(), xmlSer.getContentHandler());
        String result = new String(out.toByteArray(), "UTF-8");
        out.close();
        return result;
    }

    /**
     * Convers a cas document to its XMI representation
     * @param jcas The cas document to convert to a XMI string
     * @return The XMI representation of the CAS document.
     * @throws IOException
     * @throws SAXException
     */
    public static String raw_cas_to_xmi(CAS jcas) throws IOException, SAXException {
        org.apache.commons.io.output.ByteArrayOutputStream out = new org.apache.commons.io.output.ByteArrayOutputStream();
        XCASSerializer ser = new XCASSerializer(jcas.getTypeSystem());
        XMLSerializer xmlSer = new XMLSerializer(out, true);
        ser.serialize(jcas, xmlSer.getContentHandler());
        String result = new String(out.toByteArray(), "UTF-8");
        out.close();
        return result;
    }

    /**
     * Prints a cas document in the XMI format
     * @param jcas The jcas document to print
     * @throws IOException
     * @throws SAXException
     */
    public void print_cas(JCas jcas) throws IOException, SAXException {
        String result = cas_to_xmi(jcas);

        System.out.println(result);
        System.out.println(result.length());
    }

    /**
     * Calculates the hash for a JCAS document
     * @param jcas The jcas document to calculate the hash for.
     * @return the calculated hash
     * @throws IOException
     * @throws SAXException
     */
    static public long calculate_hash(JCas jcas) throws IOException, SAXException {
        CRC32 crc = new CRC32();
        String result = DockerWrapperUtil.cas_to_xmi(jcas).replaceAll("_id=\"[0-9]+\"","");
        byte[] arr = result.getBytes(StandardCharsets.UTF_8);
        crc.update(arr,0,arr.length);
        return crc.getValue();
    }

    /**
     * Checks if all JCas annotations are covered by reproducible annotations
     * @param cas The JCAS document to check the coverage for.
     * @return
     */
    static public boolean checkJCasIntegrity(JCas cas) {
        long iter = 0;
        long hash = 0;
        try {
            JCas inital = cas.getView(DockerWrapper.VIEW_NAME_CONFIRM_INTEGRITY);

            for (ReproducibleAnnotationHash a : JCasUtil.select(inital,ReproducibleAnnotationHash.class)) {
                hash = a.getConfiguration_crc32();
                iter++;
                a.removeFromIndexes();
            }
            if (iter == 1) {
                if (DockerWrapperUtil.calculate_hash(cas) != hash) {
                    return false;
                }

                ReproducibleAnnotationHash repo = new ReproducibleAnnotationHash(inital);
                repo.setConfiguration_crc32(hash);
                repo.addToIndexes();
                return true;
            }
            if (iter == 0) {
                Iterator<JCas> iterator = cas.getViewIterator();
                while(iterator.hasNext()) {
                    JCas a = iterator.next();
                    long count = 0;
                    for (TOP x : JCasUtil.select(a, TOP.class)) {
                        count++;
                    }
                    if (count > 1) {
                        return false;
                    }
                }

                return true;
            }
            return false;
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * Creates a new AnalysisEngineDescription from all reproducible annotations and the given DockerContainerConfiguration.
     * The analysis engine is reconstructed in a timely order
     * @param cas The JCas document to get the reproducible pipelines from
     * @param cfg The docker wrapper container configuration to recreate the pipelines with.
     * @return The newly created AnalysisEngineDescription
     * @throws IOException
     * @throws InvalidXMLException
     * @throws ResourceInitializationException
     * @throws SAXException
     * @throws CompressorException
     * @throws CASException
     */
    static public AnalysisEngineDescription fromJCas(JCas cas, DockerWrapperContainerConfiguration cfg) throws IOException, InvalidXMLException, ResourceInitializationException, SAXException, CompressorException, CASException {
       return fromJCas(cas,cfg,null);
    }


    /**
     * Creates a new AnalysisEngineDescription from all reproducible annotations from the given view. The analysis engine is reconstructed
     * in a timely order.
     * @param cas The CAS document containing the reproducible annotations
     * @param cfg The container configuration to use
     * @param viewName The view name to extract the annoations from
     * @return
     * @throws IOException
     * @throws InvalidXMLException
     * @throws ResourceInitializationException
     * @throws SAXException
     * @throws CASException
     * @throws CompressorException
     */
    static public AnalysisEngineDescription fromJCas(JCas cas, DockerWrapperContainerConfiguration cfg, String viewName) throws IOException, InvalidXMLException, ResourceInitializationException, SAXException, CASException, CompressorException {
        List<ReproducibleAnnotation> repo = get_sorted_reproducible_annotations(cas,viewName);
        return add_annotation_list_to_builder(repo, cfg);
    }

    /**
     * Extracts a list of DockerWrappedEnvironments from the given cas document sorted by timestamp
     * @param cas The cas document to extract the Wrapped Environments from
     * @return The sorted list of DockerWrappedEnvironments
     */
    static public List<DockerWrappedEnvironment> configurationsFromJCas(JCas cas) throws CASException, ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        return configurationsFromJCas(cas,null);
    }

    /**
     * Creates a new reproducible analysis engine description from a list of wrapped environments.
     * @param envs The environments used.
     * @param cfg The container configuration.
     * @return A reproducible analysis engine description.
     * @throws ResourceInitializationException
     * @throws IOException
     * @throws SAXException
     */
    static public AnalysisEngineDescription fromEnvironmentLists(List<DockerWrappedEnvironment> envs, DockerWrapperContainerConfiguration cfg) throws ResourceInitializationException, IOException, SAXException {
        AggregateBuilder builder = new AggregateBuilder();
        for(DockerWrappedEnvironment e : envs) {
            builder.add(e.build(cfg));
        }
        return builder.createAggregateDescription();
    }

    /**
     * Extracts a list of DockerWrappedEnvironments from the given cas document sorted by timestamp.
     * @param cas The cas to extract the wrapped environments from
     * @param viewName The view name to extract the wrapped environments from
     * @return A List of sorted wrapped environments by timestamp
     */
    static public List<DockerWrappedEnvironment> configurationsFromJCas(JCas cas, String viewName) throws CASException, ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        List<ReproducibleAnnotation> repo = get_sorted_reproducible_annotations(cas,viewName);

        return repo.stream().map((a) -> {
            try {
                return DockerWrappedEnvironment.fromAnnotation(a);
            } catch (InvalidXMLException e) {
                e.printStackTrace();
                throw new UncheckedIOException(new IOException());
            } catch (IOException e) {
                e.printStackTrace();
                throw new UncheckedIOException(e);
            } catch (CompressorException e) {
                e.printStackTrace();
                throw new UncheckedIOException(new IOException());
            }
        }).collect(Collectors.toList());
    }

    static private AnalysisEngineDescription add_annotation_list_to_builder(List<ReproducibleAnnotation> repo, DockerWrapperContainerConfiguration cfg) throws IOException, InvalidXMLException, ResourceInitializationException, SAXException, CompressorException {
        AggregateBuilder agg = new AggregateBuilder();
        for(int i = 0; i < repo.size(); i++) {
            ReproducibleAnnotation a = repo.get(i);

            DockerWrappedEnvironment wrp = DockerWrappedEnvironment.fromAnnotation(a);
            agg.add(wrp.build(cfg));
        }
        if(repo.size() == 0) {
            throw new InvalidParameterException("No annotations to reproduce!");
        }
        return agg.createAggregateDescription();
    }

    static private List<ReproducibleAnnotation> collect_reproducible_annotations(JCas cas) throws IOException, InvalidXMLException, ResourceInitializationException, SAXException {
        List<ReproducibleAnnotation> repo = new ArrayList<ReproducibleAnnotation>();
        for(ReproducibleAnnotation a : JCasUtil.select(cas,ReproducibleAnnotation.class)) {
            repo.add(a);
        }
        return repo;
    }

    static public void ducc_execute(CollectionReaderDescription reader, AnalysisEngineDescription engine, CasConsumerDescription collection_writer) throws Exception {
        Properties prop = new Properties();
        prop.setProperty("process_deployments_max", String.valueOf(1));
        prop.setProperty("process_memory_size", String.valueOf(2));
        prop.setProperty("process_per_item_time_max", String.valueOf(1800));
        prop.setProperty("process_pipeline_count", "1");
        prop.setProperty("process_failures_limit", "99564");
        prop.setProperty("process_initialization_failures_cap", "9999");

        prop.setProperty("working_directory", Paths.get("/home/ducc/ducc","ducctest").toString());
        prop.setProperty("log_directory", Paths.get("/home/ducc/ducc","ducctest/logs").toString());

        prop.setProperty("driver_jvm_args", "\"-Xmx20g -Dfile.encoding=utf-8\"");
        prop.setProperty("driver_exception_handler_arguments", "\"max_job_errors=99564 max_timeout_retrys_per_workitem=0\"");

        prop.setProperty("process_error_window_threshold", "20");
        prop.setProperty("process_error_window_size", "100");


        prop.setProperty("classpath", "/home/ducc/ducc/apache-uima-ducc/lib/uima-ducc/workitem/uima-ducc-workitem-v2.jar:"
                + "/home/ducc/ducc/apache-uima-ducc/apache-uima/lib/*:"
                + "/home/ducc/ducc/apache-uima-ducc/apache-uima/apache-activemq/lib/*:"
                + "/home/ducc/ducc/apache-uima-ducc/apache-uima/apache-activemq/lib/optional/*:"
//				+ "/home/ducc/ducc/jars/sub2/*"
                + "/home/ducc/ducc/jarsTagMe/*:"
                + "/home/ducc/ducc/jars/textimager-uima-deploy-0.0.2-models.jar:"
                + "/home/ducc/ducc/jars/textimager-uima-deploy-0.3.0-source.jar"
//				+ "/home/ducc/ducc/jars/textimager-uima-deploy-0.3.2-source.jar"
                .replace("$DUCC_HOME", "/home/ducc/ducc"));

        String inputFormat = "TXT";
        String language = "en";
        String fileSuffix = "txt";

        String modificationUser = "";
        String modificationComment = "";

        prop.setProperty("description", "\"Reproducible Docker Wrapper on DUCC\"");


        StringWriter wr = new StringWriter();
        reader.toXML(wr);
        Files.write(Paths.get("collection_reader.xml"),wr.getBuffer().toString().getBytes(StandardCharsets.UTF_8));
        prop.setProperty("driver_descriptor_CR", Paths.get("collection_reader.xml").toAbsolutePath().toString());

        StringWriter cc = new StringWriter();
        collection_writer.toXML(cc);
        Files.write(Paths.get("collection_writer.xml"),cc.getBuffer().toString().getBytes(StandardCharsets.UTF_8));
        prop.setProperty("process_descriptor_CC",Paths.get("collection_writer.xml").toAbsolutePath().toString());

        StringWriter ae = new StringWriter();
        engine.toXML(ae);
        Files.write(Paths.get("analysis_engine.xml"),ae.getBuffer().toString().getBytes(StandardCharsets.UTF_8));
        prop.setProperty("process_descriptor_AE", Paths.get("analysis_engine.xml").toAbsolutePath().toString());

        System.out.println(prop);
        prop.entrySet().stream().map(x->x.getKey()+"\t"+x.getValue()).forEach(System.out::println);

        //
        long duccId = -1;
        try {
            duccId = sshDuccJobSubmit(prop);
            //			DuccJobSubmit ds = new DuccJobSubmit(prop, null);
            //			boolean rc = ds.execute();
            // If the return is ’true’ then as best the API can tell, the submit worked
            if ( duccId > -1 ) {
                //				duccId = ds.getDuccId();

                // Startzeitpunkt des Jobs merken
                // TODO: Später im RM speichern
                long timeNowMillis = System.currentTimeMillis();
                System.out.println("Job started: " + timeNowMillis);
            } else {
                System.out.println("Could not submit job");
                throw new Exception("Could not submit job!");
            }
        }
        catch(Exception e) {
            System.out.println("Cannot initialize: " + e);
            throw e;
        }
    }
}
