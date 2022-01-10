package org.hucompute.uimadockerwrapper.modules;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

public interface IDockerWrapperModule {
    void onInitialize(UimaContext ctx, String configuration) throws ResourceInitializationException;
    boolean onBeforeProcess(JCas jcas) throws AnalysisEngineProcessException;
    void onAfterProcess(JCas jcas) throws AnalysisEngineProcessException;
    void onDestroy();
}
