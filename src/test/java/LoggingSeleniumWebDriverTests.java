import TestHelpers.TestLogger;
import TestHelpers.TestWebDriver;
import loggertypes.ConsoleLogger;
import logging.LogLevel;
import logging.LoggingSeleniumWebDriverException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoggingSeleniumWebDriverTests {

    @Test
    public void instanceCreation(){
        LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(new TestWebDriver());
    }

    @Test
    public void multipleLoggersOfSameType(){
        LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(new TestWebDriver());
        driver.addLogger(new ConsoleLogger());
        driver.get("https://newsite.com");
    }

    @Test
    public void builderPatternTest(){
        WebDriver driver = new LoggingSeleniumWebDriver.Builder()
                .attachWebDriverInstance(new TestWebDriver())
                .addLogger(new ConsoleLogger())
                .setMinimumLogLevel(LogLevel.DEBUG)
                .build();
        driver.get("https://mysaite.com");
        driver.quit();
    }

    @Test
    public void setLogLevelTest() throws LoggingSeleniumWebDriverException {
        LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(new FirefoxDriver());
        driver.detachWebDriverInstance();
        driver.attachWebDriverInstance(new ChromeDriver());
        driver.setMinimumLogLevel(LogLevel.INFO);
        driver.log("Message");
        driver.originalWebDriver.get("https://newsite.com");
        driver.logDebug("This is a detailed debug information message.");
        driver.logInfo("This is important information regarding execution.");
        driver.logExecutionStep("This is information about a performed execution step.");
        driver.logException(new LoggingSeleniumWebDriverException("Oups!!!"));
    }
}
