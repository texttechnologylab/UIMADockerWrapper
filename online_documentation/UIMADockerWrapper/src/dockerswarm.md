# Docker Swarm
A note on docker swarm. For containers to deploy on a swarm network one needs to be a manager in this docker swarm network. The following command:

```bash
docker node ls
```

should show the current node either as **Leader** or as **Manager**.

## Additional dependencies
This example uses the TextReader from DKPro. For this to compile one needs to import the necessary library. The maven command is given below

```
<dependency>
  <groupId>org.dkpro.core</groupId>
  <artifactId>dkpro-core-io-text-asl</artifactId>
  <version>1.12.0</version>
</dependency>
```

## Source

If this is the case one can use the library to scale the used example with the docker swarm network.
This is the example from the First Steps page. In order to scale an arbitrary AnalysisEngineDescription one has to change two lines compared to the first example in this documentation.
```java
// The annotation should be made within a container. SPECIFY THE SCALEOUT HERE
// This will determine the number of replicas on each node. A more sophisticated scheduling is not
// available at the moment and this implementationw as provided as a proof
// of concept.
DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration.default_config()
  .with_run_in_container(true)
  .with_scaleout(4);

// Create the wrapped pipeline from any AnalysisEngineDescription
DockerWrappedEnvironment env = DockerWrappedEnvironment.from(
  AnalysisEngineFactory.createEngineDescription(OpenNlpSegmenter.class)
);


CollectionReaderDescription rd = CollectionReaderFactory.createReaderDescription(TextReader.class,
    TextReader.PARAM_SOURCE_LOCATION, "/path/to/some/text/files",
    TextReader.PARAM_PATTERNS, "[+]**/*.txt",
    TextReader.PARAM_LANGUAGE, "en");

// IMPORTANT: Use AsyncPipeline and a collection reader, scaling does not make sense
// for a single document therefore it is only defined for collection readers.
AsyncPipeline.run(rd,env.build(cfg),(a) -> {
                        return a;
                    });
```
The callback is called for every loaded JCas document. There is a limited JCas Pool therefore one should try to finish the processing as fast as possible in the callback. The callback is called by multiple threads, using shared variables without a synchronization primitive is unsafe!
