package logging;

public enum LogLevel {
    DEBUG(0),
    INFO(1),
    EXECUTION_STEP(2),
    PASSED_VERIFICATION(4),
    FAILED_VERIFICATION(5),
    VERIFICATION_PROBLEM(6),
    EXCEPTION(7);

    public final int value;

    LogLevel(int value) {
        this.value = value;
    }
}
