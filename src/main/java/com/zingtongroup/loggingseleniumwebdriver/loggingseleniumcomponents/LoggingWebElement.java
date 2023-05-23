package com.zingtongroup.loggingseleniumwebdriver.loggingseleniumcomponents;

import com.zingtongroup.loggingseleniumwebdriver.logging.LoggerList;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class LoggingWebElement implements LoggingSeleniumComponent, WebElement {

    public final WebElement webElement;
    public final LoggerList loggerList;
    public final String elementString;

    public LoggingWebElement(WebElement webElement, LoggerList loggerList) {
        this.webElement = webElement;
        this.loggerList = loggerList;
        this.elementString = webElement.getTagName() + " element " + webElement.getAccessibleName();
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

    public void click() {
        loggerList.logExecutionStep("Clicking " + elementString + ".");
        webElement.click();
    }

    public void submit() {
        loggerList.logExecutionStep("Submitting");
        webElement.submit();
    }

    public void sendKeys(CharSequence... keysToSend) {
        if(keysToSend == null){
            loggerList.logInfo("Attempting to send null to " + elementString + " with sendKeys(). Skipping it.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(CharSequence seq : keysToSend){
            sb.append(seq);
        }
        loggerList.logExecutionStep("Sending keys '" + sb.toString() + "' to " + elementString + ".");
        webElement.sendKeys(keysToSend);
    }

    /**
     * Hides sent characters in log by utilizing the *** pattern rather than actual characters.
     *
     * @param keysToSend Characters to send to the element
     */
    public void sendSecretKeys(CharSequence... keysToSend) {
        if(keysToSend == null){
            loggerList.logInfo("Attempting to send null to " + elementString + " with sendSecretKeys(). Skipping it.");
            return;
        }
        loggerList.logExecutionStep("Sending secret keys '" + "*".repeat(keysToSend.length) + "' to " + elementString + ".");
        webElement.sendKeys(keysToSend);
    }

    public void clear() {
        loggerList.logExecutionStep("Clearing element " + elementString + ".");
        webElement.clear();
    }

    public String getTagName() {
        loggerList.logDebug("Getting tag name for element " + elementString + ".");
        return webElement.getTagName();
    }

    public String getAttribute(String name) {
        String attributeValue = webElement.getAttribute(name);
        loggerList.logDebug("Reading attribute value '" + attributeValue + "' for attribute '" + name + "'.");
        return attributeValue;
    }

    public boolean isSelected() {
        boolean selected = webElement.isSelected();
        if (selected) {
            loggerList.logDebug("Checked if element was selected. It was.");
        } else {
            loggerList.logDebug("Checked if element was selected. It was not.");
        }
        return selected;
    }

    public boolean isEnabled() {
        boolean enabled = webElement.isEnabled();
        if (enabled) {
            loggerList.logDebug("Checked if element was enabled. It was.");
        } else {
            loggerList.logDebug("Checked if element was enabled. It was not.");
        }
        return enabled;
    }

    public String getText() {
        String text = webElement.getText();
        loggerList.logInfo("Read text '" + text + "' from element " + elementString + ".");
        return text;
    }

    public List<WebElement> findElements(By by) {
        List<WebElement> elements = webElement.findElements(by);
        log("Identifying " + elements.size() + " elements for By statement '" + by.toString() + "'.");
        List<WebElement> elements2 = new ArrayList<>();
        for (WebElement webElement : elements) {
            elements2.add(new LoggingWebElement(webElement, loggerList));
        }
        return elements2;
    }

    public WebElement findElement(By by) {
        WebElement element = webElement.findElement(by);
        if (element == null) {
            log("Could not identify any element for By statement '" + by.toString() + "'.");
            return null;
        } else {
            log("Identified element for By statement '" + by.toString() + "'.");
        }
        return new LoggingWebElement(element, loggerList);
    }

    public boolean isDisplayed() {
        boolean displayed = webElement.isDisplayed();
        if (displayed) {
            loggerList.logDebug("Checked if element was displayed. It was.");
        } else {
            loggerList.logDebug("Checked if element was displayed. It was not.");
        }
        return displayed;
    }

    public Point getLocation() {
        Point location = webElement.getLocation();
        loggerList.logInfo("Read location (" + location.x + "x" + location.y + ") for element " + elementString + ".");
        return location;
    }

    public Dimension getSize() {
        Dimension size = webElement.getSize();
        loggerList.logInfo("Read size (" + size.width + "x" + size.height + ") for element " + elementString + ".");
        return size;
    }

    public Rectangle getRect() {
        Rectangle rect = webElement.getRect();
        loggerList.logInfo("Read rectangle (" + rect.width + "x" + rect.height + ") for element " + elementString + ".");
        return rect;
    }

    public String getCssValue(String propertyName) {
        String css = webElement.getCssValue(propertyName);
        loggerList.logInfo("Read CSS value for property '" + propertyName + "' from element " + elementString + ". It was '" + css + "'.");
        return css;
    }

    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        loggerList.logInfo("Snapping screenshot of element " + elementString + ".");
        return webElement.getScreenshotAs(target);
    }
}
