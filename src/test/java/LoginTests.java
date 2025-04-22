import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailValidPassword() {
        // we replaced (driver) To (getDriver()) everywhere
        //LoginPage loginPage = new LoginPage(driver);
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

        //Expected Result
        Assert.assertTrue(homePage.isAvatarIconDisplayed());

    }

    @Test (dataProvider = "IncorrectLoginData", dataProviderClass = BaseTest.class)
    public void loginInvalidEmailValidPassword(String email, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        //Steps
        loginPage.login(email, password );

        //Expected Result
        Assert.assertEquals(getDriver().getCurrentUrl(), url);

    }

    @Test
    public void loginValidEmailEmptyPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "" );

        //Expected Result
        Assert.assertEquals(getDriver().getCurrentUrl(), url);

    }
}
