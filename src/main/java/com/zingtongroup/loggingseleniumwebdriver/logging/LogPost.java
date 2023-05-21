package com.zingtongroup.loggingseleniumwebdriver.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogPost {
    public LogLevel logLevel;
    public String logMessage;
    public Date date;

    public LogPost(LogLevel logLevel, String logMessage) {
        date = new Date();
        this.logLevel = logLevel;
        this.logMessage = logMessage;
    }

    @Override
    public String toString(){
        return String.valueOf(logLevel.getValue()) + "-" + logLevel.toString() + " " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date) + " " + logMessage;
    }
}
