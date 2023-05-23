package com.zingtongroup.loggingseleniumwebdriver.logging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LoggerList extends ArrayList<Logger> {

    public boolean isPaused;
    public LogLevel minimumLogLevel;

    public LoggerList() {
        isPaused = false;
        minimumLogLevel = TestFlowLogLevel.INFO;
    }

    public void setMinimumLogLevel(LogLevel minimumLogLevel) {
        this.minimumLogLevel = minimumLogLevel;
    }

    private void addLogPostToLoggers(LogPost logPost) {
        for (Logger logger : this) {
            try {
                logger.addLogPost(logPost);
            } catch (Exception e) {
                for (Logger l : this) {
                    if (l.equals(logger)) continue;
                    l.addLogPost(new LogPost(TestFlowLogLevel.EXCEPTION, e.toString()));
                }
                throw e;
            }
        }
    }

    public void logException(Exception e){
        LogPost logPost = new LogPost(TestFlowLogLevel.EXCEPTION, e.getMessage());
        addLogPostToLoggers(logPost);
    }

    public void logDebug(String logMessage) {
        if (isPaused) return;
        LogPost logPost = new LogPost(TestFlowLogLevel.DEBUG, logMessage);
        if (logPost.logLevel.getValue() < minimumLogLevel.getValue()) return;
        addLogPostToLoggers(logPost);
    }

    public void logInfo(String logMessage) {
        if (isPaused) return;
        LogPost logPost = new LogPost(TestFlowLogLevel.INFO, logMessage);
        if (logPost.logLevel.getValue() < minimumLogLevel.getValue()) return;
        addLogPostToLoggers(logPost);
    }

    public void logExecutionStep(String logMessage) {
        if (isPaused) return;
        LogPost logPost = new LogPost(TestFlowLogLevel.EXECUTION_STEP, logMessage);
        if (logPost.logLevel.getValue() < minimumLogLevel.getValue()) return;
        addLogPostToLoggers(logPost);
    }

    public void log(String logMessage) {
        if (isPaused) return;
        LogPost logPost = new LogPost(TestFlowLogLevel.EXECUTION_STEP, logMessage);
        if (logPost.logLevel.getValue() < minimumLogLevel.getValue()) return;
        addLogPostToLoggers(logPost);
    }

    public Set<String> getLoggerTypes() {
        Set<String> loggerNames = new HashSet<>();
        for (Logger logger : this) {
            if (loggerNames.contains(logger.getClass().getName())) continue;
            loggerNames.add(logger.getClass().getName());
        }
        return loggerNames;
    }

    public Logger getLoggerOfType(String typeName) {
        for (Logger logger : this) {
            if (logger.getClass().getName().equals(typeName)) {
                return logger;
            }
        }
        return null;
    }

    public void removeAllLoggers() {
        this.clear();
    }

    public Logger getLoggerOfType(Class<?> type) {
        for (Logger logger : this) {
            if (logger.getClass().isAssignableFrom(type)) {
                return logger;
            }
        }
        return null;
    }

    public void pauseLogging() {
        isPaused = true;
    }

    public void resumeLogging() {
        isPaused = false;
    }
}
