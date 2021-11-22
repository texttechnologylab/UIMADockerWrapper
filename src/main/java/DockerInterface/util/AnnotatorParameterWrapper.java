package DockerInterface.util;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.NameValuePair;
import org.apache.uima.util.InvalidXMLException;

public class AnnotatorParameterWrapper {
    private ConfigurationParameter _config;
    private AnalysisEngineDescription _underlyingEngine;
    static final public String TYPE_STRING = ConfigurationParameter.TYPE_STRING;
    static final public String TYPE_BOOLEAN = ConfigurationParameter.TYPE_BOOLEAN;
    static final public String TYPE_INTEGER = ConfigurationParameter.TYPE_INTEGER;
    static final public String TYPE_FLOAT = ConfigurationParameter.TYPE_FLOAT;

    public AnnotatorParameterWrapper(ConfigurationParameter config,AnalysisEngineDescription desc) {
        _config = config;
        _underlyingEngine = desc;
    }

    public String get_name() {
        return _config.getName();
    }

    public String get_description() {
        return _config.getDescription();
    }

    public boolean get_mandatory() {
        return _config.isMandatory();
    }

    public boolean get_multivalued() {
        return _config.isMultiValued();
    }

    public String get_type() {
        return _config.getType();
    }

    public <T>T get_value() {
        for(NameValuePair v : _underlyingEngine.getAnalysisEngineMetaData().getConfigurationParameterSettings().getParameterSettings()) {
            if(v.getName().equals(_config.getName())) {
                return (T)v.getValue();
            }
        }
        return null;
    }

    public void set_value(Object value) {
        _underlyingEngine.getAnalysisEngineMetaData().getConfigurationParameterSettings().setParameterValue(_config.getName(),value);
    }

    public void debug_print() {
        debug_print(0);
    }

    public void debug_print(int offset) {
        String soff="";
        for(int i = 0; i < offset; i++) {
            soff+=" ";
        }
        System.out.printf(soff+"Name: %s\n",_config.getName());
        System.out.printf(soff+"Description: %s\n",_config.getDescription());
        System.out.printf(soff+"Type: %s\n",_config.getType());
        System.out.printf(soff+"Mandatory: %s\n",String.valueOf(_config.isMandatory()));
        System.out.printf(soff+"Multivalued: %s\n",String.valueOf(_config.isMultiValued()));
        ConfigurationParameter param = _config;
        Object result = get_value();
        switch (param.getType()) {
            case ConfigurationParameter.TYPE_FLOAT:
                if (param.isMultiValued()) {
                    String serialized = "[";
                    if(result!=null) {
                        for (float inner : (float[]) result) {
                            serialized += String.valueOf(inner);
                        }
                    }
                    serialized += "]";
                    System.out.println(soff+"Value: " + serialized);
                } else {
                    System.out.println(soff+"Value: " + String.valueOf((float) result));
                }
                break;
            case ConfigurationParameter.TYPE_STRING:
                if (param.isMultiValued()) {
                    String serialized = "[";
                    if(result!=null) {
                        for (String inner : (String[]) result) {
                            serialized += inner;
                        }
                    }
                    serialized += "]";
                    System.out.println(soff+"Value: " + serialized);
                } else {
                    System.out.println(soff+"Value: " + (String) result);
                }
                break;
            case ConfigurationParameter.TYPE_BOOLEAN:
                if (param.isMultiValued()) {
                    String serialized = "[";
                    if(result!=null) {
                        for (Boolean inner : (Boolean[]) result) {
                            serialized += String.valueOf(inner);
                        }
                    }
                    serialized += "]";
                    System.out.println(soff+"Value: " + serialized);
                } else {
                    System.out.println(soff+"Value: " + String.valueOf((Boolean) result));
                }
                break;
            case ConfigurationParameter.TYPE_INTEGER:
                if (param.isMultiValued()) {
                    String serialized = "[";
                    if(result!=null) {
                        for (Integer inner : (Integer[]) result) {
                            serialized += String.valueOf(inner);
                        }
                    }
                    serialized += "]";
                    System.out.println(soff+"Value: " + serialized);
                } else {
                    System.out.println(soff+"Value: " + String.valueOf((Integer) result));
                }
                break;
            default:
                System.out.println("Unkown type: " + param.getType()+". Skipping printing...");
        }
    }
}
