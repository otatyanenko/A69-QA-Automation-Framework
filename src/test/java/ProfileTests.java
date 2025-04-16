import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserProfilePage;

public class ProfileTests extends BaseTest{
    @Test
    public void changeProfileName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        HomePage homePage = new HomePage(driver);

        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

        userProfilePage.clickAvatar();
        userProfilePage.provideCurrentPassword("8qUBYosp");
        String randomName = generateRandomName();
        userProfilePage.provideName(randomName);
        loginPage.provideEmail("oksana.chaklosh@testpro.io");
        userProfilePage.provideNewPassword("A1234512345");
        userProfilePage.clickSave();

        //Expected Result
        // This Assert is to check if there alert is visible after Save button is clicked
        Assert.assertTrue(homePage.isAlertDisplayed());

        /*
        // Here we are checking if the randomName equals to the actual name of the profile now
        WebElement actualProfileName = driver.findElement(By.cssSelector("a.view-profile>span"));
        Assert.assertEquals(profilePage.actualProfileName(), randomName);
        Thread.sleep(2000);
        */


    }
}
