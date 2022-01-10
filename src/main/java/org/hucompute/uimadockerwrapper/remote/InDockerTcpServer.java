package org.hucompute.uimadockerwrapper.remote;

import org.apache.uima.UIMAException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class InDockerTcpServer {
    static public void main(String []args) throws IOException, UIMAException {
        ServerSocket serverSocket = new ServerSocket(9714);
        InContainerEngineProcessor proc = new InContainerEngineProcessor(new String(Files.readAllBytes(Paths.get("configuration.reproanno")), StandardCharsets.UTF_8));
        System.out.printf("Server started on port %d\n", 9714);
        while(true) {
            Socket clientSocket = serverSocket.accept();
            try {
                while (clientSocket.isConnected()&&!clientSocket.isClosed()) {
                    proc.from_socket(clientSocket);
                }
            }
            catch (SocketException excp) {
                if(!clientSocket.isClosed()) {
                    clientSocket.close();
                }
            }
        }
    }
}
