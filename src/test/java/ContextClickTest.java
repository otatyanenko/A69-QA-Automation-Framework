import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LeftNavPanelPage;
import pages.LoginPage;
import pages.SongsPage;

public class ContextClickTest extends BaseTest{

    @Test
    public void playSong(){
        LoginPage loginPage = new LoginPage(getDriver());
        LeftNavPanelPage leftNavPanelPage = new LeftNavPanelPage(getDriver());
        SongsPage songsPage = new SongsPage(getDriver());
        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

        leftNavPanelPage.chooseAllSongsList();
        songsPage.contextClickFirstSong();
        songsPage.choosePlayOption();
        Assert.assertTrue(songsPage.isSongPlaying());
    }

@Test
    public void clickHomePageLinksTests(){
    LoginPage loginPage = new LoginPage(getDriver());
    LeftNavPanelPage leftNavPanelPage = new LeftNavPanelPage(getDriver());

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
