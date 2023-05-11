package loggingseleniumcomponents;

import logging.LoggerList;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

public class LoggingTargetLocator implements WebDriver.TargetLocator, LoggingSeleniumComponent {

    public LoggerList loggerList;
    public WebDriver.TargetLocator targetLocator;

    public LoggingTargetLocator(WebDriver.TargetLocator targetLocator, LoggerList loggerList) {
        this.loggerList = loggerList;
        this.targetLocator = targetLocator;
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

    public WebDriver frame(int index) {
        loggerList.logInfo("Switching to Frame with index " + index + ".");
        return targetLocator.frame(index);
    }

    public WebDriver frame(String nameOrId) {
        loggerList.logInfo("Switching to Frame with name or id " + nameOrId + ".");
        return targetLocator.frame(nameOrId);
    }

    public WebDriver frame(WebElement frameElement) {
        loggerList.logInfo("Switching to Frame by frame WebElement.");
        return targetLocator.frame(frameElement);
    }

    public WebDriver parentFrame() {
        loggerList.logInfo("Switching to parent frame.");
        return targetLocator.parentFrame();
    }

    public WebDriver window(String nameOrHandle) {
        loggerList.logInfo("Switching to window by name or handle '" + nameOrHandle + "'.");
        return targetLocator.window(nameOrHandle);
    }

    public WebDriver newWindow(WindowType typeHint) {
        loggerList.logInfo("Creating new window of type '" + typeHint.getClass().getName() + "'.");
        return targetLocator.newWindow(typeHint);
    }

    public WebDriver defaultContent() {
        loggerList.logInfo("Switching to default content.");
        return targetLocator.defaultContent();
    }

    public WebElement activeElement() {
        loggerList.logInfo("Switching to active element.");
        return new LoggingWebElement(targetLocator.activeElement(), loggerList);
    }

    public Alert alert() {
        loggerList.logInfo("Switching to alert.");
        return targetLocator.alert();
    }
}
