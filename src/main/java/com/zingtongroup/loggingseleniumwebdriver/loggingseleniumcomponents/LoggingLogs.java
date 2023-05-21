package com.zingtongroup.loggingseleniumwebdriver.loggingseleniumcomponents;

import com.zingtongroup.loggingseleniumwebdriver.logging.LoggerList;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.Logs;

import java.util.Set;

public class LoggingLogs implements Logs, LoggingSeleniumComponent {
    public LoggerList loggerList;
    public Logs logs;

    public LoggingLogs(Logs logs, LoggerList loggerList) {
        this.loggerList = loggerList;
        this.logs = logs;
    }

    public void pauseLogging() {
        loggerList.pauseLogging();
    }

    public void resumeLogging() {
        loggerList.resumeLogging();
    }

    public void log(String logMessage) {
        loggerList.log(logMessage);
    }

    public LogEntries get(String logType) {
        loggerList.logDebug("Retrieving log type '" + logType + "'.");
        return logs.get(logType);
    }

    public Set<String> getAvailableLogTypes() {
        Set<String> logTypes = logs.getAvailableLogTypes();
        loggerList.logDebug("Retrieving names of " + logTypes.size() + " log types (" + String.join(", ", logTypes) + ").");
        return logTypes;
    }
}
