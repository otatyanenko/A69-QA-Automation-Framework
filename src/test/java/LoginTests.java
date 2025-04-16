import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailValidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

        //Expected Result
        Assert.assertTrue(homePage.isAvatarIconDisplayed());

    }

    @Test (dataProvider = "IncorrectLoginData", dataProviderClass = BaseTest.class)
    public void loginInvalidEmailValidPassword(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        //Steps
        loginPage.login(email, password );

        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), url);

    }

    @Test
    public void loginValidEmailEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "" );

        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), url);

    }
}
