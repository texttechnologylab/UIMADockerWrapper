package org.hucompute.uimadockerwrapper.util;

//This whole file is taken from https://github.com/texttechnologylab/textimager-client/blob/master/src/main/java/org/hucompute/textimager/util/SSHUtils.java
//and modified a bit.
import java.io.File;
        import java.io.IOException;
        import java.io.InputStream;
        import java.nio.charset.Charset;
        import java.nio.file.Paths;
        import java.util.Arrays;
        import java.util.Properties;
        import java.util.Map.Entry;

import net.schmizz.sshj.xfer.scp.SCPFileTransfer;
import org.apache.commons.io.IOUtils;

        import joptsimple.internal.Strings;
        import net.schmizz.sshj.SSHClient;
        import net.schmizz.sshj.connection.channel.direct.Session;
        import net.schmizz.sshj.connection.channel.direct.Session.Command;
        import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
        import net.schmizz.sshj.userauth.keyprovider.KeyProvider;

public class SSHUtils {
    public static String RSA_KEY_PATH;
    public static String SERVER_URL;
    public static int SERVER_SSH_PORT;
    public static String SSH_USER;


    static {

        RSA_KEY_PATH = Paths.get("/home/alexander/.ssh","id_rsa").toAbsolutePath().toString();
        SERVER_URL = "127.0.0.1";
        SERVER_SSH_PORT = 22222;
        SSH_USER = "ducc";

    }


    public static String runRemoteCommand(String ... cmds) throws IOException{
        System.out.println(Arrays.toString(cmds));
        System.out.println(SERVER_URL + "\t" + SERVER_SSH_PORT);
        System.out.println(SSH_USER);

        final SSHClient ssh = new SSHClient();
        ssh.addHostKeyVerifier(new PromiscuousVerifier());
        ssh.connect(SERVER_URL, SERVER_SSH_PORT);
        ssh.authPassword("ducc","password");
        String command = Strings.join(cmds, "; ");

        Session session = null;
        try {
            session = ssh.startSession();
            System.out.println("======");
            System.out.println(command);
            System.out.println("======");
            final Command cmd = session.exec(command);
            System.out.println(IOUtils.toString(cmd.getErrorStream(),Charset.defaultCharset()));

            return (IOUtils.toString(cmd.getInputStream(),Charset.defaultCharset()));
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (IOException e) {
                // Do Nothing
            }
            ssh.disconnect();
        }
    }

    public static long sshDuccJobSubmit(Properties prop) throws IOException{
        StringBuilder params = new StringBuilder();
        for (Entry<Object, Object> entry : prop.entrySet()) {
            params.append("--" + entry.getKey()).append(" ").append(entry.getValue()).append(" ");
        }


        String command = ("cd /home/ducc/ducc/apache-uima-ducc/bin && ./ducc_submit "+ params.toString()+"").replace(System.lineSeparator(), " ").replace("\n", " ");
        String output = runRemoteCommand(command);
        System.out.println(command);
        System.out.println(output);
        return Long.parseLong(output.replaceAll(".*?Job (.*?) submitted.*", "$1").trim());
    }

    //TODO: Fehler abfangen
    public static boolean sshDuccJobCancel(long jobId) throws IOException{
        String output = runRemoteCommand(
                "cd /home/ducc/ducc/apache-uima-ducc/bin",
                "./ducc_cancel --id "+ jobId)
                .replace(System.lineSeparator(), " ")
                .replace("\n", " ");
        System.out.println(output);
        return true;
    }
}