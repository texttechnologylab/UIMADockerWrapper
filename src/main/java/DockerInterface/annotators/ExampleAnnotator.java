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

    public static final String PARAM_ASYNC_SCALEOUT_MAX_DEPLOYMENTS = "TEXTTECHNOLOGYLAB_ASYNC_SCALEOUT_SIZE";
    @ConfigurationParameter(name=PARAM_ASYNC_SCALEOUT_MAX_DEPLOYMENTS, mandatory = true, defaultValue = "4")
    private int _async_scalout;

    @Override
    public void initialize(UimaContext aContext) throws ResourceInitializationException {
        super.initialize(aContext);
        System.out.println("Initialising annotator!");
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