package DockerInterface.base_env;

public class DockerBaseJavaEnv extends DockerBaseEnvImpl {
    public DockerBaseJavaEnv() {
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt update -y");
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt install -y maven");
        add_raw_dockercmd("ADD ./pom.xml /pom.xml");
        add_raw_dockercmd("ADD ./cfg /configuration.reproanno");
        add_raw_dockercmd("ENV CLASSPATH target/dependencies/*;base.jar");
        add_raw_dockercmd("ADD https://kava-i.de/download/reproannotationnlp-1.0-SNAPSHOT-jar-with-dependencies.jar base.jar");
        add_raw_dockercmd("RUN mvn dependency:copy-dependencies");
    }

    public DockerBaseJavaEnv(String java_base) {
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt update -y");
        add_raw_dockercmd(String.format("RUN DEBIAN_FRONTEND=noninteractive apt install -y %s",java_base));
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt install -y maven");
        add_raw_dockercmd("ADD ./pom.xml /pom.xml");
        add_raw_dockercmd("ADD ./cfg /configuration.reproanno");
        add_raw_dockercmd("ENV CLASSPATH target/dependencies/*;base.jar");
        add_raw_dockercmd("ADD https://kava-i.de/download/reproannotationnlp-1.0-SNAPSHOT-jar-with-dependencies.jar base.jar");
        add_raw_dockercmd("RUN mvn dependency:copy-dependencies");
    }

    public void enable_python() {
        add_raw_dockercmd("RUN export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64");
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt install -y python pip");
        add_raw_dockercmd("RUN apt install -y openjdk-11-jdk");
        add_raw_dockercmd("RUN JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 pip3 install --no-input jep==4.0.0");
        add_raw_dockercmd("RUN pip3 install --no-input dkpro-cassis==0.6.1");
        add_raw_dockercmd("RUN cp $(find . -name \"libjep.so\") /usr/lib");
    }

    public String get_execute_command() {
        return "CMD java -cp \"/target/dependency/*:base.jar\" DockerInterface.remote.InDockerHttpServer";
    }
}
