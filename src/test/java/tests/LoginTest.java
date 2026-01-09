package tests;

import assertions.AssertActions;
import config.ConfigurationManager;
import core.listener.AllureListener;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.AllureStepUtil;
import utils.UIActions;

@Listeners(AllureListener.class)
public class LoginTest extends BaseTest {

    @Description("Verify to login to application")
    @Epic("EP001")
    @Feature("Feature1: Login")
    @Story("Story : Login ")
    @Step("Verify Login Application")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 1, description = "Verify to login to application")
    public void loginTest()
    {
        UIActions.type(LoginPage.userName, ConfigurationManager.getUserName());
        UIActions.type(LoginPage.password, ConfigurationManager.getPassword());
        UIActions.click(LoginPage.loginButton);

        AssertActions.assertTrue(UIActions.isDisplayed(LoginPage.homePage), "Home page is not displayed");
    }
}
