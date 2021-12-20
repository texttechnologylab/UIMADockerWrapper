package DockerInterface.base_env;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DockerBaseEnvImpl implements IDockerBaseEnv {
    List<String> _installs;
    String _execute_command;
    String _base_img;

    public DockerBaseEnvImpl() {
        _installs = new LinkedList<String>();
        _execute_command = "CMD /bin/bash";
        _base_img = "FROM ubuntu:20.04";
    }

    public String get_base_img() {
        return _base_img;
    }

    public List<String> get_install_requirements() {
        return _installs;
    }

    public String get_execute_command() {
        return _execute_command;
    }

    public String get_assembled_dockerfile() {
        String dockerfile = get_base_img();
        dockerfile+="\n";
        for(String installs : get_install_requirements()) {
            dockerfile+=installs;
            dockerfile+="\n";
        }
        return dockerfile+get_execute_command();
    }

    public void set_execute_command(String command) {
        _execute_command = command;
    }

    public void set_base_img(String new_base) {
        _base_img = new_base;
    }

    public void add_install_requirement(String ...requ) {
        _installs.add(requ[0]);
    }

    public void add_raw_dockercmd(String line) {
        _installs.add(line);
    }

    public IDockerBaseEnv merge_environments(IDockerBaseEnv env, IDockerBaseEnvMerger merger) throws IllegalArgumentException {
        return merger.merge(this,env);
    }

    public IDockerBaseEnv merge_environments(IDockerBaseEnv env) throws IllegalArgumentException {
        if(!env.get_base_img().equals(get_base_img())) {
            throw new IllegalArgumentException();
        }
        else if(!env.get_execute_command().equals(get_execute_command())) {
            throw new IllegalArgumentException();
        }
        for(String x : env.get_install_requirements()) {
            add_install_requirement(x);
        }
        return this;
    }
}
