# ReproducibleUIMAAnnotations

This is the project for reproducible UIMA annotations. The implementation supports execution in a containerized environment.
To use the implementation one to include the project with the maven command:
```
mvn install -DskipTests
```
The tests take ages to complete on may not run all at any given moment.


Now to the first program using reproducible annotations:
```java
//Create a container configuration and dont run it in a container!
DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration.default_config()
        .with_run_in_container(false);

//Use the standard provided environment and add the analysis engine descriptions to it
DockerWrappedEnvironment env = DockerWrappedEnvironment.from(
        //Any amount of AnalysisEngineDescriptions can be added here
        AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
        AnalysisEngineFactory.createEngineDescription(CoreNlpPosTagger.class)
);

//Create the JCas document
JCas doc = JCasFactory.createJCas();
doc.setDocumentText("This is the first simple example.");
doc.setDocumentLanguage("en");

//Run the reproducible annotation by fusing the environment and the container configuration
//into a new AnalysisEngineDescription
SimplePipeline.runPipeline(doc,env.build(cfg));

//Show the information available from the CAS document
DockerWrapperUtil.describeJCasPipelines(doc);
```

Now to the reproduction part:
```java
//After annotating a JCAS document like in the example before just use this command
//to reproduce any used Pipeline to a new engine
//The second parameter is needed here as the implementation needs to know what kind of container
//it should build or if the solution runs in a container at all. This information is not saved
//by the annotation as any user may use different parameters for the container configuration. The
//annotations made by the engine should not be influenced by the second parameter.
AnalysisEngineDescription desc = DockerWrapperUtil.fromJCas(doc,cfg);

JCas test = JCasFactory.createJCas();
test.setDocumentText("This is the second simple example.");
test.setDocumentLanguage("en");

//This will reproduce the pipeline on a second document
SimplePipeline.run(test,desc);
```

What if one likes to change a previously run experiment?
```java
//Recreate all used DockerWrappedEnvironments sorted by the timestamp they were created.
//In this example there is only one entry as we only used one DockerWrappedEnvironment on the 
//JCas document
List<DockerWrappedEnvironment> envs = DockerWrapperUtil.configurationsFromJCas(doc);

//Iterate over all used DockerWrappedEnvironments
for(DockerWrappedEnvironment i : envs) {
    //Iterate over all Annotators used to change the JCas doc
    for(AnnotatorDescription d : i.get_recursive_descriptions()) {
        //Select all BreakIteratorSegmenter that were used
        if(d.get_name().equals(BreakIteratorSegmenter.class.getName())) {
            //Change the parameter split at apostrophe to true
            AnnotatorParameterWrapper p = d.get_parameter(
                    BreakIteratorSegmenter.PARAM_SPLIT_AT_APOSTROPHE
            );
            
            p.set_value(true);
        }
    }
}

//Create an example where the split at apostrophe matters
JCas test_c = JCasFactory.createJCas();
test_c.setDocumentText("Simple change's in the pipelines used.");
test_c.setDocumentLanguage("en");

//Aggregate all used environments in an engine again
SimplePipeline.run(test_c,DockerWrapperUtil.fromEnvironmentLists(envs, cfg))
```

The next example shows that every DockerWrappedEnvironment is fully capable of recreating the 
associated engine. Therfore one can decide to run every pipeline one by one:
```java
//Recreate all used DockerWrappedEnvironments sorted by the timestamp they were created.
//In this example there is only one entry as we only used one DockerWrappedEnvironment on the 
//JCas document
List<DockerWrappedEnvironment> envs = DockerWrapperUtil.configurationsFromJCas(doc);


JCas test_s = JCasFactory.createJCas();
test_s.setDocumentText("Simple change's in the pipelines used.");
test_s.setDocumentLanguage("en");

//Iterate over all used DockerWrappedEnvironments
for(DockerWrappedEnvironment i : envs) {
    //When one wants to use only one engine
        
    SimplePipeline.run(test_s,i.build(cfg));
}


//Aggregate all used environments in an engine again
SimplePipeline.run(test_c,DockerWrapperUtil.fromEnvironmentLists(envs, cfg))
```

Now to a more complicated example, changing the target view of one pipeline and recreating specific experiments:
```java
DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration.default_config()
        .with_run_in_container(false);

//Give the environment a name to later select by name
DockerWrappedEnvironment env = DockerWrappedEnvironment.from(
        AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
        AnalysisEngineFactory.createEngineDescription(CoreNlpPosTagger.class)
).with_name("experiment_1");

//Give the second environment also a name and redirect any changes to the 
//default SoFa to the "second_view". Also write the reproducible annotation
//generated by the environment into "third_view".
DockerWrappedEnvironment env2 = DockerWrappedEnvironment.from(
        AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class, BreakIteratorSegmenter.PARAM_SPLIT_AT_APOSTROPHE, true),
        AnalysisEngineFactory.createEngineDescription(CoreNlpPosTagger.class)
).with_name("experiment_2").with_reproducible_annotation_target_view("third_view")
        .with_sofa_mapping(CAS.NAME_DEFAULT_SOFA,"second_view");
        

//Create a cas with the needed views.
JCas view_cas = JCasFactory.createJCas();
view_cas.setDocumentText("This is the first simple example.");
view_cas.setDocumentLanguage("en");

JCas view_cas_sec = view_cas.createView("second_view");
view_cas_sec.setDocumentText("This is the first simple example.");
view_cas_sec.setDocumentLanguage("en");

JCas view_cas_third = view_cas.createView("third_view");


SimplePipeline.runPipeline(view_cas,env.build(cfg));
SimplePipeline.runPipeline(view_cas,env2.build(cfg));
```

Now equipped with an CAS that was annotated in that way one can reproduce the experiments in multiple ways:
```java
JCas test_view = JCasFactory.createJCas();
test_view.setDocumentText("This is the first simple example.");
test_view.setDocumentLanguage("en");

//Get all environments and filter by experiment name
List<DockerWrappedEnvironment> envs = DockerWrapperUtil.configurationsFromJCas(view_cas);
envs = envs.stream().filter((i)->!i.get_name().equals("experiment_1")).collect(Collectors.toList());

//Recreate pipeline and run again
SimplePipeline.runPipeline(test_view,DockerWrapperUtil.fromEnvironmentLists(envs,cfg));
```


```java
JCas test_view = JCasFactory.createJCas();
test_view.setDocumentText("This is the first simple example.");
test_view.setDocumentLanguage("en");

//Iterate only over the environments annotated in the third_view
List<DockerWrappedEnvironment> envs = DockerWrapperUtil.configurationsFromJCas(view_cas,"third_view");
```

Or take the second experiment and remap the sofa so that we write to another view:
```java
//Select all pipelines run on the object and change the experiment 2 mapping from "second_view" 
//to "third_view"
List<DockerWrappedEnvironment> envs = DockerWrapperUtil.configurationsFromJCas(view_cas);
for(DockerWrappedEnvironment i : envs) {
    if(i.get_name().equals("experiment_2")) {
        i.remove_sofa_mappings(CAS.NAME_DEFAULT_SOFA,"second_view");
        i.with_sofa_mapping(CAS.NAME_DEFAULT_SOFA,"third_view");
    }
}
```

When using the container version:
```java
//This runs the specified engine in an container
//Building the container takes a lot of time, since it downloads all
//dependencies for the first time. The TextImager has 700MB dependencies.
DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
        .default_config()
        //Run in a container
        .with_run_in_container(true)
        //Give the container a name, careful a name must be unique!!!
        .with_container_name("experiment_v1.1_date_14_11_2021")
        //Autoremove the container after stopping
        .with_container_autoremove(true)
        //export the container at the end to a new image with the repository
        //ttlab experiment and tag v1.1
        .with_export_to_new_image("ttlab_experiment","v1.1");
DockerWrappedEnvironment env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(
        BreakIteratorSegmenter.class
));
//Add the pomfile as we need the dependencies to instantiate the engine
//Note: Only classes from the pom file can be used not local classes, it would not
//be very reproducible otherwise. This is a limitation thats not applying to the
//run in container false configuration        
env.with_pomfile(new File("pom.xml"));

//Create a test cas
JCas jc = JCasFactory.createJCas(desc);
jc.setDocumentText("This is a very simple text.");
jc.setDocumentLanguage("en");

//Build the container run it and verify the results
SimplePipeline.runPipeline(jc, env.build(cfg));
System.out.println(DockerWrapperUtil.cas_to_xmi(jc));
```

DUCC integration
```java
//Do not use a container, ducc is already in a container
DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
        .default_config()
        .with_run_in_container(false)
        .with_confirm_integrity(false)
        .with_container_autoremove(true);

//Create a new engine
AggregateBuilder builder = new AggregateBuilder();
builder.add(AnalysisEngineFactory.createEngineDescription(
        BreakIteratorSegmenter.class
));

builder.add(AnalysisEngineFactory.createEngineDescription(CoreNlpPosTagger.class));

//Create the wrapped environment
DockerWrappedEnvironment env = DockerWrappedEnvironment.from(builder.createAggregateDescription());


//Create a collection reader
CollectionReaderDescription rd = CollectionReaderFactory.createReaderDescription(TextReader.class,
        TextReader.PARAM_SOURCE_LOCATION, "/home/alexander/textimagerserver/texts",
        TextReader.PARAM_PATTERNS, "[+]**/*.txt",
        TextReader.PARAM_LANGUAGE, "en");

//This does work on my system however I changed the config so that it executes,
//the point is one can serialize the whole reproducible analysis engine and let ducc
//execute it.
DockerWrapperUtil.ducc_execute(rd,env.build(cfg),XmiWriterCasConsumer.getDescription());
```