package com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.loggingseleniumcomponents;

import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.logging.LoggerList;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class LoggingNavigation implements WebDriver.Navigation, LoggingSeleniumComponent {

    public final LoggerList loggerList;
    public final WebDriver.Navigation navigation;

    public LoggingNavigation(WebDriver.Navigation navigation, LoggerList loggerList) {
        this.loggerList = loggerList;
        this.navigation = navigation;
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

    public void back() {
        loggerList.logExecutionStep("Navigating back.");
        navigation.back();
    }

    public void forward() {
        loggerList.logExecutionStep("Navigating forward.");
        navigation.forward();
    }

    public void to(String url) {
        loggerList.logExecutionStep("Navigating to '" + url + "'.");
        navigation.to(url);
    }

    public void to(URL url) {
        loggerList.logExecutionStep("Navigating to URL '" + url.toString() + "'.");
        navigation.to(url);
    }

    public void refresh() {
        loggerList.logExecutionStep("Refreshing page.");
        navigation.refresh();
    }
}
