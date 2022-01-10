package org.hucompute.uimadockerwrapper.remote;

import org.hucompute.uimadockerwrapper.DockerWrappedEnvironment;
import org.hucompute.uimadockerwrapper.util.DockerWrapperUtil;
import org.hucompute.uimadockerwrapper.util.InputOutputBuffer;
import com.bubelich.jBaseZ85;
import com.sun.net.httpserver.Headers;
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
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.*;
import org.hucompute.uimadockerwrapper.ReproducibleAnnotation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
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
    public InputOutputBuffer _buffer;

    public InContainerEngineProcessor(String configuration) throws UIMAException, IOException {
        try {
            _buffer = new InputOutputBuffer();
            _cfg_string = configuration;
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
            if(_analysis_engine_desc.isPrimitive()) {
                _runner = JCasFactory.createJCas();
            }
            else {
                _runner = JCasFactory.createJCas();
            }
            _last_used_typesystem = _runner.getTypeSystem();
            _deserializer = new XCASDeserializer(_last_used_typesystem);
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

    public void from_socket(Socket socket) throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            _runner.reset();
            byte integer[] = new byte[4];
            socket.getInputStream().read(integer);
            int size = ByteBuffer.wrap(integer).getInt();
            if(size<0) {
                throw new SocketException();
            }
            byte needed[] = new byte[size];

            int totalsize = 0;
            while(totalsize<size) {
                totalsize+=socket.getInputStream().read(needed, totalsize,size-totalsize);
            }

            XmiSerializationSharedData sharedData = new XmiSerializationSharedData();
            XmiCasDeserializer.deserialize(new ByteArrayInputStream(needed),_runner.getCas(),true,sharedData);
            Marker a = _runner.getCas().createMarker();
            process(_runner);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            XmiCasSerializer.serialize(_runner.getCas(), null,buffer,false,sharedData,a,true);

            byte[] bytes = ByteBuffer.allocate(4).putInt(buffer.toByteArray().length).array();
            socket.getOutputStream().write(bytes);
            socket.getOutputStream().write(buffer.toByteArray());
            socket.getOutputStream().flush();
            return;
        } catch (AnalysisEngineProcessException e) {
            e.printStackTrace();
            e.printStackTrace(pw);
        } catch(Exception e) {
            e.printStackTrace();
            e.printStackTrace(pw);
        }
        String error = sw.toString();

        socket.getOutputStream().write(error.getBytes(StandardCharsets.UTF_8).length);
        socket.getOutputStream().write(error.getBytes(StandardCharsets.UTF_8));
    }

    List<String> create_header(String val) {
        List<String> lst = new ArrayList();
        lst.add(val);
        return lst;
    }

    JSONObject add_all_subtypes(TypeDescription root, List<TypeDescription> tocheck, Map<String,List<TypeDescription>> supertypes) {
        JSONObject obj = new JSONObject();
        JSONArray children = new JSONArray();
        if(tocheck!=null) {
            for (TypeDescription d : tocheck) {
                if (supertypes.containsKey(d.getName())) {
                    children.put(add_all_subtypes(d, supertypes.get(d.getName()), supertypes));
                }
            }
        }
        obj.put("name",root.getName());
        obj.put("description",root.getDescription());
        obj.put("children",children);
        return obj;
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
                _last_used_typesystem = new_cas.getTypeSystem();
                _runner = new_cas;
                Headers header = t.getResponseHeaders();
                header.add("Connection", "Keep-Alive");
                header.add("Keep-Alive", "timeout=20 max=100");
                t.sendResponseHeaders(200,-1);
                return;
            }
            else if(t.getRequestURI().toString().contains("/rest/engine")) {
                String result = DockerWrapperUtil.analysisEngineDescriptionToJson(_analysis_engine_desc).toString();
                System.out.println(result);
                t.getResponseHeaders().put("Content-type",create_header("application/json"));

                t.getResponseHeaders().put("Access-Control-Allow-Origin",create_header("*"));
                t.getResponseHeaders().put("Access-Control-Allow-Credentials",create_header( "true"));
                t.getResponseHeaders().put("Access-Control-Allow-Methods",create_header( "GET,HEAD,OPTIONS,POST,PUT"));
                t.getResponseHeaders().put("Access-Control-Allow-Headers",create_header( "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
                t.sendResponseHeaders(200,result.getBytes(StandardCharsets.UTF_8).length);
                t.getResponseBody().write(result.getBytes(StandardCharsets.UTF_8));
                t.getResponseBody().close();
                return;
            } else if(t.getRequestURI().toString().contains("/rest/typesystem")) {
                t.getResponseHeaders().put("Content-type",create_header("application/json"));

                t.getResponseHeaders().put("Access-Control-Allow-Origin",create_header("*"));
                t.getResponseHeaders().put("Access-Control-Allow-Credentials",create_header( "true"));
                t.getResponseHeaders().put("Access-Control-Allow-Methods",create_header( "GET,HEAD,OPTIONS,POST,PUT"));
                t.getResponseHeaders().put("Access-Control-Allow-Headers",create_header( "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
                t.sendResponseHeaders(200,0);

                t.getResponseBody().write(DockerWrapperUtil.typesystemToJSON(TypeSystemUtil.typeSystem2TypeSystemDescription(_last_used_typesystem)).toString().getBytes(StandardCharsets.UTF_8));
                t.getResponseBody().close();
                return;
            } else if(t.getRequestURI().toString().contains("/rest/dockerfile")) {
                String result = _configuration.get_dockerfile();
                System.out.println(result);
                t.getResponseHeaders().put("Content-type",create_header("text/plain"));

                t.getResponseHeaders().put("Access-Control-Allow-Origin",create_header("*"));
                t.getResponseHeaders().put("Access-Control-Allow-Credentials",create_header( "true"));
                t.getResponseHeaders().put("Access-Control-Allow-Methods",create_header( "GET,HEAD,OPTIONS,POST,PUT"));
                t.getResponseHeaders().put("Access-Control-Allow-Headers",create_header( "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
                t.sendResponseHeaders(200,result.getBytes(StandardCharsets.UTF_8).length);
                t.getResponseBody().write(result.getBytes(StandardCharsets.UTF_8));
                t.getResponseBody().close();
                return;
            } else if(t.getRequestURI().toString().contains("/rest/resources")) {
                String result = _configuration.getRessourcesBase64().toString();
                t.getResponseHeaders().put("Content-type",create_header("application/json"));

                t.getResponseHeaders().put("Access-Control-Allow-Origin",create_header("*"));
                t.getResponseHeaders().put("Access-Control-Allow-Credentials",create_header( "true"));
                t.getResponseHeaders().put("Access-Control-Allow-Methods",create_header( "GET,HEAD,OPTIONS,POST,PUT"));
                t.getResponseHeaders().put("Access-Control-Allow-Headers",create_header( "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
                t.sendResponseHeaders(200,result.getBytes(StandardCharsets.UTF_8).length);
                t.getResponseBody().write(result.getBytes(StandardCharsets.UTF_8));
                t.getResponseBody().close();
                return;
            }
            _runner.reset();
            XmiSerializationSharedData sharedData = new XmiSerializationSharedData();
            XmiCasDeserializer.deserialize(t.getRequestBody(),_runner.getCas(),true,sharedData);
            Marker a = _runner.getCas().createMarker();
            process(_runner);

            Headers header = t.getResponseHeaders();
            header.add("Connection", "Keep-Alive");
            header.add("Keep-Alive", "timeout=20 max=100");
            t.sendResponseHeaders(200, 0);
            OutputStream response = t.getResponseBody();
            XmiCasSerializer.serialize(_runner.getCas(), null,response,false,sharedData,a,true);
            response.close();
            return;
        } catch (AnalysisEngineProcessException e) {
            e.printStackTrace();
            e.printStackTrace(pw);
        } catch(Exception e) {
            e.printStackTrace();
            e.printStackTrace(pw);
        }
        String error = sw.toString();

        Headers header = t.getResponseHeaders();
        header.add("Connection", "Keep-Alive");
        header.add("Keep-Alive", "timeout=20 max=100");
        t.sendResponseHeaders(500, error.length());
        OutputStream os = t.getResponseBody();
        os.write(error.getBytes());
        os.close();
    }
}
