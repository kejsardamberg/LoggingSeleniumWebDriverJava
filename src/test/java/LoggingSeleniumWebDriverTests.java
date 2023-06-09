import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.LoggingSeleniumWebDriver;
import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.loggertypes.ConsoleLogger;
import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.logging.LoggingSeleniumWebDriverException;
import com.zingtongroup.seleniumextensions.loggingseleniumwebdriver.logging.TestFlowLogLevel;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testhelpers.TestWebDriver;

public class LoggingSeleniumWebDriverTests {

    @Test
    public void instanceCreation(){
        LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(new TestWebDriver());
        driver.quit();
    }

    @Test
    public void multipleLoggersOfSameType(){
        LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(new TestWebDriver());
        driver.addLogger(new ConsoleLogger());
        driver.get("https://newsite.com");
        driver.quit();
    }

    @Test
    public void builderPatternTest(){
        WebDriver driver = new LoggingSeleniumWebDriver.Builder()
                .attachWebDriverInstance(new TestWebDriver())
                .addLogger(new ConsoleLogger())
                .setMinimumLogLevel(TestFlowLogLevel.DEBUG)
                .build();
        By searchField = By.tagName("textarea");
        driver.get("https://google.com");
        driver.findElement(By.id("W0wltc")).click();
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys("Logging");
        driver.findElement(searchField).submit();
        driver.quit();
    }

    @Test
    public void setLogLevelTest() throws LoggingSeleniumWebDriverException {
        WebDriver driver1 = new TestWebDriver();
        LoggingSeleniumWebDriver driver = new LoggingSeleniumWebDriver(driver1);
        driver.detachWebDriverInstance();
        driver1.quit();

        WebDriver driver2 = new TestWebDriver();
        driver.attachWebDriverInstance(driver2);
        driver.setMinimumLogLevel(TestFlowLogLevel.INFO);
        driver.log("Message");
        driver.originalWebDriver.get("https://zingtongroup.com");
        driver.logDebug("This is a detailed debug information message.");
        driver.logInfo("This is important information regarding execution.");
        driver.logExecutionStep("This is information about a performed execution step.");
        //driver.logException(new LoggingSeleniumWebDriverException("Oups!!!"));
        driver.quit();
    }
}
