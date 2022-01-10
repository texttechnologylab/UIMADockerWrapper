package org.hucompute.uimadockerwrapper.annotators;

import org.hucompute.uimadockerwrapper.DockerWrappedEnvironment;
import org.hucompute.uimadockerwrapper.DockerWrapper;
import org.hucompute.uimadockerwrapper.DockerWrapperContainerConfiguration;
import org.hucompute.uimadockerwrapper.ReproducibleAnnotation;
import org.hucompute.uimadockerwrapper.base_env.DockerBaseJavaEnv;
import org.hucompute.uimadockerwrapper.base_env.DockerBasePythonGPUEnv;
import org.hucompute.uimadockerwrapper.modules.DockerWrapperModuleSkipAlreadyAnnotated;
import org.hucompute.uimadockerwrapper.util.*;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.tudarmstadt.ukp.dkpro.core.tokit.CamelCaseTokenSegmenter;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.*;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.InvalidXMLException;
import org.dkpro.core.corenlp.CoreNlpPosTagger;
import org.dkpro.core.io.text.TextReader;
import org.dkpro.core.stanfordnlp.StanfordNamedEntityRecognizer;
import org.hucompute.uimadockerwrapper.util.AnnotatorDescription;
import org.hucompute.uimadockerwrapper.util.AsyncPipeline;
import org.hucompute.uimadockerwrapper.util.DockerWrapperUtil;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.*;
import java.sql.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DockerWrapperTest {
    @Test
    void TestNotRunningInContainer() throws UIMAException, IOException, SAXException {
        //This runs the specified engine not in an container but in the host system
        TypeSystemDescription desc = TypeSystemDescriptionFactory.createTypeSystemDescriptionFromPath("/home/alexander/Documents/BachelorThesis/target/jcasgen/typesystem.xml");

        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(false)
                .with_container_name("hallo")
                .with_gpu(true);



        JCas jc = JCasFactory.createJCas(desc);
        jc.setDocumentText("This is a very simple text.");
        jc.setDocumentLanguage("en");

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(
                ExampleAnnotator.class,
                ExampleAnnotator.PARAM_PIPELINE_CONFIGURATION, ""
        ));

        env.with_pomfile(new File("pom.xml"));
        SimplePipeline.runPipeline(jc, env.build(cfg));
        System.out.println(DockerWrapperUtil.cas_to_xmi(jc));
    }

    @Test
    void TestRunInContainer() throws UIMAException, IOException, SAXException {
        //This runs the specified engine not in an container but in the host system
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(true)
                .with_container_autoremove(false);
        AggregateBuilder builder = new AggregateBuilder();
        builder.add(AnalysisEngineFactory.createEngineDescription(
                ExampleAnnotator.class,
                ExampleAnnotator.PARAM_PIPELINE_CONFIGURATION, "inner_0"
        ),CAS.NAME_DEFAULT_SOFA,"second_view");

        builder.add(AnalysisEngineFactory.createEngineDescription(
                ExampleAnnotator.class,
                ExampleAnnotator.PARAM_PIPELINE_CONFIGURATION, "inner_1"
        ));

        AggregateBuilder intoDepth = new AggregateBuilder();
        intoDepth.add(builder.createAggregateDescription());
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(
                        ExampleAnnotator.class,
                        ExampleAnnotator.PARAM_PIPELINE_CONFIGURATION, ""
                ),
                AnalysisEngineFactory.createEngineDescription(
                        OpenNlpSegmenter.class
                ),
                intoDepth.createAggregateDescription(),
                AnalysisEngineFactory.createEngineDescription(
                        OpenNlpPosTagger.class
                ),
                builder.createAggregateDescription());
        env.with_pomfile(new File("pom.xml"));
        env.withResource("src",new File("src"));

        JCas jc = JCasFactory.createJCas();
        jc.setDocumentText("This is a very simple text.");
        jc.setDocumentLanguage("en");

        SimplePipeline.runPipeline(jc, env.build(cfg));
        System.out.println(DockerWrapperUtil.cas_to_xmi(jc));
    }

    @Test
    void TestRunInContainerWithAutoremove() throws UIMAException, IOException, SAXException {
        //This runs the specified engine not in an container but in the host system
        //TypeSystemDescription desc = TypeSystemDescriptionFactory.createTypeSystemDescriptionFromPath("/home/alexander/Documents/BachelorThesis/target/jcasgen/typesystem.xml");
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(true)
                .with_container_autoremove(false);
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(
                OpenNlpSegmenter.class
        ));
        env.with_pomfile(new File("pom.xml"));

        JCas jc = JCasFactory.createJCas();
        jc.setDocumentText("This is a very simple text.");
        jc.setDocumentLanguage("en");

        SimplePipeline.runPipeline(jc, env.build(cfg));
        System.out.println(DockerWrapperUtil.cas_to_xmi(jc));
    }

    @Test
    void TestRunInContainerWithExport() throws UIMAException, IOException, SAXException {
        //This runs the specified engine not in an container but in the host system
        TypeSystemDescription desc = TypeSystemDescriptionFactory.createTypeSystemDescriptionFromPath("/home/alexander/Documents/BachelorThesis/target/jcasgen/typesystem.xml");
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(true)
                .with_container_name("experiment_v1.1_date_14_11_2021")
                .with_container_autoremove(true)
                .with_export_to_new_image("alex_experiment","v1.1");
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(
                ExampleAnnotator.class,
                ExampleAnnotator.PARAM_PIPELINE_CONFIGURATION, ""
        ));
        env.with_pomfile(new File("pom.xml"));

        JCas jc = JCasFactory.createJCas(desc);
        jc.setDocumentText("This is a very simple text.");
        jc.setDocumentLanguage("en");

        SimplePipeline.runPipeline(jc, env.build(cfg));
        System.out.println(DockerWrapperUtil.cas_to_xmi(jc));
    }

    @Test
    void TestRunInContainerWithGPU() throws UIMAException, IOException, SAXException {
        //This runs the specified engine not in an container but in the host system
        TypeSystemDescription desc = TypeSystemDescriptionFactory.createTypeSystemDescriptionFromPath("/home/alexander/Documents/BachelorThesis/target/jcasgen/typesystem.xml");
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(true)
                .with_container_autoremove(true)
                .with_gpu(true)
                .with_export_to_new_image("gpu_exp","v0.0alpha");

        DockerBasePythonGPUEnv env = new DockerBasePythonGPUEnv("FROM nvidia/cuda:11.2.0-devel-ubuntu20.04");
        env.add_install_requirement("torch==1.10.0+cu111 -f https://download.pytorch.org/whl/torch_stable.html");
        env.add_raw_dockercmd("RUN wget https://raw.githubusercontent.com/ShadowItaly/ReproducibleAnnotations/master/python_impl/test/TorchAnnotator.py");

        DockerWrappedEnvironment wrapped_env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(
                PythonAnnotator.class,
                PythonAnnotator.PARAM_FILE_PATH, "/TorchAnnotator.py",
                PythonAnnotator.PARAM_CLASS_NAME, "TorchAnnotator"
        ))
                .with_dockerfile(env);

        JCas jc = JCasFactory.createJCas(desc);
        jc.setDocumentText("This is a very simple text.");
        jc.setDocumentLanguage("en");

        SimplePipeline.runPipeline(jc, wrapped_env.build(cfg));
        System.out.println(DockerWrapperUtil.cas_to_xmi(jc));
    }

    @Test
    void TestUseExisting() throws UIMAException, IOException, SAXException {
        //This runs the specified engine not in an container but in the host system
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_gpu(true)
                .with_unsafe_running_container_id("c63e8a4f9f3d");

        JCas jc = JCasFactory.createJCas();
        jc.setDocumentText("This is a very simple text.");
        jc.setDocumentLanguage("en");

        SimplePipeline.runPipeline(jc, DockerWrapper.use_existing(cfg));
        System.out.println(DockerWrapperUtil.cas_to_xmi(jc));
    }

    @Test
    void TestDeepContainerChain() throws UIMAException, IOException, SAXException {
        //This runs the specified engine not in an container but in the host system
        TypeSystemDescription desc = TypeSystemDescriptionFactory.createTypeSystemDescriptionFromPath("/home/alexander/Documents/BachelorThesis/target/jcasgen/typesystem.xml");
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(true)
                .with_container_autoremove(true)
                .with_unsafe_map_docker_daemon(true);
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(
                ExampleAnnotator.class,
                ExampleAnnotator.PARAM_PIPELINE_CONFIGURATION, ""
        ));
        env.with_pomfile(new File("pom.xml"));
        env.with_dockerfile(new DockerBaseJavaEnv("openjdk-8-jdk"));

        JCas jc = JCasFactory.createJCas(desc);
        jc.setDocumentText("This is a very simple text.");
        jc.setDocumentLanguage("en");

        SimplePipeline.runPipeline(jc,env.build(cfg));
        System.out.println("Finished");
    }

    @Test
    void RecoverInformationAfterAnnotations() throws IOException, UIMAException, SAXException, CompressorException {
        //This runs the specified engine not in an container but in the host system
        TypeSystemDescription desc = TypeSystemDescriptionFactory.createTypeSystemDescription();
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(false)
                .with_container_autoremove(true);
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(
                        ExampleAnnotator.class,
                        ExampleAnnotator.PARAM_PIPELINE_CONFIGURATION, ""
                ),
                AnalysisEngineFactory.createEngineDescription(
                        OpenNlpSegmenter.class
                ),
                AnalysisEngineFactory.createEngineDescription(
                        OpenNlpPosTagger.class
                )
        );
        env.with_pomfile(new File("pom.xml"));

        JCas jc = JCasFactory.createJCas(desc);
        jc.setDocumentText("This is a very simple text.");
        jc.setDocumentLanguage("en");

        SimplePipeline.runPipeline(jc, env.build(cfg));


        System.out.println(DockerWrapperUtil.checkJCasIntegrity(jc));

        Sentence xl = new Sentence(jc);
        xl.setBegin(0);
        xl.setEnd(10);
        xl.addToIndexes();

        System.out.println(DockerWrapperUtil.checkJCasIntegrity(jc));

        Arrays.stream(DockerWrapperUtil.getAnnotatorNames(jc)).forEach(System.out::println);

        System.out.println("\nDockerfile");
        DockerWrapperUtil.configurationsFromJCas(jc).stream().map((a)->a.get_dockerfile()).forEach(System.out::println);

        System.out.println("\nPOM File");
        DockerWrapperUtil.configurationsFromJCas(jc).stream().map((a)->a.get_pomfile()).forEach(System.out::println);


        AnalysisEngineDescription reproduced = DockerWrapperUtil.fromJCas(jc,cfg);

        JCas jcas_other = JCasFactory.createJCas();
        jcas_other.setDocumentText("This is a different text but still simple.");
        jcas_other.setDocumentLanguage("en");

        SimplePipeline.runPipeline(jcas_other,reproduced);

        {
            System.out.println("SECOND JCAS REPRODUCED !!_______________________");
            System.out.println(DockerWrapperUtil.checkJCasIntegrity(jcas_other));

            Sentence xl2 = new Sentence(jcas_other);
            xl2.setBegin(0);
            xl2.setEnd(10);
            xl2.addToIndexes();

            System.out.println(DockerWrapperUtil.checkJCasIntegrity(jcas_other));

            Arrays.stream(DockerWrapperUtil.getAnnotatorNames(jcas_other)).forEach(System.out::println);

            System.out.println("\nDockerfile");
            DockerWrapperUtil.configurationsFromJCas(jcas_other).stream().map((a)->a.get_dockerfile()).forEach(System.out::println);

            System.out.println("\nPOM File");
            DockerWrapperUtil.configurationsFromJCas(jcas_other).stream().map((a)->a.get_pomfile()).forEach(System.out::println);

            AnalysisEngineDescription reproduced_second = DockerWrapperUtil.fromJCas(jcas_other,cfg);
        }
    }

    @Test
    void MultiPipelineSettings() throws IOException, UIMAException, SAXException, CompressorException {
        //This runs the specified engine not in an container but in the host system
        TypeSystemDescription desc = TypeSystemDescriptionFactory.createTypeSystemDescription();
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(false)
                .with_container_autoremove(true);
        AggregateBuilder builder = new AggregateBuilder();
        builder.add(AnalysisEngineFactory.createEngineDescription(
                ExampleAnnotator.class,
                ExampleAnnotator.PARAM_PIPELINE_CONFIGURATION, "inner_0"
        ));

        builder.add(AnalysisEngineFactory.createEngineDescription(
                ExampleAnnotator.class,
                ExampleAnnotator.PARAM_PIPELINE_CONFIGURATION, "inner_1"
        ));

        AggregateBuilder intoDepth = new AggregateBuilder();
        intoDepth.add(builder.createAggregateDescription());
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(
                        ExampleAnnotator.class,
                        ExampleAnnotator.PARAM_PIPELINE_CONFIGURATION, ""
                ),
                AnalysisEngineFactory.createEngineDescription(
                        OpenNlpSegmenter.class
                ),
                intoDepth.createAggregateDescription(),
                AnalysisEngineFactory.createEngineDescription(
                        OpenNlpPosTagger.class
                ),
                builder.createAggregateDescription());
        env.with_pomfile(new File("pom.xml"));

        JCas jc = JCasFactory.createJCas(desc);
        jc.setDocumentText("This is a very simple text.");
        jc.setDocumentLanguage("en");



        SimplePipeline.runPipeline(jc, env.build(cfg));

        System.out.println(DockerWrapperUtil.checkJCasIntegrity(jc));

        Sentence xl = new Sentence(jc);
        xl.setBegin(0);
        xl.setEnd(10);
        xl.addToIndexes();

        System.out.println(DockerWrapperUtil.checkJCasIntegrity(jc));

        Arrays.stream(DockerWrapperUtil.getAnnotatorNames(jc)).forEach(System.out::println);
        {
            System.out.println("Second JCAS Output.");
            AnalysisEngineDescription reproduced_second = DockerWrapperUtil.fromJCas(jc,cfg);
            JCas jcas_other = JCasFactory.createJCas();
            jcas_other.setDocumentText("This is a different text but still simple.");
            jcas_other.setDocumentLanguage("en");

            SimplePipeline.runPipeline(jcas_other,reproduced_second);

            Arrays.stream(DockerWrapperUtil.getAnnotatorNames(jcas_other)).forEach(System.out::println);
        }
    }

    @Test
    void MultiViewSetup() throws IOException, UIMAException, SAXException, CompressorException {
        //This runs the specified engine not in an container but in the host system
        TypeSystemDescription desc = TypeSystemDescriptionFactory.createTypeSystemDescription();


        JCas jc = JCasFactory.createJCas(desc);
        jc.setDocumentText("This is a very simple text.");
        jc.setDocumentLanguage("en");

        JCas second = jc.createView("second_view");
        second.setDocumentLanguage("en");
        second.setDocumentText("This is another document");

        JCas third = jc.createView("third_view");
        third.setDocumentLanguage("en");
        third.setDocumentText("This is another document");

        AggregateBuilder builder = new AggregateBuilder();
        builder.add(AnalysisEngineFactory.createEngineDescription(
                OpenNlpSegmenter.class
        ), CAS.NAME_DEFAULT_SOFA, "second_view");

        builder.add(AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class), CAS.NAME_DEFAULT_SOFA,"second_view");
        builder.add(AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class), CAS.NAME_DEFAULT_SOFA,"third_view");
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(false)
                .with_confirm_integrity(true)
                .with_container_autoremove(true);
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(builder.createAggregateDescription());
        env.with_pomfile(new File("pom.xml"));
        SimplePipeline.runPipeline(jc, env.build(cfg));

        System.out.println(DockerWrapperUtil.cas_to_xmi(jc));


        System.out.println(DockerWrapperUtil.checkJCasIntegrity(jc));

        Sentence xl = new Sentence(jc);
        xl.setBegin(0);
        xl.setEnd(10);
        xl.addToIndexes();

        System.out.println(DockerWrapperUtil.checkJCasIntegrity(jc));

        Arrays.stream(DockerWrapperUtil.getAnnotatorNames(jc)).forEach(System.out::println);
        System.out.println("Nothing printed yet!");

        Arrays.stream(DockerWrapperUtil.getAnnotatorNames(jc.getView("second_view"))).forEach(System.out::println);
    }

    @Test
    void MultiDistinctAnnotators() throws IOException, UIMAException, SAXException, CompressorException {
        //This runs the specified engine not in an container but in the host system
        TypeSystemDescription desc = TypeSystemDescriptionFactory.createTypeSystemDescription();
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(true)
                .with_confirm_integrity(true)
                .with_container_autoremove(true)
                .with_module(DockerWrapperModuleSkipAlreadyAnnotated.class);

        AggregateBuilder builder = new AggregateBuilder();
        builder.add(AnalysisEngineFactory.createEngineDescription(
                OpenNlpSegmenter.class
        ));

        builder.add(AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class));

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(builder.createAggregateDescription())
                .with_reproducible_annotation_target_view("view_2")
                .with_compression(CompressorStreamFactory.XZ)
                .with_pomfile(new File("pom.xml"))
                .with_name("experiment_1_22_11_2021");


        JCas jc = JCasFactory.createJCas(desc);
        jc.setDocumentText("This is a very simple t'ext.");
        jc.setDocumentLanguage("en");

        JCas view_1 = jc.createView("view_1");
        view_1.setDocumentText("This is a very simple t'ext.");
        view_1.setDocumentLanguage("en");

        JCas view_2 = jc.createView("view_2");
        view_2.setDocumentText("This is a very simple t'ext.");
        view_2.setDocumentLanguage("en");

        JCas test = JCasFactory.createJCas(desc);
        test.setDocumentText("This is a very simple t'ext.");
        test.setDocumentLanguage("en");

        JCas test_1 = test.createView("view_1");
        test_1.setDocumentText("This is a very simple t'ext.");
        test_1.setDocumentLanguage("en");

        JCas test_2 = test.createView("view_2");
        test_2.setDocumentText("This is a very simple t'ext.");
        test_2.setDocumentLanguage("en");



        SimplePipeline.runPipeline(jc, env.build(cfg));

        DockerWrapperUtil.describeJCasPipelines(jc);


        System.out.println(DockerWrapperUtil.checkJCasIntegrity(jc));

        System.out.println("Selecting from original!");
        for(TOP tk : JCasUtil.select(jc, TOP.class)) {
            System.out.printf("Default view token: %s\n",tk.getType().getName());
        }
        System.out.printf("Total token count in default view: %d\n",JCasUtil.select(jc,TOP.class).size());
        for(TOP tk : JCasUtil.select(jc.getView("view_1"), TOP.class)) {
            System.out.printf("View 1 token: %s\n",tk.getType().getName());
        }
        System.out.printf("Total token count in view 1: %d\n",JCasUtil.select(jc.getView("view_1"),TOP.class).size());
        for(TOP tk : JCasUtil.select(jc.getView("view_2"), TOP.class)) {
            System.out.printf("View 2 token: %s\n",tk.getType().getName());
        }
        System.out.printf("Total token count in view 2: %d\n\n",JCasUtil.select(jc.getView("view_2"),TOP.class).size());

        List<DockerWrappedEnvironment> envs = DockerWrapperUtil.configurationsFromJCas(jc);

        for(DockerWrappedEnvironment i : envs) {

            System.out.printf("Experiment name %s\n",i.get_name());
            if(i.has_sofa_mapping(CAS.NAME_DEFAULT_SOFA)) {
                i.remove_sofa_mappings(CAS.NAME_DEFAULT_SOFA);
            }
            i.with_sofa_mapping(CAS.NAME_DEFAULT_SOFA,"view_2");

            for(AnnotatorDescription d : i.get_recursive_descriptions()) {
                System.out.printf("Found annotator %s\n",d.get_name());
            }


            SimplePipeline.runPipeline(test, i.build(cfg));
            System.out.println("Selecting from reproduced!");
            for(TOP tk : JCasUtil.select(test, TOP.class)) {
                System.out.printf("Default view token: %s\n",tk.getType().getName());
            }
            System.out.printf("Total token count in default view: %d\n",JCasUtil.select(test,TOP.class).size());
            for(TOP tk : JCasUtil.select(test.getView("view_1"), TOP.class)) {
                System.out.printf("View 1 token: %s\n",tk.getType().getName());
            }
            System.out.printf("Total token count in view 1: %d\n",JCasUtil.select(test.getView("view_1"),TOP.class).size());
            for(TOP tk : JCasUtil.select(test.getView("view_2"), TOP.class)) {
                System.out.printf("View 2 token: %s\n",tk.getType().getName());
            }
            System.out.printf("Total token count in view 2: %d\n\n",JCasUtil.select(test.getView("view_2"),TOP.class).size());


            DockerWrappedEnvironment kk = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class))
                    .with_sofa_mapping(CAS.NAME_DEFAULT_SOFA,"view_2");
            SimplePipeline.runPipeline(test, kk.get_engine_description());
        }
    }

    @Test
    void DuccTest() throws Exception {
        //This runs the specified engine not in an container but in the host system
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration
                .default_config()
                .with_run_in_container(false)
                .with_confirm_integrity(false)
                .with_container_autoremove(true);

        AggregateBuilder builder = new AggregateBuilder();
        builder.add(AnalysisEngineFactory.createEngineDescription(
                OpenNlpSegmenter.class
        ));

        builder.add(AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class));
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(builder.createAggregateDescription());
        env.with_pomfile(new File("pom.xml"));

        CollectionReaderDescription rd = CollectionReaderFactory.createReaderDescription(TextReader.class,
                TextReader.PARAM_SOURCE_LOCATION, "/home/alexander/textimagerserver/stuff",
                TextReader.PARAM_PATTERNS, "[+]**/*.txt",
                TextReader.PARAM_LANGUAGE, "en");
        //DockerWrapperUtil.ducc_execute(rd,env.build(cfg),XmiWriterCasConsumer.getDescription());
    }

    @Test
    void Evaluation() throws Exception {
        CollectionReaderDescription rd;


        Class.forName("org.sqlite.JDBC");
        Connection c = DriverManager.getConnection("jdbc:sqlite:evaluation.db");



        for(int corpus = 0; corpus < 1; corpus++) {
            String target = "";
            String target_async = "";
            if(corpus==0) {
                rd = CollectionReaderFactory.createReaderDescription(TextReader.class,
                        TextReader.PARAM_SOURCE_LOCATION, "/home/alexander/Documents/BachelorThesis/corpora/extract_wikipedia_sample/output",
                        TextReader.PARAM_PATTERNS, "[+]**/*.txt",
                        TextReader.PARAM_LANGUAGE, "en");
                Statement state = c.createStatement();
                String creat_table = "CREATE TABLE IF NOT EXISTS experiment_en_wikipedia(time INTEGER, size INTEGER, with_wrapper INTEGER, in_container INTEGER, with_compression TEXT," +
                        "number_of_annotators INTEGER, scaleout INTEGER, confirm_integrity INTEGER)";
                String creat_table_async = "CREATE TABLE IF NOT EXISTS experiment_en_wikipedia_async(time INTEGER, documents INTEGER, number_of_annotators INTEGER)";
                state.executeUpdate(creat_table);
                state.close();
                Statement stateas = c.createStatement();
                stateas.executeUpdate(creat_table_async);
                stateas.close();
                target = "experiment_en_wikipedia";
                target_async = "experiment_en_wikipedia_async";
            }
            else {
                rd = CollectionReaderFactory.createReaderDescription(TextReader.class,
                        TextReader.PARAM_SOURCE_LOCATION, "/home/alexander/Documents/BachelorThesis/corpora/German-political-speeches-2019-release/output",
                        TextReader.PARAM_PATTERNS, "[+]**/*.txt",
                        TextReader.PARAM_LANGUAGE, "de");
                Statement state = c.createStatement();
                String creat_table = "CREATE TABLE IF NOT EXISTS experiment_germ_polit(time INTEGER, size INTEGER, with_wrapper INTEGER, in_container INTEGER, with_compression TEXT," +
                        "number_of_annotators INTEGER, scaleout INTEGER, confirm_integrity INTEGER)";
                String creat_table_async = "CREATE TABLE IF NOT EXISTS experiment_germ_polit_async(time INTEGER, documents INTEGER, number_of_annotators INTEGER)";
                state.executeUpdate(creat_table);

                state.close();
                Statement stateas = c.createStatement();
                stateas.executeUpdate(creat_table_async);
                stateas.close();
                target = "experiment_germ_polit";
                target_async = "experiment_germ_polit_async";
            }

            for (int i = 27; i > -1; i--) {
                int with_wrapper = 0;
                int in_container = 0;
                String with_compression = "none";
                int number_of_annotators = 1;
                int confirm_integrity = 0;
                boolean async = false;
                AnalysisEngineDescription desc = null;
                if (i % 4 == 0) {
                    desc = AnalysisEngineFactory.createEngineDescription(AnalysisEngineFactory.createEngineDescription(
                            BreakIteratorSegmenter.class
                    ));
                } else if (i % 4 == 1) {
                    AggregateBuilder builder2 = new AggregateBuilder();
                    builder2.add(AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class));
                    builder2.add(AnalysisEngineFactory.createEngineDescription(CoreNlpPosTagger.class));
                    desc = builder2.createAggregateDescription();
                } else if (i % 4 == 2) {
                    AggregateBuilder builder2 = new AggregateBuilder();
                    builder2.add(AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class));
                    builder2.add(AnalysisEngineFactory.createEngineDescription(CoreNlpPosTagger.class));
                    builder2.add(AnalysisEngineFactory.createEngineDescription(CamelCaseTokenSegmenter.class));
                    desc = builder2.createAggregateDescription();
                } else if (i % 4 == 3) {
                    AggregateBuilder builder2 = new AggregateBuilder();
                    builder2.add(AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class));
                    builder2.add(AnalysisEngineFactory.createEngineDescription(CoreNlpPosTagger.class));
                    builder2.add(AnalysisEngineFactory.createEngineDescription(CamelCaseTokenSegmenter.class));
                    builder2.add(AnalysisEngineFactory.createEngineDescription(StanfordNamedEntityRecognizer.class));
                    desc = builder2.createAggregateDescription();
                }

                if (i == 0) {
                    with_wrapper = 0;
                    in_container = 0;
                    with_compression = "none";
                    number_of_annotators = 1;
                    confirm_integrity = 0;
                } else if (i == 1) {
                    with_wrapper = 0;
                    in_container = 0;
                    with_compression = "none";
                    number_of_annotators = 2;
                    confirm_integrity = 0;
                } else if (i == 2) {
                    with_wrapper = 0;
                    in_container = 0;
                    with_compression = "none";
                    number_of_annotators = 3;
                    confirm_integrity = 0;
                } else if (i == 3) {
                    with_wrapper = 0;
                    in_container = 0;
                    with_compression = "none";
                    number_of_annotators = 4;
                    confirm_integrity = 0;
                } else if (i == 4) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 1;
                    confirm_integrity = 0;
                } else if (i == 5) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 2;
                    confirm_integrity = 0;
                } else if (i == 6) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 3;
                    confirm_integrity = 0;
                } else if (i == 7) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 4;
                    confirm_integrity = 0;
                } else if (i == 8) {
                    with_wrapper = 1;
                    in_container = 1;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 1;
                    confirm_integrity = 0;
                } else if (i == 9) {
                    with_wrapper = 1;
                    in_container = 1;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 2;
                    confirm_integrity = 0;
                } else if (i == 10) {
                    with_wrapper = 1;
                    in_container = 1;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 3;
                    confirm_integrity = 0;
                } else if (i == 11) {
                    with_wrapper = 1;
                    in_container = 1;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 4;
                    confirm_integrity = 0;
                } else if (i == 12) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 1;
                    confirm_integrity = 1;
                } else if (i == 13) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 2;
                    confirm_integrity = 1;
                } else if (i == 14) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 3;
                    confirm_integrity = 1;
                } else if (i == 15) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 4;
                    confirm_integrity = 1;
                } else if (i == 16) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = "";
                    number_of_annotators = 1;
                    confirm_integrity = 0;
                } else if (i == 17) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = "";
                    number_of_annotators = 2;
                    confirm_integrity = 0;
                } else if (i == 18) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = "";
                    number_of_annotators = 3;
                    confirm_integrity = 0;
                } else if (i == 19) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = "";
                    number_of_annotators = 4;
                    confirm_integrity = 0;
                } else if (i == 20) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.DEFLATE;
                    number_of_annotators = 1;
                    confirm_integrity = 0;
                } else if (i == 21) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.DEFLATE;
                    number_of_annotators = 2;
                    confirm_integrity = 0;
                } else if (i == 22) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.DEFLATE;
                    number_of_annotators = 3;
                    confirm_integrity = 0;
                } else if (i == 23) {
                    with_wrapper = 1;
                    in_container = 0;
                    with_compression = CompressorStreamFactory.DEFLATE;
                    number_of_annotators = 4;
                    confirm_integrity = 0;
                } else if (i == 24) {
                    with_wrapper = 1;
                    in_container = 1;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 1;
                    confirm_integrity = 0;
                    async = true;
                } else if (i == 25) {
                    with_wrapper = 1;
                    in_container = 1;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 2;
                    confirm_integrity = 0;
                    async = true;
                } else if (i == 26) {
                    with_wrapper = 1;
                    in_container = 1;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 3;
                    confirm_integrity = 0;
                    async = true;
                } else if (i == 27) {
                    with_wrapper = 1;
                    in_container = 1;
                    with_compression = CompressorStreamFactory.XZ;
                    number_of_annotators = 4;
                    confirm_integrity = 0;
                    async = true;
                } else {
                    break;
                }

                if (with_wrapper == 1 && async == false) {
                    DockerWrappedEnvironment env = DockerWrappedEnvironment.from(desc);
                    env.with_pomfile(new File("pom.xml"));
                    env.with_compression(with_compression);
                    desc = env.build(DockerWrapperContainerConfiguration.default_config()
                            .with_run_in_container(in_container == 1)
                            .with_confirm_integrity(confirm_integrity == 1)
                            .with_container_autoremove(true));
                }


                if (!async) {
                    Iterator<JCas> iterator = SimplePipeline.iteratePipeline(rd, desc).iterator();
                    PreparedStatement ps = c
                            .prepareStatement("INSERT INTO "+target+" VALUES (?, ?, ?, ?, ?, ?, ?,?);");
                    int error_count = 0;
                    while (iterator.hasNext()) {
                        long current = System.currentTimeMillis();
                        JCas jc = iterator.next();
                        long endTime = System.currentTimeMillis();
                        long totalTime = endTime - current;
                        long totalSize = DockerWrapperUtil.cas_to_xmi(jc).length();

                        ps.setLong(1, totalTime);
                        ps.setLong(2, totalSize);
                        ps.setInt(3, with_wrapper);
                        ps.setInt(4, in_container);
                        ps.setString(5, with_compression);
                        ps.setInt(6, number_of_annotators);
                        ps.setInt(7, 1);
                        ps.setInt(8, confirm_integrity);
                        ps.executeUpdate();
                        System.out.printf("Iterator %d\n",i);
                    }
                } else {
                    DockerWrappedEnvironment env = DockerWrappedEnvironment.from(desc);
                    env.with_pomfile(new File("pom.xml"));
                    env.with_compression(with_compression);
                    desc = env.build(DockerWrapperContainerConfiguration.default_config()
                            .with_run_in_container(true)
                            .with_confirm_integrity(false)
                            .with_container_autoremove(true)
                            .with_scaleout(4));
                    PreparedStatement ps = c
                            .prepareStatement("INSERT INTO "+target_async+" VALUES (?, ?, ?);");
                    long current = System.currentTimeMillis();
                    AsyncPipeline.run(rd, desc, (a) -> {
                        return a;
                    });
                    ps.setLong(1, System.currentTimeMillis() - current);
                    ps.setInt(2, 6685);
                    ps.setInt(3, number_of_annotators);
                    ps.executeUpdate();
                }
            }
        }
        c.close();
    }

    @Test
    void check_same_size() throws IOException, ResourceInitializationException, SAXException, InvalidXMLException {
        AnalysisEngineDescription base = AnalysisEngineFactory.createEngineDescription(AnalysisEngineFactory.createEngineDescription(
                OpenNlpSegmenter.class
        ));
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(base,AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class));
        env.with_pomfile(new File("pom.xml"));
        env.with_compression(CompressorStreamFactory.XZ);

        AnalysisEngineDescription cont = env.build(DockerWrapperContainerConfiguration.default_config()
                .with_run_in_container(false)
                .with_confirm_integrity(true)
                .with_container_autoremove(true));


        AnalysisEngineDescription no_cont = env.build(DockerWrapperContainerConfiguration.default_config()
                .with_run_in_container(false)
                .with_confirm_integrity(true)
                .with_container_autoremove(true));
        CollectionReaderDescription rd = CollectionReaderFactory.createReaderDescription(TextReader.class,
                TextReader.PARAM_SOURCE_LOCATION, "/home/alexander/Documents/BachelorThesis/corpora/extract_wikipedia_sample/output",
                TextReader.PARAM_PATTERNS, "[+]**/*.txt",
                TextReader.PARAM_LANGUAGE, "de");


        Iterator<JCas> iterator = SimplePipeline.iteratePipeline(rd, cont).iterator();
        Iterator<JCas> iter2 = SimplePipeline.iteratePipeline(rd, no_cont).iterator();
        while (iterator.hasNext() && iter2.hasNext()) {
            JCas jc = iterator.next();
            JCas jc2 = iter2.next();
            System.out.println("Size: "+JCasUtil.selectAll(jc).size());
            String st1 = DockerWrapperUtil.cas_to_xmi(jc).replaceAll("timestamp=\"[0-9]+\"","");
            String st2 = DockerWrapperUtil.cas_to_xmi(jc2).replaceAll("timestamp=\"[0-9]+\"","");
            if (!st1.equals(st2)) {
                System.out.println(DockerWrapperUtil.cas_to_xmi(jc));
                System.out.println(DockerWrapperUtil.cas_to_xmi(jc2));
                assert(false);
            }
        }
    }

    @Test
    void simplest_usage_example() throws UIMAException, IOException, SAXException, CompressorException, InterruptedException {
        CollectionReaderDescription rd = CollectionReaderFactory.createReaderDescription(TextReader.class,
                TextReader.PARAM_SOURCE_LOCATION, "/home/alexander/Documents/BachelorThesis/corpora/extract_wikipedia_sample/output",
                TextReader.PARAM_PATTERNS, "[+]**/*.txt",
                TextReader.PARAM_LANGUAGE, "de");

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        env.with_pomfile(new File("pom.xml"));
        env.with_compression(CompressorStreamFactory.XZ);

        AnalysisEngineDescription cont = env.build(DockerWrapperContainerConfiguration.default_config()
                .with_run_in_container(false)
                .with_confirm_integrity(false)
                .with_container_autoremove(true));

        JCas jc = JCasFactory.createJCas();
        jc.setDocumentText("This is a very simple t'ext.");
        jc.setDocumentLanguage("en");

        SimplePipeline.runPipeline(jc,cont);
        System.out.println(DockerWrapperUtil.cas_to_xmi(jc));
    }

    @Test
    void performance_comparision() throws UIMAException, IOException, SAXException, CompressorException, InterruptedException {
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration.default_config()
                .with_run_in_container(true)
                .with_container_initialise_timeout(50)
                .with_scaleout(4)
                .with_container_autoremove(true);

        DockerWrapperContainerConfiguration cfg_no_cont = DockerWrapperContainerConfiguration.default_config()
                .with_run_in_container(false)
                .with_container_initialise_timeout(50);


        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(
                AnalysisEngineFactory.createEngineDescription(OpenNlpSegmenter.class),
                AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class)
                ).with_name("experiment_1").with_pomfile(new File("pom.xml"));

        DockerWrappedEnvironment env2 = DockerWrappedEnvironment.from(
                        AnalysisEngineFactory.createEngineDescription(OpenNlpSegmenter.class),
                        AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class)
                ).with_name("experiment_2");

        CollectionReaderDescription rd = CollectionReaderFactory.createReaderDescription(TextReader.class,
                TextReader.PARAM_SOURCE_LOCATION, "/home/alexander/Documents/BachelorThesis/corpora/extract_wikipedia_sample/output",
                TextReader.PARAM_PATTERNS, "[+]**/*.txt",
                TextReader.PARAM_LANGUAGE, "en"
        );
        System.out.println(DockerWrapperUtil.typesystemToJSON(env2.build(cfg_no_cont).getAnalysisEngineMetaData().getTypeSystem()));
        AnalysisEngine eng2 = AnalysisEngineFactory.createEngine(env2.build(cfg_no_cont));


        long time_1 = System.currentTimeMillis();
        AsyncPipeline.run(rd,env.build(cfg), (a) -> a);
        long runtime1 = System.currentTimeMillis()-time_1;
        /*
        time_1 = System.currentTimeMillis();
        SimplePipeline.runPipeline(CollectionReaderFactory.createReader(rd),eng2);
        long runtime2 = System.currentTimeMillis()-time_1;
        System.out.printf("Experiment 1 took %dms\n",runtime1);
        System.out.printf("Experiment 2 took %dms\n",runtime2);*/
    }

    @Test
    void third_test() throws UIMAException, IOException, SAXException {
        JCas cas = JCasFactory.createJCas();
        cas.setDocumentLanguage("de");
        cas.setDocumentText("ok");
        ReproducibleAnnotation ann = new ReproducibleAnnotation(cas);
        ann.setCompression("xz");
        ann.setConfiguration("ok");
        ann.setTimestamp(0);
        ann.addToIndexes();
        DockerWrapperContainerConfiguration cfg = DockerWrapperContainerConfiguration.default_config()
                .with_run_in_container(true);
        StringWriter wr = new StringWriter();
        AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class).getAnalysisEngineMetaData().getTypeSystem().toXML(wr);
        System.out.println(wr.getBuffer().toString());

//Give the environment a name to later select by name
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(
                AnalysisEngineFactory.createEngineDescription(BreakIteratorSegmenter.class),
                AnalysisEngineFactory.createEngineDescription(CoreNlpPosTagger.class),
                AnalysisEngineFactory.createEngineDescription(CamelCaseTokenSegmenter.class),
                AnalysisEngineFactory.createEngineDescription(CoreNlpPosTagger.class)
        ).with_name("experiment_1");

//Give the second environment also a name and redirect any changes to the
//default SoFa to the "second_view". Also write the reproducible annotation
//generated by the environment into "third_view".


        AggregateBuilder builder2 = new AggregateBuilder();
        /*builder2.add(AnalysisEngineFactory.createEngineDescription(
                BreakIteratorSegmenter.class
        ));


        builder2.add(AnalysisEngineFactory.createEngineDescription(CoreNlpPosTagger.class));
        builder2.add(AnalysisEngineFactory.createEngineDescription(CamelCaseTokenSegmenter.class));
        builder2.add(AnalysisEngineFactory.createEngineDescription(CoreNlpNamedEntityRecognizer.class));*/
        AnalysisEngineDescription desc = builder2.createAggregateDescription();

        DockerWrappedEnvironment env2 = DockerWrappedEnvironment.from(desc).with_name("experiment_2")
                .with_pomfile(new File("pom.xml"));
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

        JCas test_view = JCasFactory.createJCas();
        test_view.setDocumentText("This is the first simple example.");
        test_view.setDocumentLanguage("en");

        List<DockerWrappedEnvironment> envs = DockerWrapperUtil.configurationsFromJCas(view_cas);
        for(DockerWrappedEnvironment i : envs) {
            if(i.get_name().equals("experiment_2")) {
                i.remove_sofa_mappings(CAS.NAME_DEFAULT_SOFA,"second_view");
                i.with_sofa_mapping(CAS.NAME_DEFAULT_SOFA,"third_view");
            }
        }
    }
}
