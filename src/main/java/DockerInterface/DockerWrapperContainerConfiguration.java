package DockerInterface;

import DockerInterface.database.DatabaseInterface;
import DockerInterface.database.DatabaseSQLite;
import DockerInterface.modules.IDockerWrapperModule;
import org.apache.uima.cas.SerialFormat;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains the whole container configuration is only needed if the run in container flag ist true.
 */
public class DockerWrapperContainerConfiguration {
    /**
     * Checks the integrity of every given CAS document
     */
    private boolean _confirm_integrity;

    /**
     * Runs the given AnalysisEngine only in the container if this flag is true. Can be important if the implementation already
     * runs inside a container.
     */
    private boolean _run_in_container;

    /**
     * The container name if one wants to give the created container a name
     */
    private String _container_name;

    /**
     * Enables the GPU usage when this flag is set to true, only works when the container is not started already
     */
    private boolean _use_gpu;

    /**
     * Autoremoves the container on stopping it at the end. This will only work if the container is not started already.
     */
    private boolean _autoremove;

    /**
     * Recursively map the docker daemon inside child container, this enables deep nesting but will only work if the container is
     * not running already.
     */
    private boolean _map_daemon;

    /**
     * Exports the container at the end of execution.
     */
    private String _export_name;

    /**
     * Checks if a container with the same name exists and if so, starts this container for the communication.
     */
    private boolean _reuse_container;

    /**
     * The classes of the used modules which can be used to track performance and a lot more.
     */
    private List<String> _module_classes;
    /**
     * Every module gets a configuration string, this is the specified configuration string for every module.
     */
    private List<String> _module_configs;

    /**
     * A timeout for the container intialisation. This will throw an exception if the container does not finish the startup in the specified time
     */
    private int _container_initialise_timeout;

    /**
     * This is used to connect to a running container and saves the container id to connect to.
     */
    private String _running_container_id;

    /**
     * Defines how the pipeline should be scaled.
     */
    private int _scale;

    /**
     * Defines how the pipeline should be scaled.
     */
    private ScaleoutType _scale_type;

    /**
     * Creates the default DockerWrapperConfiguration. This includes confirm_integrity = true, run in container = true,
     * container name = "", use gpu = true, autoremove = true, export name = "", reuse container = false, module classes empty,
     * initialise timeout = 20, running container id = "", map daemon = false
     * @return
     */
    static public DockerWrapperContainerConfiguration default_config() {
        try {
            return new DockerWrapperContainerConfiguration();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates the basic container configuration with a set of sensible defaults:
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    DockerWrapperContainerConfiguration() throws SQLException, ClassNotFoundException {
        _confirm_integrity = false;
        _run_in_container = true;
        _container_name = "";
        _use_gpu = true;
        _autoremove = true;
        _export_name = "";
        _reuse_container = false;
        _module_classes = new LinkedList<String>();
        _module_configs = new LinkedList<String>();
        _container_initialise_timeout = 20;
        _running_container_id = "";
        _map_daemon = false;
        _scale_type = ScaleoutType.SHARED_ANNOTATOR;
        _scale = 1;
    }

    /**
     * Initialises the DockerWrapperContainerConfiguration from a JSON object.
     * @param js The object to intialise from.
     */
    DockerWrapperContainerConfiguration(JSONObject js) {
        _confirm_integrity = js.getBoolean("confirm_integrity");
        _run_in_container = js.getBoolean("run_in_container");
        _container_name = js.getString("container_name");
        _use_gpu = js.getBoolean("use_gpu");
        _autoremove = js.getBoolean("autoremove");
        _export_name = js.getString("export_name");
        _reuse_container = js.getBoolean("reuse");
        _container_initialise_timeout = js.getInt("timeout");
        _running_container_id = js.getString("container_address");
        _map_daemon = js.getBoolean("map_daemon");
        _scale = js.getInt("scaleout");
        _scale_type = ScaleoutType.valueOf(js.getString("scaletype"));

        _module_classes = new LinkedList<String>();
        JSONArray arr = js.getJSONArray("modules");
        for(int i = 0; i < arr.length(); i++) {
            _module_classes.add(arr.getString(i));
        }

        _module_configs = new LinkedList<String>();
        JSONArray arr2 = js.getJSONArray("module_configs");
        for(int i = 0; i < arr2.length(); i++) {
            _module_configs.add(arr2.getString(i));
        }
    }

    /**
     * Serializes this object to a JSON string.
     * @return The JSON representation of this object.
     */
    public String toJsonString() {
        JSONObject obj = new JSONObject();
        obj.put("confirm_integrity",_confirm_integrity);
        obj.put("run_in_container",_run_in_container);
        obj.put("container_name",_container_name);
        obj.put("use_gpu",_use_gpu);
        obj.put("autoremove",_autoremove);
        obj.put("export_name",_export_name);
        obj.put("reuse",_reuse_container);
        obj.put("modules",_module_classes);
        obj.put("module_configs",_module_configs);
        obj.put("timeout",_container_initialise_timeout);
        obj.put("container_address",_running_container_id);
        obj.put("map_daemon",_map_daemon);
        obj.put("scalout",_scale);
        obj.put("scaletype",_scale_type.name());
        return obj.toString();
    }

    /**
     * Adds a module which receives notification based on the state of the pipeline
     * @param clazz The class which extends the Module interface, this class must have a constructor with no arguments which is public
     * @param configuration The configuration parameter, any string can be given here. Should not contain unescaped double quotes
     * @return A reference to itself for function chaining.
     */
    public DockerWrapperContainerConfiguration with_module(Class<? extends IDockerWrapperModule> clazz, String configuration) {
        _module_classes.add(clazz.getName());
        _module_configs.add(configuration);
        return this;
    }

    /**
     * Returns a list of the module configuration string, If was given when adding the module this contains an empty string instead.
     * @return The List of module configuration strings.
     */
    public List<String> get_module_configurations() {
        return _module_configs;
    }

    /**
     * Adds the module to the list of used modules, this can get serialized and deserialized as long as the class is available
     * in the classpath.
     * @param clazz The class which extends the IDockerWrapperModule class needs to have a public constructor with no arguments.
     * @return A reference to itself for easier function chaining.
     */
    public DockerWrapperContainerConfiguration with_module(Class<? extends IDockerWrapperModule> clazz) {
        _module_classes.add(clazz.getName());
        _module_configs.add("");
        return this;
    }

    public DockerWrapperContainerConfiguration with_scaleout(int factor) {
        _scale = factor;
        return this;
    }

    public int getContainerScalout() {
        return _scale;
    }

    public ScaleoutType getContainerScaleType() {
        return _scale_type;
    }

    public DockerWrapperContainerConfiguration withScaleType(ScaleoutType scaleType) {
        _scale_type = scaleType;
        return this;
    }

    /**
     * Returns the container initialise timeout, this can be adjusted according to the needs of the programmer.
     * @return Returns the container initialise timeout.
     */
    public int get_container_initialise_timeout() {
        return _container_initialise_timeout;
    }

    /**
     * Returns a list of additional module classes which are loaded on initialise.
     * @return The list of additional module classes.
     */
    public List<String> get_additional_modules() {
        return _module_classes;
    }

    /**
     * Getter for the state of confirm integrity, if set to true every DockerWrapper will confirm the integrity of the CAS document
     * @return The status of the confirm integrity class
     */
    public boolean get_confirm_integrity() {
        return _confirm_integrity;
    }

    /**
     * Returns the use gpu flag. If a new container is created all available GPUs are mapped inside the container.
     * @return The gpu flag status.
     */
    public boolean get_use_gpu() {
        return _use_gpu;
    }

    /**
     * Gets the reuse container flag. This flag indicates if a container is reused. When this flag is set to true, if there
     * exists a container on the host system with the same name as this one, use the existing instead.
     * @return
     */
    public boolean get_reuse_container() {
        return _reuse_container;
    }

    /**
     * This flag indicates if a container should be build an used for the execution of this framework.
     * @return The flag status of the run in container flag.
     */
    public boolean get_run_in_container() {
        return _run_in_container;
    }

    /**
     * Returns the currently set container name. When a new container is built this is the container name used. When the reuse
     * flag is set to true, searches for a container with the same name as this one.
     * @return The container name.
     */
    public String get_containername() {
        return _container_name;
    }

    /**
     * Returns the set export name which indicates to what image the container is exported to at the end.
     * @return The export name.
     */
    public String get_export_name() {
        return _export_name;
    }

    /**
     * This is the docker autoremove flag if this is set the container is removed upon stopping, if this is false the container
     * persists after it is stopped.
     * @return The autoremove flag status/
     */
    public boolean get_autoremove() {
        return _autoremove;
    }

    /**
     * Sets the confirm integrity flag
     * @param confirm_integrity The new value of the confirm integrity flag
     * @return A reference to itself for easier function chaining.
     */
    public DockerWrapperContainerConfiguration with_confirm_integrity(boolean confirm_integrity) {
        _confirm_integrity = confirm_integrity;
        return this;
    }

    /**
     * Sets the use gpu flag.
     * @param gpu The new flag status
     * @return A reference to itself for easier function chaining.
     * @throws IOException
     */
    public DockerWrapperContainerConfiguration with_gpu(boolean gpu) throws IOException {
        _use_gpu = gpu;
        return this;
    }

    /**
     * Sets the docker container initialise timeout
     * @param timeout_seconds The new timeout in seconds default is 20.
     * @return A reference to itself for easier function chaining.
     * @throws IOException
     */
    public DockerWrapperContainerConfiguration with_container_initialise_timeout(int timeout_seconds) throws IOException {
        _container_initialise_timeout = timeout_seconds;
        return this;
    }

    /**
     * Sets the container autoremove flag
     * @param autoremove The new flag status of the autoremove flag
     * @return A reference to itself for easier function chaining.
     * @throws IOException
     */
    public DockerWrapperContainerConfiguration with_container_autoremove(boolean autoremove) throws IOException {
        _autoremove = autoremove;
        return this;
    }


    /**
     * Sets the new container name
     * @param name The new name of the container
     * @return A reference to itself for easier function chaining
     * @throws IOException
     */
    public DockerWrapperContainerConfiguration with_container_name(String name) throws IOException {
        _container_name = name;
        return this;
    }

    /**
     * Sets the reuse container flag
     * @param reuse The new reuse container flag status
     * @return A reference to itself for better function chaining.
     * @throws IOException
     */
    public DockerWrapperContainerConfiguration with_reuse_container(boolean reuse) throws IOException {
        _reuse_container = reuse;
        return this;
    }

    /**
     * Sets the run in container flag
     * @param value The new run in container flag status.
     * @return A reference to itself for better function chaining.
     */
    public DockerWrapperContainerConfiguration with_run_in_container(boolean value) {
        _run_in_container = value;
        return this;
    }

    /**
     * Enables the usage of an already running container, this is marked unsafe as the container is responsible for making
     * reproducible annotations and this cannot get confirmed by the implementation
     * @param address The container id
     * @return A reference to itself for function chaining
     */
    public DockerWrapperContainerConfiguration with_unsafe_running_container_id(String address) {
        _running_container_id = address;
        return this;
    }

    /**
     * Maps the docker daemon recursively into child containers, this is unsafe as it has serious security implications for the
     * host system.
     * @param map_daemon The new flag status of the map daemon flag
     * @return A reference to itself for better function chaining
     */
    public DockerWrapperContainerConfiguration with_unsafe_map_docker_daemon(boolean map_daemon) {
        _map_daemon = map_daemon;
        return this;
    }

    /**
     * Returns the map daemon docker status flag
     * @return The current map daemon status flag.
     */
    public boolean get_unsafe_map_docker_daemon() {
        return _map_daemon;
    }

    /**
     * Returns the running container id is empty by default
     * @return The running container id.
     */
    public String get_unsafe_running_container_id() {
        return _running_container_id;
    }

    /**
     * Exports the running container to a new image at the end of execution
     * @param repository_name The repository name to save the image to
     * @param tag_name The tag name to save the image to
     * @return A reference to itself for better function chaining.
     */
    public DockerWrapperContainerConfiguration with_export_to_new_image(String repository_name, String tag_name) {
        _export_name = repository_name+"!"+tag_name;
        return this;
    }
}
