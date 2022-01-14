# Autoremove
This chapter shows how to automatically remove the container after it has been stopped. When the AnalysisEngine is destroyed all containers are stopped and all services are removed. The container will persist after being stopped when not using this option.

```
// The last line activates the functionality
DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
  .default_config()
  .with_run_in_container(true)
  .with_container_autoremove(true);
```
