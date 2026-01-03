package tests;

import assertions.AssertActions;
import assertions.VerifyActions;
import config.ConfigurationManager;
import core.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    @BeforeMethod
    void setUp() {
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigurationManager.getBaseUrl());
    }

    @AfterMethod
    void tearDown() {
        VerifyActions.assertAll();
        DriverManager.quitDriver();
    }
}
