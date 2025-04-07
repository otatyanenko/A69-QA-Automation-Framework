import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LeftNavPanelPage;
import pages.LoginPage;
import pages.SongsPage;

public class ContextClickTest extends BaseTest{

    @Test
    public void playSong(){
        LoginPage loginPage = new LoginPage(driver);
        LeftNavPanelPage leftNavPanelPage = new LeftNavPanelPage(driver);
        SongsPage songsPage = new SongsPage(driver);
        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

        leftNavPanelPage.chooseAllSongsList();
        songsPage.contextClickFirstSong();
        songsPage.choosePlayOption();
        Assert.assertTrue(songsPage.isSongPlaying());
    }

@Test
    public void clickHomePageLinksTests(){
    LoginPage loginPage = new LoginPage(driver);
    LeftNavPanelPage leftNavPanelPage = new LeftNavPanelPage(driver);

    loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

    leftNavPanelPage.clickFavoritesLink();
    leftNavPanelPage.clickCurrentQueueLink();
    leftNavPanelPage.clickHomeLink();
    leftNavPanelPage.clickAlbumsLink();
    leftNavPanelPage.chooseAllSongsList();
    leftNavPanelPage.clickArtistsLink();
    leftNavPanelPage.clickRecentlyPlayedLink();

}
}
