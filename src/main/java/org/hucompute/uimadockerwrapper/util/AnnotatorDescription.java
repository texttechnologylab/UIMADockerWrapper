package org.hucompute.uimadockerwrapper.util;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.resource.metadata.ConfigurationParameter;
import org.apache.uima.resource.metadata.NameValuePair;

import java.util.*;

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

    public Object get_unlisted_parameter(String key) {
        AnnotatorParameterWrapper wrp = get_parameter(key);
        if(wrp==null) {
            Object result = _engine.getAnalysisEngineMetaData().getConfigurationParameterSettings().getParameterValue(key);
            return result;
        }
        return wrp.get_value();
    }

    public List<String> get_unlisted_parameters() {
        List<String> unlisted = new LinkedList<>();
        for(NameValuePair pair : _engine.getAnalysisEngineMetaData().getConfigurationParameterSettings().getParameterSettings()) {
            if(get_parameter(pair.getName()) == null) {
                unlisted.add(pair.getName());
            }
        }
        return unlisted;
    }

    public List<AnnotatorParameterWrapper> get_parameters() {
        List<AnnotatorParameterWrapper> lst = new LinkedList<>();
        for(Map.Entry<String,AnnotatorParameterWrapper> entry : _configParameters.entrySet()) {
            lst.add(entry.getValue());
        }
        return lst;
    }
}
