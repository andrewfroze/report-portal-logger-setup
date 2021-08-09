package tests;

import assertion.CustomSoftAssert;
import com.epam.reportportal.annotations.Step;
import com.epam.reportportal.annotations.TestCaseId;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestClass extends BaseTest {
    CustomSoftAssert softAssert = new CustomSoftAssert();

    @BeforeMethod
    public void preconditions() {
        logger.info("method preconditions");
    }

    @TestCaseId("ANDRFRZ-1")
    @Test(description = "testing logging for report portal")
    public void testMethod() {
        stepOne();
        stepTwo();
        stepTree();
        check();
        softAssert.assertAll();
    }

    @Step("Step 1")
    public void stepOne() {
        logger.info("test step 1");
        logger.info("test step 1.1");
        logger.info("test step 1.2");
    }

    @Step("Step 2: check 1")
    public void stepTwo() throws AssertionError{
        logger.info("test step 2");
        logger.info("test step 2.1");
        logger.info("test step 2.2");
        softAssert.assertTrue(false, "something wrong");
    }


    @Step("Step 3")
    public void stepTree() {
        logger.info("test step 3");
        logger.info("test step 3.1");
        logger.info("test step 3.2");
        logger.info("test step 3.3");
    }

    @Step("Check final")
    public void check() throws AssertionError{
        softAssert.assertTrue(true);
    }

    @AfterMethod
    public void postConditions() {
        logger.info("method post conditions");
    }
}
