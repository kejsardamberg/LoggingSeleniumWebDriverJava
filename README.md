# LoggingSeleniumWebDriver - Java version
Simple logging framework to wrap a Java Selenium WebDriver instance to make it output customizable and extendible logs.
This library by default logs event by the WebDriver instance in a natural flow. 
You may also log your own messages to the log, for example for verification results. 

## What it is
Selenium WebDriver is a useful tool to drive web browser activities for test purposes. 
However, Selenium WebDriver doesn't have any means of telling what it has done for logging purposes.

For example; the test:
```java
        By searchField = By.tagName("textarea");
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys("Logging");
        driver.findElement(searchField).submit();
        driver.quit();
```
Would only output some text about starting a ChromeDriver instance.

If we would wrap this with LogginSeleniumWebDriver it produces the followin output:
```console
2023-05-11 16:30:06 INFO:           Starting driver of type org.openqa.selenium.chrome.ChromeDriver.
2023-05-11 16:30:06 EXECUTION_STEP: Navigating to 'https://google.com'.
2023-05-11 16:30:06 EXECUTION_STEP: Clicking textarea element Sök.
2023-05-11 16:30:07 EXECUTION_STEP: Sending keys 'Logging' to textarea element Sök.
2023-05-11 16:30:07 EXECUTION_STEP: Submitting
2023-05-11 16:30:07 INFO:           Quitting web driver instance.
```
Depending on the log level this could get even more detailed (as can be seen below).

```console
2023-05-11 16:38:19 INFO:           Starting driver of type org.openqa.selenium.chrome.ChromeDriver.
2023-05-11 16:38:19 EXECUTION_STEP: Navigating to 'https://google.com'.
2023-05-11 16:38:20 DEBUG:          Identified element for By statement 'By.tagName: textarea'.
2023-05-11 16:38:20 EXECUTION_STEP: Clicking textarea element Sök.
2023-05-11 16:38:20 DEBUG:          Identified element for By statement 'By.tagName: textarea'.
2023-05-11 16:38:20 EXECUTION_STEP: Sending keys 'Logging' to textarea element Sök.
2023-05-11 16:38:20 DEBUG:          Identified element for By statement 'By.tagName: textarea'.
2023-05-11 16:38:20 EXECUTION_STEP: Submitting
2023-05-11 16:38:20 INFO:           Quitting web driver instance.
```

## Getting started
### Including dependency
Get it from maven central by including this to your dependencies section of your pom.xml file:
```xml
<dependency>
    <groupId>com.github.claremontqualitymanagement</groupId>
    <artifactId>LoggingSeleniumWebDriver</artifactId>
    <version>1.1.2</version>
</dependency>
```
or by downloading release artifacts from this repository, or clone the code and compile it yourself.

### Regular instantiation
```java
    WebDriver driver = new LoggingSeleniumWebDriver(new ChromeDriver()); //Enables console logger by default
```
### Builder pattern constructor
```java
        WebDriver driver = new LoggingSeleniumWebDriver.Builder()
                .attachWebDriverInstance(new ChromeDriver())
                .addLogger(new ConsoleLogger())
                .setMinimumLogLevel(LogLevel.DEBUG)
                .build();
        driver.get("https://mysaite.com");
```

## Other features
Using this library is meant to be pretty straight forward. However, since there are a few features these could also be configured.

### Set log level
There are several log levels, DEBUG, INFO, EXECUTION_STEP, and EXCEPTION (in this order). 
To set the minimum log level, use the setMinimumLogLevel() method.
```java
        LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(new FirefoxDriver());
        driver.setMinimumLogLevel(LogLevel.INFO);
```    
Using the builder pattern you may also set the logging level at instansiation.

### Custom log messages
Sometimes you want to write out your own messages to the log. 
This could be done easily by:
```java
    driver.log("You are now in the code line that produces my own log message."); //Default log level is INFO
    driver.logDebug("This is a detailed debug information message.");
    driver.logInfo("This is important information regarding execution.");
    driver.logExecutionStep("This is information about a performed execution step.");
    driver.logException(new LoggingSeleniumWebDriverException("Oups!!!"));
```


### Adding your own Loggers
If not used as a WebDriver, but kept as a LoggingSeleniumWebDriver you may add other loggers and custom loggers:
```java
    LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(new ChromeDriver());
    driver.addLogger(new HtmlLogger());
```

### Pausing and resuming logging
Logging may be paused or resumed anywhere by
```java
    LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(new ChromeDriver());
    driver.get("https://mypage.com");
    driver.findElement(userNameField).sendKeys("SuperAdmin");
    driver.pauseLogging();
    driver.findElement(passwordField).sendKeys("TopSecret!");
    driver.resumeLogging();
    driver.findElement(loginButton).click();
```

## Advanced usage

### Original WebDriver instance
The original and non-logging WebDriver instance could be accessed and used at any time by using:
```java
        driver.originalWebDriver.get("https://newsite.com");
```

### Extending with your own Logger classes
THe Logger is an interface. Feel free to implement your own classes based on this interface. 

### Chance WebDriver instance at runtime
```java
        LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(new FirefoxDriver());
        // Do stuff with this Firefox browser instance
        driver.detachWebDriverInstance();
        driver.attachWebDriverInstance(new ChromeDriver());
        //Continue exeuction in Chrome
```

### Extend with custom log levels
Any new log level should implement the LogLevel interface. You might need to override existing log levels for log level filtering log level to work as expected. 
