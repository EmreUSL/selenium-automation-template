package tests;

import config.ConfigurationManager;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.UIActions;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest()
    {
        UIActions.type(LoginPage.userName, ConfigurationManager.getUserName());
        UIActions.type(LoginPage.password, ConfigurationManager.getPassword());
        UIActions.click(LoginPage.loginButton);
    }
}
