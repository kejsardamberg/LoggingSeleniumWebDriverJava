package com.zingtongroup.seleniumextensions.loggingseleniumwebdriver;

import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.loggertypes.ConsoleLogger;
import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.logging.LogLevel;
import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.logging.Logger;
import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.logging.LoggerList;
import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.logging.LoggingSeleniumWebDriverException;
import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.loggingseleniumcomponents.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Wrapping class for any {@link WebDriver} instance to make it output description of what actions are performed.
 * Create custom {@link Logger} class for custom logging.
 * @author Jörgen Damberg
 */
public class LoggingSeleniumWebDriver implements WebDriver, LoggingSeleniumComponent {

    /**
     * List of {@link Logger} instances to log output to.
     */
    public LoggerList loggerList;

    /**
     * Raw {@link WebDriver} instance for non-logged interaction.
     */
    public WebDriver originalWebDriver;

    public LoggingSeleniumWebDriver(WebDriver webDriver) {
        this();
        loggerList.add(new ConsoleLogger());
        this.originalWebDriver = webDriver;
        log("Starting driver of type " + this.originalWebDriver.getClass().getName() + ".");
    }

    /**
     * Simple constructor mainly for testing purposes.
     * If used, remember to attach a WebDriver instance
     * with attachWebDriverInstance() and add some Logger
     * using addLogger();
     */
    public LoggingSeleniumWebDriver(){
        loggerList = new LoggerList();
    }

    /**
     * Re-assigns active {@link WebDriver} instance to keep consistent logging over multiple browser instances.
     * Note: You must use detachWebDriverInstance before this method to avoid throwing exception.
     * @param driver The new {@link WebDriver} instance to use.
     * @return Returns a new instance for chained command structure.
     * @throws LoggingSeleniumWebDriverException when not using detachWebDriverInstance before this.
     */
    public LoggingSeleniumWebDriver attachWebDriverInstance(WebDriver driver) throws LoggingSeleniumWebDriverException {
        if(originalWebDriver != null) throw new LoggingSeleniumWebDriverException("Attaching new driver instance when driver instance is already set require driver detach.");
        this.originalWebDriver = driver;
        logInfo("Starting driver of type " + this.originalWebDriver.getClass().getName() + ".");
        return this;
    }

    /**
     * Sets minimum log level to enable more or less verbose logging.
     *
     * @param logLevel LogLevel, for example from TestFlowLogLevel enum
     * @return Returns itself to enable chained commands
     */
    public LoggingSeleniumWebDriver setMinimumLogLevel(LogLevel logLevel){
        loggerList.minimumLogLevel = logLevel;
        return this;
    }

    /**
     * Used before attachWebDriverInstance() method to disconnect and change {@link WebDriver} instance for consistent logging over multiple browser instances.
     * @return Returns itself to enable chained commands
     */
    public LoggingSeleniumWebDriver detachWebDriverInstance(){
        originalWebDriver = null;
        return this;
    }

    /**
     * Add another {@link Logger} instance to the list of loggers to output to.
     * @param logger Any type of {@link Logger} instance implementing this interface.
     * @return Returns the same instance for chained command purposes.
     */
    public LoggingSeleniumWebDriver addLogger(Logger logger) {
        loggerList.add(logger);
        return this;
    }

    /**
     * Push a (TestFlowLogLevel.INFO) message to the log.
     * @param message The message to output in the log
     */
    public void log(String message) {
        loggerList.logInfo(message);
    }

    /**
     * Pushes a log message with DEBUG log level to registered {@link Logger} instances.
     * @param message The message to output in the log
     */
    public void logDebug(String message){
        loggerList.logDebug(message);
    }

    /**
     * Pushes a log message with EXCEPTION log level to registered {@link Logger} instances.
     * @param e Exception to log before being handled.
     */
    public void logException(Exception e){
        loggerList.logException(e);
    }

    /**
     * Pushes a log message with INFO log level to registered {@link Logger} instances.
     * @param message The message to output in the log
     */
    public void logInfo(String message){
        loggerList.logInfo(message);
    }

    /**
     * Pushes a log message with PASSED_VERIFICATION log level to registered {@link Logger} instances.
     * @param message The message to output in the log
     */
    public void logVerificationPassed(String message){
        loggerList.logVerificationPassed(message);
    }

    /**
     * Pushes a log message with VERIFICATION_PROBLEM log level to registered {@link Logger} instances.
     * @param message The message to output in the log
     */
    public void logVerificationProblem(String message){
        loggerList.logVerificationProblem(message);
    }

    /**
     * Pushes a log message with FAILED_VERIFICATION log level to registered {@link Logger} instances.
     * @param message The message to output in the log
     */
    public void logVerificationFailed(String message){
        loggerList.logVerificationFailed(message);
    }


    /**
     * Pushes a log message with EXECUTION_STEP log level to registered {@link Logger} instances.
     * @param message The message to output in the log
     */
    public void logExecutionStep(String message){
        loggerList.logExecutionStep(message);
    }

    /**
     * Suppresses logging until the resumeLogging() method is called.
     */
    public void pauseLogging() {
        loggerList.pauseLogging();
    }

    /**
     * Resumes logging after logging has been paused by the pauseLogging() method.
     */
    public void resumeLogging() {
        loggerList.resumeLogging();
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @param url The URL to navigate to
     */
    public void get(String url) {
        logExecutionStep("Navigating to '" + url + "'.");
        originalWebDriver.get(url);
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @return The current browser URL
     */
    public String getCurrentUrl() {
        String url = originalWebDriver.getCurrentUrl();
        logDebug("Retrieving current URL (='" + url + "').");
        return url;
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @return The current browser tab title
     */
    public String getTitle() {
        String title = originalWebDriver.getTitle();
        logDebug("Retrieving current page title (='" + title + "').");
        return title;
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @param by Selenium element identifier
     * @return List of matching elements
     */
    public List<WebElement> findElements(By by) {
        List<WebElement> elements = originalWebDriver.findElements(by);
        logDebug("Identifying " + elements.size() + " elements for By statement '" + by.toString() + "'.");
        List<WebElement> elements2 = new ArrayList<>();
        for (WebElement webElement : elements) {
            elements2.add(new LoggingWebElement(webElement, loggerList));
        }
        return elements2;
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @param by Selenium element identifier
     * @return First matching element
     */
    public WebElement findElement(By by) {
        WebElement element = originalWebDriver.findElement(by);
        if (element == null) {
            logDebug("Could not identify any element for By statement '" + by.toString() + "'.");
            return null;
        } else {
            logDebug("Identified element for By statement '" + by.toString() + "'.");
        }
        return new LoggingWebElement(element, loggerList);
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @return Current browser page source
     */
    public String getPageSource() {
        logInfo("Retrieving current page source.");
        return originalWebDriver.getPageSource();
    }

    /**
     * {@link WebDriver} native method, but with logging.
     */
    public void close() {
        logInfo("Closing web driver.");
        originalWebDriver.close();
    }

    /**
     * {@link WebDriver} native method, but with logging.
     */
    public void quit() {
        logInfo("Quitting web driver instance.");
        originalWebDriver.quit();
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @return Window handles
     */
    public Set<String> getWindowHandles() {
        Set<String> windowHandles = originalWebDriver.getWindowHandles();
        logDebug("Retrieved " + windowHandles.size() + " window handles.");
        return windowHandles;
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @return Window handle
     */
    public String getWindowHandle() {
        String handle = originalWebDriver.getWindowHandle();
        logDebug("Identified current window handle (='" + handle + "').");
        return handle;
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @return Locator
     */
    public WebDriver.TargetLocator switchTo() {
        return new LoggingTargetLocator(originalWebDriver.switchTo(), loggerList);
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @return Navigation entity
     */
    public WebDriver.Navigation navigate() {
        return new LoggingNavigation(originalWebDriver.navigate(), loggerList);
    }

    /**
     * {@link WebDriver} native method, but with logging.
     * @return Options entity
     */
    public WebDriver.Options manage() {
        return new LoggingOptions(originalWebDriver.manage(), loggerList);
    }

    /**
     * {@link LoggingSeleniumWebDriver} constructor from builder pattern.
     * Make sure to finish with the build() method.
     */
    public static class Builder {

        private WebDriver driver;
        private final LoggerList loggerList;
        private LogLevel minimumLogLevel;

        public Builder() {
            loggerList = new LoggerList();
            minimumLogLevel = null;
            driver = null;
        }

        public Builder addLogger(Logger logger) {
            loggerList.add(logger);
            return this;
        }

        public Builder attachWebDriverInstance(WebDriver webDriver) {
            driver = webDriver;
            return this;
        }

        public Builder removeAllLoggers() {
            loggerList.removeAllLoggers();
            return this;
        }

        public Builder startWithLoggingPaused() {
            loggerList.pauseLogging();
            return this;
        }

        public LoggingSeleniumWebDriver build() {
            LoggingSeleniumWebDriver loggingSeleniumWebDriver = new LoggingSeleniumWebDriver(driver);
            loggingSeleniumWebDriver.loggerList = loggerList;
            if (minimumLogLevel != null) loggingSeleniumWebDriver.setMinimumLogLevel(minimumLogLevel);
            return loggingSeleniumWebDriver;
        }

        public Builder setMinimumLogLevel(LogLevel minimumLogLevel) {
            this.minimumLogLevel = minimumLogLevel;
            return this;
        }
    }
}
