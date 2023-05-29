package com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.logging;

public enum TestFlowLogLevel implements LogLevel {
    DEBUG(0),
    INFO(1),
    EXECUTION_STEP(2),
    PASSED_VERIFICATION(3),
    VERIFICATION_PROBLEM(4),
    FAILED_VERIFICATION(5),
    EXECUTION_PROBLEM(6),
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
