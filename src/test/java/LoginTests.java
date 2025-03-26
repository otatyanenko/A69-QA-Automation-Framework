import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailValidPassword() {
        //Steps
        navigateToPage();
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();

        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
        //Expected Result
        Assert.assertTrue(avatarIcon.isDisplayed());

    }

    @Test
    public void loginInvalidEmailValidPassword() {

        //Steps
        navigateToPage();
        provideEmail("invalid@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();

        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), url);

    }

    @Test
    public void loginValidEmailEmptyPassword() {

        //Steps
        navigateToPage();
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("");
        clickSubmit();

        //Expected Result
        Assert.assertEquals(driver.getCurrentUrl(), url);

    }
}
