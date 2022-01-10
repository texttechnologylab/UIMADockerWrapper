package org.hucompute.uimadockerwrapper.base_env;

public class DockerBaseJavaEnv extends DockerBaseEnvImpl {
    public DockerBaseJavaEnv() {
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt update -y");
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt install -y maven bzip2 locales");
        add_raw_dockercmd("ENV LANG=en_US.UTF-8");
        add_raw_dockercmd("RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && locale-gen");
        add_raw_dockercmd("ENV LANG en_US.UTF-8");
        add_raw_dockercmd("ENV LANGUAGE en_US:en");
        add_raw_dockercmd("ENV LC_ALL en_US.UTF-8");

        add_raw_dockercmd("ADD ./pom.xml /pom.xml");
        add_raw_dockercmd("ADD https://kava-i.de/download/framework.tar.bz2 /base.tar.bz2");
        add_raw_dockercmd("RUN mkdir framework");
        add_raw_dockercmd("RUN tar -xf /base.tar.bz2 -C framework");
        add_raw_dockercmd("RUN mvn dependency:copy-dependencies");
        add_raw_dockercmd("RUN cd framework && mvn compile package && mvn dependency:build-classpath -Dmdep.outputFile=mvn_classpath.txt");
        add_raw_dockercmd("ADD ./cfg /framework/configuration.reproanno");
    }

    public DockerBaseJavaEnv(String java_base) {
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt update -y");
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt install -y maven bzip2 locales");
        add_raw_dockercmd("ENV LANG=en_US.UTF-8");
        add_raw_dockercmd("RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && locale-gen");
        add_raw_dockercmd("ENV LANG en_US.UTF-8");
        add_raw_dockercmd("ENV LANGUAGE en_US:en");
        add_raw_dockercmd("ENV LC_ALL en_US.UTF-8");
        add_raw_dockercmd(String.format("RUN DEBIAN_FRONTEND=noninteractive apt install -y %s",java_base));
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt install -y maven bzip2");
        add_raw_dockercmd("ADD ./pom.xml /pom.xml");
        add_raw_dockercmd("ADD https://kava-i.de/download/framework.tar.bz2 /base.tar.bz2");
        add_raw_dockercmd("RUN mkdir framework");
        add_raw_dockercmd("RUN tar -xf /base.tar.bz2 -C -C framework");
        add_raw_dockercmd("RUN mvn dependency:copy-dependencies");
        add_raw_dockercmd("RUN cd framework && mvn compile package && mvn -q exec:exec -Dexec.executable=echo -Dexec.args=\"%classpath\" > mvn_classpath.txt");
        add_raw_dockercmd("ADD ./cfg /framework/configuration.reproanno");
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
        return "CMD cd framework && java -cp \"target/classes:$(cat mvn_classpath.txt):/target/dependency/*:target/uimadockerwrapper-0.1.jar\" org.hucompute.uimadockerwrapper.remote.InDockerHttpServer";
    }
}
