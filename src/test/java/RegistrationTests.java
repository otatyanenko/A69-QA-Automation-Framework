import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class RegistrationTests extends BaseTest {
    @Test
    public void registrationNavigation(){
        LoginPage loginPage = new LoginPage(getDriver());
        //Steps
        String url = "https://qa.koel.app/";
        getDriver().get(url);

        loginPage.clickRegistrationLink();

        //Expected result Redirected to Registration Page
        String regUrl = "https://qa.koel.app/registration";
        Assert.assertEquals(getDriver().getCurrentUrl(), regUrl);

    }
}
