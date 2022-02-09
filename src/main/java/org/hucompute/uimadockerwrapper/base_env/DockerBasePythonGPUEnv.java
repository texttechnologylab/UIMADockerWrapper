package org.hucompute.uimadockerwrapper.base_env;

public class DockerBasePythonGPUEnv extends DockerBaseEnvImpl {
    public DockerBasePythonGPUEnv(String cudabase) {
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt update -y");
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt install -y maven wget");
        add_raw_dockercmd("ADD ./pom.xml /pom.xml");
        add_raw_dockercmd("ADD ./cfg /configuration.reproanno");
        add_raw_dockercmd("ENV CLASSPATH target/dependencies/*;base.jar");
        add_raw_dockercmd("ADD https://kava-i.de/download/framework2.tar.bz2 base.jar");
        add_raw_dockercmd("RUN mvn dependency:copy-dependencies");
        add_raw_dockercmd("RUN export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64");
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt install -y python pip");
        add_raw_dockercmd("RUN DEBIAN_FRONTEND=noninteractive apt install -y openjdk-11-jdk");
        add_raw_dockercmd("RUN JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 pip3 install --no-input jep==4.0.0");
        add_raw_dockercmd("RUN pip3 install --no-input dkpro-cassis==0.6.1");
        add_raw_dockercmd("RUN cp $(find . -name \"libjep.so\") /usr/lib");
        set_base_img(cudabase);
    }

    @Override
    public void add_install_requirement(String... requ) {
        String cmd = "RUN pip3 install --no-input ";
        for(int i = 0; i < requ.length; i++) {
            cmd+=requ[i];
            if(i!=requ.length-1) {
                cmd+=" ";
            }
        }
        add_raw_dockercmd(cmd);
    }

    public String get_execute_command() {
        return "CMD cd framework && java -cp \"target/classes:$(cat mvn_classpath.txt):/target/dependency/*:target/uimadockerwrapper-0.1.jar\" org.hucompute.uimadockerwrapper.remote.InDockerHttpServer";
    }
}
