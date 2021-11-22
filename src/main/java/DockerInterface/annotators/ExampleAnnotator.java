package DockerInterface.annotators;

import org.apache.uima.UIMAException;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;

import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.util.TypeSystemUtil;


public class ExampleAnnotator extends JCasAnnotator_ImplBase {
    public static final String PARAM_PIPELINE_CONFIGURATION = "pipeline_configuration";
    @ConfigurationParameter(name = PARAM_PIPELINE_CONFIGURATION, mandatory = true,defaultValue = "")
    private String whatever;

    @Override
    public void initialize(UimaContext aContext) throws ResourceInitializationException {
        super.initialize(aContext);
    }

    public String get_pipeline_prefix() {
        return whatever;
    }

    public void process(JCas aJCas) throws AnalysisEngineProcessException {
        System.out.printf("RUNNING IN VIEW: %s\n",aJCas.getViewName());
        for(TOP t : JCasUtil.select(aJCas,TOP.class)) {
            System.out.printf("Got Annotation: %s\n",t.getType().getName());
        }
    }
}