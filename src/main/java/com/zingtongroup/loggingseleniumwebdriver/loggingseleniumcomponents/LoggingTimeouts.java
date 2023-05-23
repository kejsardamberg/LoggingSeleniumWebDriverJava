package com.zingtongroup.loggingseleniumwebdriver.loggingseleniumcomponents;

import com.zingtongroup.loggingseleniumwebdriver.logging.LoggerList;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoggingTimeouts implements WebDriver.Timeouts, LoggingSeleniumComponent {
    public final LoggerList loggerList;
    public final WebDriver.Timeouts timeouts;

    public LoggingTimeouts(WebDriver.Timeouts timeouts, LoggerList loggerList) {
        this.loggerList = loggerList;
        this.timeouts = timeouts;
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

    public WebDriver.Timeouts implicitlyWait(long time, TimeUnit unit) {
        loggerList.logInfo("Setting implicitly wait to " + time + " " + unit.toString().toLowerCase() + ".");
        return timeouts.implicitlyWait(time, unit);
    }

    public WebDriver.Timeouts setScriptTimeout(long time, TimeUnit unit) {
        loggerList.logInfo("Setting script timeout to " + time + " " + unit.toString().toLowerCase() + ".");
        return timeouts.setScriptTimeout(time, unit);
    }

    public WebDriver.Timeouts pageLoadTimeout(long time, TimeUnit unit) {
        loggerList.logInfo("Setting page load timeout to " + time + " " + unit.toString().toLowerCase() + ".");
        return timeouts.pageLoadTimeout(time, unit);
    }
}
