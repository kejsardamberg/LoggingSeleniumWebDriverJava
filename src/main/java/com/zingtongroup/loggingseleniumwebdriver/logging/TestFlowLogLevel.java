package com.zingtongroup.loggingseleniumwebdriver.logging;

public enum TestFlowLogLevel implements LogLevel {
    DEBUG(0),
    INFO(1),
    EXECUTION_STEP(2),
    EXCEPTION(7);

    private final int value;

    TestFlowLogLevel(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
