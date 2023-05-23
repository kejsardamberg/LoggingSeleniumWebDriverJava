package com.zingtongroup.loggingseleniumwebdriver.logging;

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
    private final String evenLengthStringRepresentation;

    TestFlowLogLevel(int value) {
        this.value = value;
        int longestStringLength = 0;
        for(TestFlowLogLevel level : TestFlowLogLevel.values()){
            if(level.toString().length() > longestStringLength){
                longestStringLength = level.toString().length();
            }
        }
        this.evenLengthStringRepresentation = toString() + " ".repeat(Math.max(0, (longestStringLength + 1) - toString().length()));
    }

    @Override
    public String toString(){
        return evenLengthStringRepresentation;
    }

    @Override
    public int getValue() {
        return value;
    }
}
