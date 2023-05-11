import TestHelpers.TestLogger;
import TestHelpers.TestWebDriver;
import loggertypes.ConsoleLogger;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

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
                .build();
        driver.get("https://mysaite.com");
        driver.quit();
    }
}
