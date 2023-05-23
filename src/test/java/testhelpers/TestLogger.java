package testhelpers;

import com.zingtongroup.loggingseleniumwebdriver.logging.LogPost;
import com.zingtongroup.loggingseleniumwebdriver.logging.Logger;
import org.junit.Assert;

public class TestLogger implements Logger {
    StringBuilder sb;

    public TestLogger() {
        sb = new StringBuilder();
    }

    @Override
    public void addLogPost(LogPost logPost) {
        sb.append(logPost.toString()).append(System.lineSeparator());
    }


    public void verifyContains(String str) {
        Assert.assertTrue(sb.toString().contains(str));
    }
}
