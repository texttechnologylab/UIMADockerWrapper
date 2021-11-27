package DockerInterface.remote;

import DockerInterface.DockerWrappedEnvironment;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.metadata.SofaMapping;
import org.apache.uima.cas.*;
import org.apache.uima.cas.impl.*;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.*;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.hucompute.reproannotationnlp.ReproducibleAnnotation;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.bubelich.jBaseZ85;

import java.util.zip.CRC32;


public class InContainerEngineProcessor implements HttpHandler {
    public TypeSystemDescription _sys;
    public AnalysisEngine _analysis_engine;
    public AnalysisEngineDescription _analysis_engine_desc;
    public DockerWrappedEnvironment _configuration;
    public String _compressed;
    public String _cfg_string;
    public long _cfg_hash;
    public SofaMapping[] _mappings;
    public TypeSystem _last_used_typesystem;
    public JCas _runner;
    public XCASDeserializer _deserializer;

    public InContainerEngineProcessor(String configuration) throws UIMAException, IOException {
        try {
            _runner = JCasFactory.createJCas();
            _last_used_typesystem = _runner.getTypeSystem();
            _cfg_string = configuration;
            _deserializer = new XCASDeserializer(_last_used_typesystem);
            _configuration = DockerWrappedEnvironment.from(_cfg_string);
            if(!_configuration.get_compression().equals("")) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                CompressorOutputStream cos = new CompressorStreamFactory()
                        .createCompressorOutputStream(_configuration.get_compression(), out);
                IOUtils.copy(new ByteArrayInputStream(_cfg_string.getBytes(StandardCharsets.UTF_8)), cos);
                cos.close();
                _compressed = jBaseZ85.encode(out.toByteArray());
            }
            else {
                _compressed = jBaseZ85.encode(_cfg_string.getBytes(StandardCharsets.UTF_8));
            }

            CRC32 crc = new CRC32();
            byte[] bytes = _cfg_string.getBytes(StandardCharsets.UTF_8);
            crc.update(bytes,0,bytes.length);
            _cfg_hash = crc.getValue();
            String tempanno = Files.createTempFile("reproanno", ".xml").toFile().getAbsolutePath();
            Files.write(Paths.get(tempanno), _configuration.get_engine_string_description().getBytes(StandardCharsets.UTF_8));
            _analysis_engine_desc = AnalysisEngineFactory.createEngineDescriptionFromPath(tempanno);
            _mappings = _analysis_engine_desc.getSofaMappings();
            _analysis_engine = null;
        } catch (InvalidXMLException e) {
            e.printStackTrace();
            throw new ResourceInitializationException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResourceInitializationException(e);
        } catch (CompressorException e) {
            e.printStackTrace();
            throw new ResourceInitializationException(e);
        } catch (SAXException e) {
            e.printStackTrace();
            throw new ResourceInitializationException(e);
        }
    }

    public void destroy() {
        if(_analysis_engine!=null) {
            _analysis_engine.destroy();
        }
    }

    public AnalysisEngine get_analysis_engine() throws AnalysisEngineProcessException {
        if(_analysis_engine == null) {
            try {
                _analysis_engine = AnalysisEngineFactory.createEngine(_analysis_engine_desc);
            } catch (ResourceInitializationException e) {
                throw new AnalysisEngineProcessException(e);
            }
        }
        return _analysis_engine;
    }

    public void process(JCas aJCas) throws AnalysisEngineProcessException {
        _analysis_engine = get_analysis_engine();
        _analysis_engine.process(aJCas);
        ReproducibleAnnotation ann;
        if(!_configuration.get_reproducible_annotation_target_view().equals("")) {
            try {
                ann = new ReproducibleAnnotation(aJCas.getView(_configuration.get_reproducible_annotation_target_view()));
            } catch (CASException e) {
                e.printStackTrace();
                throw new AnalysisEngineProcessException(e);
            }
        }
        else {
            ann = new ReproducibleAnnotation(aJCas);
        }
        ann.setConfiguration(_compressed);
        ann.setConfiguration_hash(_cfg_hash);
        ann.setCompression(_configuration.get_compression());
        ann.setTimestamp(System.currentTimeMillis());
        ann.addToIndexes();
        return;
    }

    public String cas_to_xmi(JCas jcas) throws IOException, SAXException {
        org.apache.commons.io.output.ByteArrayOutputStream out = new org.apache.commons.io.output.ByteArrayOutputStream();
        XCASSerializer ser = new XCASSerializer(jcas.getTypeSystem());
        XMLSerializer xmlSer = new XMLSerializer(out, false);
        ser.serialize(jcas.getCas(), xmlSer.getContentHandler());
        String result = new String(out.toByteArray(), "UTF-8");
        out.close();
        return result;
    }

    public void print_cas(JCas jcas) throws IOException, SAXException {
        String result = cas_to_xmi(jcas);

        System.out.println(result);
        System.out.println(result.length());
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            if(t.getRequestURI().toString().contains("/set_typesystem")) {
                Path tmpfile = Files.createTempFile("reproanno",".xml");
                Files.write(tmpfile,org.apache.commons.io.IOUtils.toByteArray(t.getRequestBody()));
                TypeSystemDescription desc = TypeSystemDescriptionFactory.createTypeSystemDescriptionFromPath(tmpfile.toAbsolutePath().toString());
                JCas new_cas = JCasFactory.createJCas(desc);
                _runner = new_cas;
                t.sendResponseHeaders(200,-1);
                return;
            }
            _runner.reset();
            XmiSerializationSharedData sharedData = new XmiSerializationSharedData();
            XmiCasDeserializer.deserialize(t.getRequestBody(),_runner.getCas(),true,sharedData);
            Marker a = _runner.getCas().createMarker();
            process(_runner);


            t.sendResponseHeaders(200, 0);
            OutputStream response = t.getResponseBody();
            XmiCasSerializer.serialize(_runner.getCas(), null,response,false,sharedData,a,true);
            t.getResponseBody().close();
            return;
        } catch (AnalysisEngineProcessException e) {
            e.printStackTrace();
            e.printStackTrace(pw);
        } catch(Exception e) {
            e.printStackTrace();
            e.printStackTrace(pw);
        }
        String error = sw.toString();

        t.sendResponseHeaders(500, error.length());
        OutputStream os = t.getResponseBody();
        os.write(error.getBytes());
        os.close();
    }
}
