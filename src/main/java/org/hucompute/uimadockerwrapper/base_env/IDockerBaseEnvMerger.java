package org.hucompute.uimadockerwrapper.base_env;

public interface IDockerBaseEnvMerger {
    IDockerBaseEnv merge(IDockerBaseEnv a, IDockerBaseEnv b) throws IllegalArgumentException;
}
