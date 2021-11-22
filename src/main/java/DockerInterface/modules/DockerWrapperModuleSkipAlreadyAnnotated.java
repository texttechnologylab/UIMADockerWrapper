package DockerInterface.modules;

import DockerInterface.DockerWrappedEnvironment;
import DockerInterface.DockerWrapper;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;
import org.apache.uima.UimaContext;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.hucompute.reproannotationnlp.ReproducibleAnnotation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.CRC32;

public class DockerWrapperModuleSkipAlreadyAnnotated extends DockerWrapperBaseImpl {

    @ConfigurationParameter(name=DockerWrapper.PARAM_CFG, mandatory = true)
    private String _cfg_string;

    private String _compressed;
    private long _cfg_hash;

    @Override
    public void onInitialize(UimaContext aContext, String configuration) throws ResourceInitializationException {
        _cfg_string = (String)aContext.getConfigParameterValue(DockerWrapper.PARAM_CFG);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XZCompressorOutputStream ot = null;
        try {
            ot = new XZCompressorOutputStream(out,9);
            ot.write(_cfg_string.getBytes(StandardCharsets.UTF_8));
            ot.close();
        } catch (IOException e) {
            throw new ResourceInitializationException(e);
        }
        _compressed = Base64.getEncoder().encodeToString(out.toByteArray());
        byte []bytes = _cfg_string.getBytes(StandardCharsets.UTF_8);
        CRC32 crc = new CRC32();
        crc.update(bytes,0,bytes.length);
        _cfg_hash = crc.getValue();
    }

    @Override
    public boolean onBeforeProcess(JCas jCas) {
        for(ReproducibleAnnotation a : JCasUtil.select(jCas,ReproducibleAnnotation.class)) {
            if(a.getConfiguration_hash() == _cfg_hash) {
                if(a.getConfiguration().equals(_compressed)) {
                    return false;
                }
            }
        }
        return true;
    }
}
