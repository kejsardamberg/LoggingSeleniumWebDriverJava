# LoggingSeleniumWebDriverJava
Simple logging framework to wrap a Java Selenium WebDriver instance to make it output customizable and extendible logs.

Used by:
    WebDriver driver = new LoggingSeleniumWebDriver(new ChromeDriver()); //Enables console logger by default
    
If not used as a WebDriver, but kept as a LoggingSeleniumWebDriver you may add other loggers and custom loggers:
    LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(new ChromeDriver());
    driver.addLogger(new HtmlLogger());
    
Logging may be paused or resumed anywhere by
    driver.pauseLogging();
    driver.get("https://mypage.com");
    driver.resumeLogging();
