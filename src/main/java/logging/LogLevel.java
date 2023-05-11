package logging;

public enum LogLevel {
    DEBUG(0),
    INFO(1),
    EXECUTION_STEP(2),
    EXCEPTION(3);

    public final int value;

    LogLevel(int value) {
        this.value = value;
    }
}
