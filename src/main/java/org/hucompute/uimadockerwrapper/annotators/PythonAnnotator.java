package org.hucompute.uimadockerwrapper.annotators;

import org.hucompute.uimadockerwrapper.DockerWrapper;
import jep.JepException;
import jep.SubInterpreter;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.SerialFormat;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import jep.Interpreter;
import org.apache.uima.util.CasIOUtils;
import org.apache.uima.util.TypeSystemUtil;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class PythonAnnotator extends JCasAnnotator_ImplBase {
    Interpreter _interpreter;

    public static final String PARAM_FILE_PATH="PARAM_PYTHON_ANNOTATOR_FILE_PATH";
    @ConfigurationParameter(name=PARAM_FILE_PATH, mandatory = true)
    String filePath;

    public static final String PARAM_CLASS_NAME="PARAM_PYTHON_CLASS_NAME";
    @ConfigurationParameter(name=PARAM_CLASS_NAME, mandatory = true)
    String className;

    public static final String PARAM_INITIALISE="PARAM_PYTHON_INITIALISE";
    @ConfigurationParameter(name=PARAM_INITIALISE, mandatory = true, defaultValue = "")
    String initialiseString;

    @Override
    public void initialize(UimaContext aContext) throws ResourceInitializationException {
        super.initialize(aContext);
        try {
            _interpreter = new SubInterpreter();
        } catch (JepException e) {
            throw new ResourceInitializationException(e);
        }
        // Import the use defined class
        _interpreter.exec("import importlib.util");
        _interpreter.exec(String.format("spec = importlib.util.spec_from_file_location(\"base_annotator\", \"%s\")",filePath));
        _interpreter.exec("foo = importlib.util.module_from_spec(spec)");
        _interpreter.exec("spec.loader.exec_module(foo)");
        _interpreter.exec(String.format("annotator = foo.%s(\"%s\")",className,initialiseString));
    }

    public void process(JCas aJCas) throws AnalysisEngineProcessException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        StringWriter type_system_xmi = new StringWriter();
        try {
            CasIOUtils.save(aJCas.getCas(),out, SerialFormat.XMI);
            TypeSystemUtil.typeSystem2TypeSystemDescription(aJCas.getTypeSystem()).toXML(type_system_xmi);
            _interpreter.exec(String.format("result = annotator.process(\"%s\",\"%s\")",DockerWrapper.escape(new String(out.toByteArray())),
                    DockerWrapper.escape(type_system_xmi.getBuffer().toString())));
            String result = (String)_interpreter.getValue("result");
            ByteArrayInputStream input = new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8));
            CasIOUtils.load(input,aJCas.getCas(), aJCas.getTypeSystem());
        } catch (IOException | SAXException e) {
            throw new AnalysisEngineProcessException(e);
        }
    }

    @Override
    public void destroy() {
        _interpreter.exec("annotator.destroy()");
        _interpreter.close();
    }
}
