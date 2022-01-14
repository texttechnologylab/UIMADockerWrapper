# Modules
The library provides the possibility to add modules which will be called for specific events. The definition of modules looks like this:

```java
public interface IDockerWrapperModule {
    void onInitialize(UimaContext ctx, String configuration) throws ResourceInitializationException;
    boolean onBeforeProcess(JCas jcas) throws AnalysisEngineProcessException;
    void onAfterProcess(JCas jcas) throws AnalysisEngineProcessException;
    void onDestroy();
}
```
Notice that modules are executed **outside** of the docker container. Since the modules are not serialized and annotated into the JCas document they are not part of the reproducible workflow. The intention for modules is therefore an observational one. It is intended to empower the developer to print statistics or other important information but the JCas document should not be changed.

- ```onInitialize``` is called right after the initialization of the docker container is done.
- ```onBeforeProcess``` is called before starting to send the JCas document to the container.
- ```onAfterProcess``` is called after the JCas document has been processed by the container.
- ```onDestroy``` is called before the container is stopped.

An example module is provided here [skip already annotated module](./skip_annotated.md).
