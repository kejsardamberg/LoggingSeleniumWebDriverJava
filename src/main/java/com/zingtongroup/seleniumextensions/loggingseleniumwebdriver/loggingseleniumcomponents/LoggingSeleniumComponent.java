package com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.loggingseleniumcomponents;

public interface LoggingSeleniumComponent {

    void pauseLogging();

    void resumeLogging();

    void log(String logMessage);

}
