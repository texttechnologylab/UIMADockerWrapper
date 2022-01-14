# Skip already annotated
This module will prevent the exact same configuration to run multiple times on a JCas document. It will compare a hash calculated from the reproducible annotation against the hash of the current container. If the hash matches the whole configuration is compared to overcome the problem of hash collision. If the configuration match the annotator is not run again on this JCas document. The underlying assumption is of course that an annotator will not add more annotations if the annotations all exist.

```java
DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
  .default_config()
  .with_run_in_container(true)
  .with_container_autoremove(true)
  .with_module(DockerWrapperModuleSkipAlreadyAnnotated.class);
```
