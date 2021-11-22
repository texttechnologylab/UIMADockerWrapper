package DockerInterface.base_env;

public interface IDockerBaseEnvMerger {
    IDockerBaseEnv merge(IDockerBaseEnv a, IDockerBaseEnv b) throws IllegalArgumentException;
}
