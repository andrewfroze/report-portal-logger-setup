package assertion;

import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.service.ReportPortal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.DataProviderInvocationException;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;
import utils.screenshotmaker.ScreenshotMaker;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class CustomSoftAssert extends Assertion {
    private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
    private static final String DEFAULT_SOFT_ASSERT_MESSAGE = "The following asserts failed:";
    private boolean supportsScreenshots = true;
    private static final Logger logger = LogManager.getRootLogger();

    public CustomSoftAssert() {
        super();
    }

    public CustomSoftAssert(boolean supportsScreenshots) {
        this.supportsScreenshots = supportsScreenshots;
    }

    public void assertAll() {
        assertAll(null);
    }

    public void assertAll(String message) {
        if (!m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder(null == message ? DEFAULT_SOFT_ASSERT_MESSAGE : message);
            boolean first = true;
            for (AssertionError error : m_errors.keySet()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n\t");
                sb.append(getErrorDetails(error));
            }
            throw new AssertionError(sb.toString());
        }
    }

    @Override
    @Step("Check result")
    protected void doAssert(IAssert<?> a) {
        onBeforeAssert(a);
        try {
            doAssertion(a);
            onAssertSuccess(a);
            logger.info("Successful assertion");
        } catch (AssertionError ex) {
            onAssertFailure(a, ex);
            ReportPortal.emitLog(ex.getMessage(), "error", new Date(), ScreenshotMaker.takeScreenshot("target/screenshots/failure"));
            m_errors.put(ex, a);
        } finally {
            onAfterAssert(a);
        }
    }

    @Step("Do assert")
    protected void doAssertion(IAssert<?> a) {
        a.doAssert();
    }

//    public void assertTrue(boolean condition, String message) {
//        try {
//            Assert.assertTrue(condition);
//        } catch (AssertionError e) {
//            e.printStackTrace();
//        }
//        softAssert.assertTrue(condition, message);
//    }

//    public void assertTrue(boolean condition) {
//        this.assertTrue(condition, "");
//    }
}
