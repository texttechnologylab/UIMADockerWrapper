package DockerInterface.base_env;

import java.util.List;
import java.util.function.Function;

public interface IDockerBaseEnv {
    String get_base_img();
    List<String> get_install_requirements();
    String get_execute_command();

    void add_install_requirement(String ...requirement);
    void add_raw_dockercmd(String line);
    void set_execute_command(String command);
    void set_base_img(String baseImg);

    String get_assembled_dockerfile();
    IDockerBaseEnv merge_environments(IDockerBaseEnv env) throws IllegalArgumentException;
    IDockerBaseEnv merge_environments(IDockerBaseEnv env, IDockerBaseEnvMerger merger) throws IllegalArgumentException;
}
