package DockerInterface.remote;

import DockerInterface.remote.InContainerEngineProcessor;
import com.sun.net.httpserver.HttpServer;
import org.apache.uima.UIMAException;
import org.apache.uima.cas.CASException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class InDockerHttpServer {
    static public void main(String []args) throws IOException, UIMAException {
        HttpServer server = HttpServer.create(new InetSocketAddress(9714), 0);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                server.stop(0);
            }
        });
        server.createContext("/process", new InContainerEngineProcessor(new String(Files.readAllBytes(Paths.get("configuration.reproanno")), StandardCharsets.UTF_8)));
        server.setExecutor(null);
        server.start();
        System.out.printf("Server started on port %d\n",9714);
    }
}
