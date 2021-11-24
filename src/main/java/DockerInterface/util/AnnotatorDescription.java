package DockerInterface.util;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.metadata.SofaMapping;
import org.apache.uima.fit.factory.SofaMappingFactory;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.NameValuePair;
import org.apache.uima.util.InvalidXMLException;

import java.util.*;
import java.util.stream.Collectors;

public class AnnotatorDescription {
    private AnalysisEngineDescription _engine;
    private Map<String,AnnotatorParameterWrapper> _configParameters;

    public AnnotatorDescription(AnalysisEngineDescription desc) {
        _engine = desc;
        if(!_engine.isPrimitive()) {
            throw new IllegalArgumentException("Engine must be primitive!");
        }
        _configParameters = new HashMap<String,AnnotatorParameterWrapper>();
        for(ConfigurationParameter param : _engine.getAnalysisEngineMetaData().getConfigurationParameterDeclarations().getConfigurationParameters()) {
            _configParameters.put(param.getName(), new AnnotatorParameterWrapper(param,_engine));
        }
    }

    public AnalysisEngineDescription get_underlying_engine() {
        return _engine;
    }

    public String get_name() throws IllegalArgumentException {
        if(_engine.isPrimitive()) {
            return _engine.getAnnotatorImplementationName();
        }
        throw new IllegalArgumentException();
    }

    public AnnotatorParameterWrapper get_parameter(String value) {
        return _configParameters.get(value);
    }

    public List<AnnotatorParameterWrapper> get_parameters() {
        List<AnnotatorParameterWrapper> lst = new LinkedList<>();
        for(Map.Entry<String,AnnotatorParameterWrapper> entry : _configParameters.entrySet()) {
            lst.add(entry.getValue());
        }
        return lst;
    }
}
