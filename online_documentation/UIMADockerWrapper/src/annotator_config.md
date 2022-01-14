# Change Annotator Configuration
This page will give a short introduction in the manipulation of reproducible experiments. The following example shows how to obtain a list of all created reproducible annotations. And how to remove sofa mappings from them, and run them selectively.

```java
// Get a list of all reproducible annotations from JCas document
List<DockerWrappedEnvironment> envs = DockerWrapperUtil.configurationsFromJCas(jc);

for(DockerWrappedEnvironment i : envs) {
  // Print the experiment name
  System.out.printf("Experiment name %s\n",i.get_name());
  
  // Check if the analysis engine has any sofa mappings
  if(i.has_sofa_mapping(CAS.NAME_DEFAULT_SOFA)) {
    // If there is a mapping remove it
    i.remove_sofa_mappings(CAS.NAME_DEFAULT_SOFA);
  }
  // Remap the default sofa to view 2
  i.with_sofa_mapping(CAS.NAME_DEFAULT_SOFA,"view_2");

  // Print all annotators in reproducible annotation
  for(AnnotatorDescription d : i.get_recursive_descriptions()) {
    System.out.printf("Found annotator %s\n",d.get_name());
  }


  // Execute the changed pipeline on a new JCas document test.
  SimplePipeline.runPipeline(test, i.build(cfg));
}
```


More examples are coming, once I have the time to add them to this documentation.
