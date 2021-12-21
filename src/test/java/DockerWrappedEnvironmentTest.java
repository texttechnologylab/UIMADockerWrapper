import DockerInterface.DockerWrappedEnvironment;
import DockerInterface.annotators.ExampleAnnotator;
import DockerInterface.base_env.DockerBaseJavaEnv;
import DockerInterface.util.AnnotatorDescription;
import com.bubelich.jBaseZ85;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.ZipEntry;

public class DockerWrappedEnvironmentTest {
    @Test
    void TestEnvironmentCreation() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        Assertions.assertEquals(env.get_compression(),"xz");
        Assertions.assertEquals(env.get_name(),"unnamed");
        Assertions.assertEquals(env.get_reproducible_annotation_target_view(),"");
        String defaultPom = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <groupId>org.hucompute</groupId>\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <version>1.0.0</version>\n" +
                "    <artifactId>reproduciblea_annotations</artifactId>\n" +
                "\n" +
                "\n" +
                "    <properties>\n" +
                "        <maven.compiler.source>8</maven.compiler.source>\n" +
                "        <maven.compiler.target>8</maven.compiler.target>\n" +
                "      </properties>\n" +
                "        <repositories>\n" +
                "                <repository>\n" +
                "                        <id>central</id>\n" +
                "                        <name>Central Repository</name>\n" +
                "                        <url>https://repo.maven.apache.org/maven2</url>\n" +
                "                        <layout>default</layout>\n" +
                "                        <snapshots>\n" +
                "                                <enabled>false</enabled>\n" +
                "                        </snapshots>\n" +
                "                </repository>\n" +
                "  </repositories>\n" +
                "  <dependencies>\n"+
                "  </dependencies>\n" +
                "</project>";
        Assertions.assertEquals(env.get_pomfile(), defaultPom);
        Assertions.assertEquals(env.get_dockerfile(),new DockerBaseJavaEnv().get_assembled_dockerfile());
        Assertions.assertEquals(env.get_recursive_descriptions().size(),1);
        Assertions.assertEquals(env.get_recursive_descriptions().get(0).get_name(),"DockerInterface.annotators.ExampleAnnotator");
    }

    @Test
    void TestEnvironmentAggregateHandling() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());

        //Two annotators are used
        Assertions.assertEquals(env.get_recursive_descriptions().size(),2);
        for(AnnotatorDescription ann : env.get_recursive_descriptions()) {
            Assertions.assertEquals(ann.get_name(),"DockerInterface.annotators.ExampleAnnotator");
        }
    }

    @Test
    void TestEnvironmentDeepAggregateHandling() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));

        AggregateBuilder deep = new AggregateBuilder();
        deep.add(AnalysisEngineFactory.createEngineDescription(OpenNlpSegmenter.class));
        deep.add(AnalysisEngineFactory.createEngineDescription(OpenNlpSegmenter.class));

        agg.add(deep.createAggregateDescription());
        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());

        //Four annotators are used
        Assertions.assertEquals(env.get_recursive_descriptions().size(),4);
        for(AnnotatorDescription ann : env.get_recursive_descriptions()) {
            Assertions.assertEquals(ann.get_name().equals("DockerInterface.annotators.ExampleAnnotator") || ann.get_name().equals(OpenNlpSegmenter.class.getName()),true);
        }
    }

    @Test
    void TestEnvironmentResourceSerialize() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());

        //Two ressources pom.xml and Dockerfile are the default
        Assertions.assertEquals(env.getRessources().length(),2);
        Assertions.assertNotEquals(env.getResourceString("dockerfile",""),"");
        Assertions.assertNotEquals(env.getResourceString("pom.xml",""),"");

        //Overwrite pom.xml ressource
        env.with_pomfile(new File("pom.xml"));
        Assertions.assertEquals(env.getResourceString("pom.xml"),new String(Files.readAllBytes(Paths.get("pom.xml"))));
    }

    @Test
    void TestEnvironmentResourceAddDirectory() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());

        //Overwrite pom.xml ressource
        env.withResource("src",new File("src"));
        Assertions.assertEquals(env.getRessources().length(),3);
        Assertions.assertNotEquals(env.getRessources().get("zip:src"),null);
    }

    @Test
    void TestEnvironmentResourceAddString() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());

        //Overwrite pom.xml ressource
        env.withResource("src","Hello World!");
        Assertions.assertEquals(env.getRessources().length(),3);
        Assertions.assertEquals(env.getRessources().get("string:src"),"Hello World!");
        Assertions.assertEquals(env.getResourceString("src"),"Hello World!");
    }

    @Test
    void TestEnvironmentResourceAddStringExtended() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());

        //Overwrite pom.xml ressource
        env.withResource("src",new File("pom.xml"));
        Assertions.assertEquals(env.getRessources().length(),3);
        Assertions.assertEquals(new String(env.getResourceBinary("src")),new String(Files.readAllBytes(Paths.get("pom.xml"))));
        String data = new String(Files.readAllBytes(Paths.get("pom.xml")));
        data = jBaseZ85.encode(data.getBytes(StandardCharsets.UTF_8));

        //Data should be Base85 encoded
        Assertions.assertEquals(env.getRessources().getString("binary:src"),data);
    }

    @Test
    void TestEnvironmentResourceAddDirectoryExtended() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());

        //Overwrite pom.xml ressource
        env.withResource("src",new File("src"));
        Assertions.assertEquals(env.getRessources().length(),3);


        //Data should be Base85 encoded
        Assertions.assertNotEquals(env.getRessources().getString("zip:src"),null);
        //Back to binary data
        byte[] byts = jBaseZ85.decode(env.getRessources().getString("zip:src"));
        ZipArchiveInputStream inp = new ZipArchiveInputStream(new ByteArrayInputStream(byts));
        ZipEntry zipEntry = inp.getNextZipEntry();
        while (zipEntry != null) {
            Assertions.assertEquals(zipEntry.getName().startsWith("src/"),true);
            zipEntry = inp.getNextZipEntry();
        }

        env.eraseResource("zip:src");
        Assertions.assertThrows(JSONException.class, () -> env.getRessources().getString("zip:src"));
    }

    @Test
    void TestEnvironmentAddSettings() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());
        env.with_name("fuchs");
        Assertions.assertEquals(env.get_name(),"fuchs");
    }

    @Test
    void TestEnvironmentAddCompression() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());
        env.with_compression(CompressorStreamFactory.DEFLATE);
        Assertions.assertEquals(env.get_compression(),CompressorStreamFactory.DEFLATE);
        Assertions.assertEquals(env.get_timestamp(),0);
    }

    @Test
    void TestEnvironmentBase64() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());

        //Overwrite pom.xml ressource
        env.withResource("src",new File("pom.xml"));
        Assertions.assertEquals(env.getRessources().length(),3);
        Assertions.assertEquals(new String(env.getResourceBinary("src")),new String(Files.readAllBytes(Paths.get("pom.xml"))));
        String data = new String(Files.readAllBytes(Paths.get("pom.xml")));
        data = Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));

        //Data should be Base85 encoded
        Assertions.assertEquals(env.getRessourcesBase64().getString("binary:src"),data);
        Assertions.assertNotEquals(env.getRessources().getString("binary:src"),data);
    }

    @Test
    void TestEnvironmentReadBinary() throws ResourceInitializationException, InvalidXMLException, IOException, SAXException {
        AggregateBuilder agg = new AggregateBuilder();
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));
        agg.add(AnalysisEngineFactory.createEngineDescription(ExampleAnnotator.class));

        DockerWrappedEnvironment env = DockerWrappedEnvironment.from(agg.createAggregateDescription());

        //Overwrite pom.xml ressource
        env.withResource("src",new File("pom.xml"));
        Assertions.assertEquals(env.getResourceBinary("src").length,Files.readAllBytes(Paths.get("pom.xml")).length);
    }

}
