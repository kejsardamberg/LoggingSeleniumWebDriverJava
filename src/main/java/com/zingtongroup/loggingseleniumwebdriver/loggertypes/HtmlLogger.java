package com.zingtongroup.loggingseleniumwebdriver.loggertypes;

import com.zingtongroup.loggingseleniumwebdriver.logging.LogPost;
import com.zingtongroup.loggingseleniumwebdriver.logging.Logger;

import java.text.SimpleDateFormat;

public class HtmlLogger implements Logger {
    StringBuilder sb;
    SimpleDateFormat sdf;

    public HtmlLogger() {
        sdf = new SimpleDateFormat("yyMMdd HH:mm:ss");
        sb = new StringBuilder();
        sb.append("<!DOCTYPE html><html><head><title>Log</title><style>.time {color: gray;} .logmessage { font-weight: bold; }</style></head><body><h1>Selenium log</h1>");
    }

    @Override
    public String toString() {
        sb.append("</body></html>");
        return sb.toString();
    }

    public void addLogPost(LogPost logPost) {
        sb.append("<div class='logrow'><span class='time'>")
                .append(sdf.format(logPost.date))
                .append("</span><span class='logmessage'>")
                .append(logPost.logMessage)
                .append("</span></div>");

    }

    public void addLogPost(String logMessage) {
        sb.append("<div class='logrow'><span class='time'>")
                .append(sdf.format("yyyy-DD-mm HH:mm:SS"))
                .append("</span><span class='logmessage'>")
                .append(logMessage)
                .append("</span></div>");
    }
}
