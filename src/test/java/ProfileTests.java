import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest{
    @Test
    public void changeProfileName() throws InterruptedException {
        //Steps
        navigateToPage();
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();
        Thread.sleep(2000);
        clickAvatar();
        provideCurrentPassword("8qUBYosp");
        String randomName = generateRandomName();
        provideName(randomName);
        provideEmail("oksana.chaklosh@testpro.io");
        provideNewPassword("A1234512345");
        clickSave();
        Thread.sleep(2000);
        /*  // This Assert is to check if there alert is visible after Save button is clicked
        WebElement alert = driver.findElement(By.cssSelector("[class='alertify-logs top right']"));
        Assert.assertTrue(alert.isDisplayed());
        */
        // Here we are checking if the randomName equals to the actual name of the profile now
        WebElement actualProfileName = driver.findElement(By.cssSelector("a.view-profile>span"));
        Assert.assertEquals(actualProfileName.getText(), randomName);
        Thread.sleep(2000);

        //Expected Result

    }
}
