import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SongsPage;

public class SongTests extends BaseTest{

    @Test
    public void playSong() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

        homePage.playNextSong();
        homePage.clickPlaySong();

        //Expected results
        //The pause button is displayed to confirm that the song is playing

        Assert.assertTrue(homePage.isPauseBtnDisplayed());

    }

}
