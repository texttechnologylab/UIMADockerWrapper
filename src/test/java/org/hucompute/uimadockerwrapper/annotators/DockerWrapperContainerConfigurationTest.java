package org.hucompute.uimadockerwrapper.annotators;

import org.hucompute.uimadockerwrapper.DockerWrapperContainerConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DockerWrapperContainerConfigurationTest {
    @Test
    void DockerWrapperBasicConfiguration() {
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration.default_config();

        //Check safe default values
        Assertions.assertEquals(cfg.get_unsafe_running_container_id(),"");
        Assertions.assertEquals(cfg.get_unsafe_map_docker_daemon(),false);
    }

    @Test
    void DockerWrapperBasicConfigurationExtended() {
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration.default_config();

        //Check safe default values
        Assertions.assertEquals(cfg.get_containername(),"");
        Assertions.assertEquals(cfg.get_reuse_container(),false);
        Assertions.assertEquals(cfg.get_module_configurations().size(),0);
        Assertions.assertEquals(cfg.get_additional_modules().size(),0);
        Assertions.assertEquals(cfg.get_autoremove(),true);
        Assertions.assertEquals(cfg.get_use_gpu(),true);
        Assertions.assertEquals(cfg.get_export_name(),"");
    }
}
