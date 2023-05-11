import loggertypes.ConsoleLogger;
import logging.*;
import loggingseleniumcomponents.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoggingSeleniumWebDriver implements WebDriver, LoggingSeleniumComponent {

    public LoggerList loggerList;
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

    public LoggingSeleniumWebDriver attachWebDriverInstance(WebDriver driver) throws LoggingSeleniumWebDriverException {
        if(originalWebDriver != null) throw new LoggingSeleniumWebDriverException("Attaching new driver instance when driver instance is already set require driver detach.");
        this.originalWebDriver = driver;
        logInfo("Starting driver of type " + this.originalWebDriver.getClass().getName() + ".");
        return this;
    }

    public LoggingSeleniumWebDriver setMinimumLogLevel(LogLevel logLevel){
        loggerList.minimumLogLevel = logLevel;
        return this;
    }

    public LoggingSeleniumWebDriver detachWebDriverInstance(){
        originalWebDriver = null;
        return this;
    }

    public LoggingSeleniumWebDriver addLogger(Logger logger) {
        loggerList.add(logger);
        return this;
    }

    public void log(String message) {
        loggerList.logInfo(message);
    }

    public void logDebug(String message){
        loggerList.logDebug(message);
    }

    public void logException(Exception e){
        loggerList.logException(e);
    }

    public void logInfo(String message){
        loggerList.logInfo(message);
    }

    public void logExecutionStep(String message){
        loggerList.logExecutionStep(message);
    }

    public void pauseLogging() {
        loggerList.pauseLogging();
    }

    public void resumeLogging() {
        loggerList.resumeLogging();
    }

    public void get(String url) {
        logExecutionStep("Navigating to '" + url + "'.");
        originalWebDriver.get(url);
    }

    public String getCurrentUrl() {
        String url = originalWebDriver.getCurrentUrl();
        logDebug("Retrieving current URL (='" + url + "').");
        return url;
    }

    public String getTitle() {
        String title = originalWebDriver.getTitle();
        logDebug("Retrieving current page title (='" + title + "').");
        return title;
    }

    public List<WebElement> findElements(By by) {
        List<WebElement> elements = originalWebDriver.findElements(by);
        logDebug("Identifying " + elements.size() + " elements for By statement '" + by.toString() + "'.");
        List<WebElement> elements2 = new ArrayList<>();
        for (WebElement webElement : elements) {
            elements2.add(new LoggingWebElement(webElement, loggerList));
        }
        return elements2;
    }

    public WebElement findElement(By by) {
        WebElement element = originalWebDriver.findElement(by);
        if (element == null) {
            logDebug("Could not identify any element for By statement '" + by.toString() + "'.");
        } else {
            logDebug("Identified element for By statement '" + by.toString() + "'.");
        }
        return new LoggingWebElement(element, loggerList);
    }

    public String getPageSource() {
        logInfo("Retrieving current page source.");
        return originalWebDriver.getPageSource();
    }

    public void close() {
        logInfo("Closing web driver.");
        originalWebDriver.close();
    }

    public void quit() {
        logInfo("Quitting web driver instance.");
        originalWebDriver.quit();
    }

    public Set<String> getWindowHandles() {
        Set<String> windowHandles = originalWebDriver.getWindowHandles();
        logDebug("Retrieved " + windowHandles.size() + " window handles.");
        return windowHandles;
    }

    public String getWindowHandle() {
        String handle = originalWebDriver.getWindowHandle();
        logDebug("Identified current window handle (='" + handle + "').");
        return handle;
    }

    public WebDriver.TargetLocator switchTo() {
        return new LoggingTargetLocator(originalWebDriver.switchTo(), loggerList);
    }

    public WebDriver.Navigation navigate() {
        return new LoggingNavigation(originalWebDriver.navigate(), loggerList);
    }

    public WebDriver.Options manage() {
        return new LoggingOptions(originalWebDriver.manage(), loggerList);
    }


    public static class Builder{

        private WebDriver driver;
        private LoggerList loggerList;
        private LogLevel mimimumLogLevel;

        public Builder(){
            loggerList = new LoggerList();
            mimimumLogLevel = null;
            driver = null;
        }

        public Builder addLogger(Logger logger){
            loggerList.add(logger);
            return this;
        }

        public Builder attachWebDriverInstance(WebDriver webDriver){
            driver = webDriver;
            return this;
        }

        public Builder startWithLoggingPaused(){
            loggerList.pauseLogging();
            return this;
        }

        public LoggingSeleniumWebDriver build() {
            LoggingSeleniumWebDriver loggingSeleniumWebDriver = new LoggingSeleniumWebDriver(driver);
            loggingSeleniumWebDriver.loggerList = loggerList;
            if(mimimumLogLevel != null) loggingSeleniumWebDriver.setMinimumLogLevel(mimimumLogLevel);
            return loggingSeleniumWebDriver;
        }

        public Builder setMinimumLogLevel(LogLevel mimimumLogLevel) {
            this.mimimumLogLevel = mimimumLogLevel;
            return this;
        }
    }
}
