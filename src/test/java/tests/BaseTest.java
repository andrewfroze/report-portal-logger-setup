package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;



public class BaseTest {
    protected static final Logger logger = LogManager.getRootLogger();

    @BeforeSuite
    public void suiteInit() {
        logger.info("suite init");
    }

    @BeforeClass
    public void classInit() {
        logger.info("class init");
    }

    @AfterClass
    public void classClose() {
        logger.info("class close");
    }

    @AfterSuite
    public void suiteShutDown() {
        logger.info("suite shutdown");
    }
}
