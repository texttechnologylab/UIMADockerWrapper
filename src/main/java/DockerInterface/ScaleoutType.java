package DockerInterface;

public enum ScaleoutType {
    SHARED_ANNOTATOR(0),
    DUPLICATED_ANNOTATOR(1);

    private final int value;

    ScaleoutType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
