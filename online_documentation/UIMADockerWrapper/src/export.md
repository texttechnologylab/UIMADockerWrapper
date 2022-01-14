# Export
When the AnalysisEngine is destroyed all containers are stopped. To export the container before stopping it to a new image with a given tag and a given repository one can use the method provided below. Notice this will only work for standalone containers and not for Docker Swarm containers.

```
DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
  .default_config()
  .with_run_in_container(true)
  .with_export_to_new_image("alex_experiment","v1.1");
```
Exports the container at the end of its lifetime to an image with the name ```alex_experiment``` and ```v1.1``` as version.
