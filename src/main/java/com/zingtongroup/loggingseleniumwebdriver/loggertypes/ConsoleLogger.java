package com.zingtongroup.loggingseleniumwebdriver.loggertypes;

import com.zingtongroup.loggingseleniumwebdriver.logging.LogPost;
import com.zingtongroup.loggingseleniumwebdriver.logging.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleLogger implements Logger {
    SimpleDateFormat sdf;

    public ConsoleLogger() {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void addLogPost(LogPost logPost) {
        System.out.println(sdf.format(logPost.date) + " " + logPost.logLevel.toString() + ": " + logPost.logMessage);
    }

    public void addLogPost(String logMessage) {
        System.out.println(sdf.format(new Date()) + "   " + logMessage);
    }
}
