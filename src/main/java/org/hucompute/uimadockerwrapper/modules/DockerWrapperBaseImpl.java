package org.hucompute.uimadockerwrapper.modules;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

public class DockerWrapperBaseImpl implements IDockerWrapperModule {
    @Override
    public void onInitialize(UimaContext ctx, String configuration) throws ResourceInitializationException {
    }

    @Override
    public boolean onBeforeProcess(JCas jcas) throws AnalysisEngineProcessException {
        return true;
    }

    @Override
    public void onAfterProcess(JCas jcas) throws AnalysisEngineProcessException {
    }

    @Override
    public void onDestroy() {
    }
}
