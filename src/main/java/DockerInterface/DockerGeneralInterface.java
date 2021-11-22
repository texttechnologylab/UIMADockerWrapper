package DockerInterface;
import com.fasterxml.jackson.core.JsonParser;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.exception.DockerClientException;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.CreateContainerCmdImpl;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.google.common.collect.ImmutableList;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.impl.client.HttpClients;
import org.xml.sax.SAXException;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * This code is based on the code from the docker api package
 */
class BuildImageProgress extends BuildImageResultCallback {
    private String imageId;
    private String error;

    @Override
    public void onNext(BuildResponseItem item) {
        if (item.isBuildSuccessIndicated()) {
            this.imageId = item.getImageId();
        } else if (item.isErrorIndicated()) {
            this.error = item.getError();
        }
        if(item.getStream()!=null) {
            System.out.print(item.getStream());
        }
    }

    /**
     * Awaits the image id from the response stream.
     *
     * @throws DockerClientException
     *             if the build fails.
     */
    public String awaitImageId() {
        try {
            awaitCompletion();
        } catch (InterruptedException e) {
            throw new DockerClientException("", e);
        }

        return getImageId();
    }

    /**
     * Awaits the image id from the response stream.
     *
     * @throws DockerClientException
     *             if the build fails or the timeout occurs.
     */
    public String awaitImageId(long timeout, TimeUnit timeUnit) {
        try {
            awaitCompletion(timeout, timeUnit);
        } catch (InterruptedException e) {
            throw new DockerClientException("Awaiting image id interrupted: ", e);
        }

        return getImageId();
    }

    private String getImageId() {
        if (imageId != null) {
            return imageId;
        }

        if (error == null) {
            throw new DockerClientException("Could not build image");
        }

        throw new DockerClientException("Could not build image: " + error);
    }
}

/**
 * This is the general docker interface which interacts with the docker daemon.
 */
public class DockerGeneralInterface {
    /**
     * The connection to the docker client.
     */
    DockerClient _docker;


    /**
     * Creates a default object which connects to the local docker daemon, may need admin rights depending on the docker installation
     * @throws IOException
     */
    public DockerGeneralInterface() throws IOException {
        _docker = DockerClientBuilder.getInstance().build();
    }

    /**
     * Extracts port mapping from the container with the given containerid, this is important since docker does auto allocate
     * ports when not explicitly specifying the port number. This will only work on a DockerWrapper constructed container.
     * @param containerid The running containerid to read the port mapping from
     * @return The port it was mapped to.
     * @throws InterruptedException
     */
    public int extract_port_mapping(String containerid) throws InterruptedException {
        InspectContainerResponse container
                = _docker.inspectContainerCmd(containerid).exec();

        int innerport = 0;
        for(Map.Entry<ExposedPort, Ports.Binding[]> port : container.getNetworkSettings().getPorts().getBindings().entrySet()) {
            if(port.getValue().length > 0 && port.getKey().getPort() == 9714) {
                innerport = Integer.parseInt(port.getValue()[0].getHostPortSpec());
            }
        }

        System.out.printf("Detected port: %d\n",innerport);
        return innerport;
    }

    /**
     * Returns true if the code is run inside the container and false otherwise.
     * @return true if in container false otherwise
     */
    public boolean inside_container() {
        if(new File("/.dockerenv").exists()) {
            return true;
        }
        return false;
    }

    /**
     * Reads the container gateway bridge ip if inside the container to enable communication between sibling containers or the
     * localhost ip if one is the host.
     * @return The ip address.
     */
    public String get_ip() {
        if(inside_container()) {
            Network net = _docker.inspectNetworkCmd().withNetworkId("docker_gwbridge").exec();
            return net.getIpam().getConfig().get(0).getGateway();
        }
        return "127.0.0.1";
    }

    /**
     * Reads the logs from the container to determine if the container has started up without errors
     * @param containerid The container id to check the logs from
     * @return The string representation of the read logs
     * @throws InterruptedException
     */
    public String get_logs(String containerid) throws InterruptedException {
        final List<String> logs = new ArrayList<>();
        InspectContainerResponse container
                = _docker.inspectContainerCmd(containerid).exec();
        _docker.logContainerCmd(containerid).withContainerId(containerid).withStdOut(true).withStdErr(true)
                        .withTimestamps(true).withTail(5).exec(new LogContainerResultCallback() {
            @Override
            public void onNext(Frame item) {
                logs.add(item.toString());
            }
        }).awaitCompletion();
        String completelog = "";
        for(String x : logs) {
            completelog+=x;
        }
        return completelog;
    }

    /**
     * Stops the container with the given container id
     * @param id The id of the container to stop.
     */
    public void stop_container(String id) {
        _docker.stopContainerCmd(id).withTimeout(10).exec();
    }

    /**
     * Exports a running container to a new image.
     * @param containerid The containerid to commit to a new image
     * @param imagename The image name in the format "repository!imagename"
     */
    public void export_to_new_image(String containerid, String imagename) {
        if(!imagename.equals("")) {
            String split[] = imagename.split("!");
            _docker.commitCmd(containerid).withRepository(split[0]).withTag(split[1]).exec();
        }
    }

    /**
     * Builds and runs the container with a specified temporary build directory and some flags.
     * @param builddir The temporary build directory to build the container from
     * @param gpu If the gpu should be used
     * @param autoremove If the autoremove flag is set for the container
     * @param reuse_container If the reuse container flag is used
     * @param containername The container name for the container
     * @param map_daemon If a recursive mapping of the docker daemon should take place
     * @return The docker container id
     * @throws InterruptedException
     */
    public String build_and_run(Path builddir, boolean gpu, boolean autoremove, boolean reuse_container, String containername,
                                boolean map_daemon) throws InterruptedException {
        if(reuse_container) {
            List<Container> containers = _docker.listContainersCmd().withShowAll(true).exec();
            for (Container cont : containers) {
                for(String name : cont.getNames()) {
                    if(name.equals(containername)) {
                        _docker.startContainerCmd(cont.getId()).exec();
                        return cont.getId();
                    }
                }
            }
        }


        String img_id = _docker.buildImageCmd().withPull(true)
                .withBaseDirectory(builddir.toFile())
                .withDockerfile(Paths.get(builddir.toString(),"dockerfile").toFile())
                .exec(new BuildImageProgress()).awaitImageId();

        HostConfig cfg = new HostConfig();
        if(autoremove) {
            cfg = cfg.withAutoRemove(true);
        }
        if(gpu) {
            cfg = cfg.withDeviceRequests(ImmutableList.of(new DeviceRequest()
                    .withCapabilities(ImmutableList.of(ImmutableList.of("gpu")))));
        }

        if(map_daemon) {
            cfg.withBinds(Bind.parse("/var/run/docker.sock:/var/run/docker.sock"));
        }

        CreateContainerCmd cmd = _docker.createContainerCmd(img_id)
                .withHostConfig(cfg)
                .withExposedPorts(ExposedPort.tcp(9714)).withPublishAllPorts(true);

        if(!containername.equals("")) {
            cmd = cmd.withName(containername);
        }

        CreateContainerResponse feedback = cmd.exec();
        _docker.startContainerCmd(feedback.getId()).exec();
        return feedback.getId();
    }
}
