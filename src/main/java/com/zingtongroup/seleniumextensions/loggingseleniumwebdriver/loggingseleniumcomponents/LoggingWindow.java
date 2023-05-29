package com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.loggingseleniumcomponents;

import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.logging.LoggerList;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class LoggingWindow implements WebDriver.Window, LoggingSeleniumComponent {
    public final LoggerList loggerList;
    public final WebDriver.Window window;

    public LoggingWindow(WebDriver.Window window, LoggerList loggerList) {
        this.loggerList = loggerList;
        this.window = window;
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

    public Dimension getSize() {
        Dimension size = window.getSize();
        loggerList.logDebug("Retrieving current window size (=" + size.width + "x" + size.height + ").");
        return size;
    }

    public void setSize(Dimension targetSize) {
        loggerList.logInfo("Setting window dimension to " + targetSize.width + "x" + targetSize.height + " pixels.");
        window.setSize(targetSize);
    }

    public Point getPosition() {
        Point position = window.getPosition();
        loggerList.logDebug("Retrieving current window position (=" + position.x + "," + position.y + ").");
        return position;
    }

    public void setPosition(Point targetPosition) {
        loggerList.logInfo("Setting window position to " + targetPosition.x + ", " + targetPosition.y + " pixels.");
        window.setPosition(targetPosition);
    }

    public void maximize() {
        loggerList.logInfo("Maximizing window.");
        window.maximize();
    }

    public void minimize() {
        loggerList.logInfo("Minimizing window.");
        window.minimize();
    }

    public void fullscreen() {
        loggerList.logInfo("Switching to browser full screen mode.");
        window.fullscreen();
    }
}
