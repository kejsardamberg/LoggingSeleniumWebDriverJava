package com.zingtongroup.loggingseleniumwebdriver.loggertypes;

import com.zingtongroup.loggingseleniumwebdriver.logging.LogPost;
import com.zingtongroup.loggingseleniumwebdriver.logging.Logger;
import com.zingtongroup.loggingseleniumwebdriver.logging.TestFlowLogLevel;

import java.text.SimpleDateFormat;

public class ConsoleLogger implements Logger {
    private final SimpleDateFormat sdf;
    private int longestLogLevelNameLength;

    public ConsoleLogger() {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        longestLogLevelNameLength = 0;
        for (TestFlowLogLevel level : TestFlowLogLevel.values()) {
            if (level.toString().length() > longestLogLevelNameLength)
                longestLogLevelNameLength = level.toString().length();
        }
    }

    public void addLogPost(LogPost logPost) {
        String evenLengthLogLevel = logPost.logLevel.toString() + " ".repeat(Math.max(0, longestLogLevelNameLength - logPost.logLevel.toString().length()));
        System.out.println(sdf.format(logPost.date) + " " + evenLengthLogLevel + ": " + logPost.logMessage);
    }


}
