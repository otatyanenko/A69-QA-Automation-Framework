import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailValidPassword() {
        //Steps
        //navigateToPage();    moved to Base class
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();

        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
        //Expected Result
        Assert.assertTrue(avatarIcon.isDisplayed());

    }

    @Test (dataProvider = "IncorrectLoginData", dataProviderClass = BaseTest.class)
    public void loginInvalidEmailValidPassword(String email, String password) {

        //Steps
        //navigateToPage();    moved to Base class
        provideEmail(email);
        providePassword(password);
        clickSubmit();

        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), url);

    }

    @Test
    public void loginValidEmailEmptyPassword() {

        //Steps
        //navigateToPage();  moved to Base class
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("");
        clickSubmit();

        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), url);

    }
}
