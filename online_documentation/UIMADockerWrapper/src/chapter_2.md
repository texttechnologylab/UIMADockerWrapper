# First steps

This page will guide you through the process of creating a simple reproducible annotation.

## Additional dependencies
An example annotator is used to show the wrapping of an annotator which is not native to this library. The used Annotator is the OpenNlpSegmenter from the apache OpenNLP library. Below is the dependency given for the maven package manager:

```
<dependency>
  <groupId>de.tudarmstadt.ukp.dkpro.core</groupId>
  <artifactId>de.tudarmstadt.ukp.dkpro.core.opennlp-asl</artifactId>
  <version>1.10.0</version>
</dependency>
```

## Pom file
An example pom file is provided here. Due to unknown constraints the library works only for Java 8 at the moment. This is due to a runtime error, which does not seem to depend on the java version. This is a requirement which is hopefully lifted in the near future.

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>test_doc</artifactId>
    <version>1.0-SNAPSHOT</version>
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>com.github.texttechnologylab</groupId>
            <artifactId>UIMADockerWrapper</artifactId>
            <version>83daf45aaf</version>
        </dependency>
        <dependency>
            <groupId>de.tudarmstadt.ukp.dkpro.core</groupId>
            <artifactId>de.tudarmstadt.ukp.dkpro.core.opennlp-asl</artifactId>
            <version>1.10.0</version>
        </dependency>
    </dependencies>
</project>
```

## Source
This section will provide the source code with a few comments to explain the library a bit better.

```java
JCas test_c = JCasFactory.createJCas();
test_c.setDocumentText("Simple change's in the pipelines used.");
test_c.setDocumentLanguage("en");

// The annotation should be made within a container
DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration.default_config()
  .with_run_in_container(true);

// Create the wrapped pipeline from any AnalysisEngineDescription
DockerWrappedEnvironment env = DockerWrappedEnvironment.from(
  AnalysisEngineFactory.createEngineDescription(OpenNlpSegmenter.class)
).with_pomfile(new File("pom.xml"));

// Create the docker container, note the programm must have access to the docker daemon,
// therefore the programm must either run as root or the current user has access to the daemon
SimplePipeline.runPipeline(test_c,env.build(cfg));
```


The above programm will create a reproducible annotation on the JCas object in addition to all annotations that will be made by the AnalysisEngineDescription which is wrapped.


When one tries to reproduce the exact environment and the exact wrapped AnalysisEngineDescription one has to use the following code.

```java
// Create a second JCas objec to annotate.
JCas second = JCasFactory.createJCas();
second.setDocumentText("Simple change's in the pipelines used.");
second.setDocumentLanguage("en");

// Recreate all reproducible annotations from the first document with the container configuration cfg.
AnalysisEngineDescription desc = DockerWrapperUtil.fromJCas(test_c,cfg);

// Reuse the same Annotators on a different object.
SimplePipeline.runPipeline(second,desc);
```
