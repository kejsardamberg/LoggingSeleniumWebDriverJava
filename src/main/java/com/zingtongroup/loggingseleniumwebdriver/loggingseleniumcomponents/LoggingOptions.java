package com.zingtongroup.loggingseleniumwebdriver.loggingseleniumcomponents;

import com.zingtongroup.loggingseleniumwebdriver.logging.LoggerList;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.Logs;

import java.util.Set;

public class LoggingOptions implements WebDriver.Options, LoggingSeleniumComponent {

    public final LoggerList loggerList;
    public final WebDriver.Options options;

    public LoggingOptions(WebDriver.Options options, LoggerList loggerList) {
        this.loggerList = loggerList;
        this.options = options;
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

    public void addCookie(Cookie cookie) {
        loggerList.logExecutionStep("Adding cookie.");
        options.addCookie(cookie);
    }

    public void deleteCookieNamed(String name) {
        loggerList.logExecutionStep("Deleting cookie named '" + name + "'.");
        options.deleteCookieNamed(name);
    }

    public void deleteCookie(Cookie cookie) {
        loggerList.logExecutionStep("Deleting cookie.");
        options.deleteCookie(cookie);
    }

    public void deleteAllCookies() {
        loggerList.logExecutionStep("Deleting all cookies.");
        options.deleteAllCookies();
    }

    public Set<Cookie> getCookies() {
        Set<Cookie> cookies = options.getCookies();
        loggerList.logDebug("Retrieving " + cookies.size() + " cookies.");
        return cookies;
    }

    public Cookie getCookieNamed(String name) {
        Cookie cookie = options.getCookieNamed(name);
        loggerList.logDebug("Retrieving cookie named '" + name + "'.");
        return cookie;
    }

    public WebDriver.Timeouts timeouts() {
        return new LoggingTimeouts(options.timeouts(), loggerList);
    }

    public WebDriver.Window window() {
        return new LoggingWindow(options.window(), loggerList);
    }

    public Logs logs() {
        return options.logs();
    }
}
