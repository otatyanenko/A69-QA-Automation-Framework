import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class PlaylistTests extends BaseTest{
    public String playlistName = "ty";
    public boolean alreadyLoggedIn = false; // user not logged in
    // if createPlaylist was called from addSong, needs to rerun addSong
    public boolean addSong = false;

    @Test (priority = 3)
    public void deletePlaylist() throws InterruptedException {
        String expectedAlert = "Deleted playlist \"" + playlistName + ".\"";

        LoginPage loginPage = new LoginPage(driver);
        SongsPage songsPage = new SongsPage(driver);
        HomePage homePage = new HomePage(driver);
        LeftNavPanelPage leftNavPanelPage = new LeftNavPanelPage(driver);

        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

        boolean exists = leftNavPanelPage.selectPlaylistSideMenu(playlistName);
        if (!exists){
            alreadyLoggedIn = true;
            createPlaylist();
        }
        else {
            boolean empty = songsPage.checkIfPlaylistEmpty();
            songsPage.removePlaylist();
            if(!empty) {
                songsPage.confirmDelete();    //SOMETIMES NEEDS TO BE DISABLED WHEN Confirmation Box does not appear
            }
            //WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='alertify-logs top right']")));
            Assert.assertEquals(homePage.getTextFromAlert(),expectedAlert);
        }

    }

    //Creating a playlist using plus sign on left panel
    @Test (priority = 1)
    public void createPlaylist() throws InterruptedException {
        String expectedAlert = "Created playlist \"" + playlistName + ".\"";

        LeftNavPanelPage leftNavPanelPage = new LeftNavPanelPage(driver);
        HomePage homePage = new HomePage(driver);
        //Steps
        if(!alreadyLoggedIn){
            LoginPage loginPage = new LoginPage(driver);
            //Steps
            loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );
        }

        boolean exists = leftNavPanelPage.selectPlaylistSideMenu(playlistName);
        if (!exists){
            leftNavPanelPage.clickAddPlaylistButtonPlusSign();
            leftNavPanelPage.clickNewPlaylist();
            leftNavPanelPage.inputPlaylistName(playlistName);

            //WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='alertify-logs top right']")));
            Assert.assertEquals(homePage.getTextFromAlert(),expectedAlert);
            
        }
    }

    //Searching for song through search on left panel; then choosing the first song from the list to add
    //either to existing playlist or new playlist
    @Test (priority = 2)
    public void addSongToPlaylist() throws InterruptedException {
        String expectedAlert = "Added 1 song into \"" + playlistName + ".\"";

        LoginPage loginPage = new LoginPage(driver);
        LeftNavPanelPage leftNavPanelPage = new LeftNavPanelPage(driver);
        SongsPage songsPage = new SongsPage(driver);
        HomePage homePage = new HomePage(driver);

        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );
        
        leftNavPanelPage.searchSong("Dee");
        songsPage.viewSearchResults();
        songsPage.clickFirstSong();
        songsPage.clickAddToButton();
        boolean exists = songsPage.clickExistingPlaylistToAddSongTo(playlistName);
        if (exists){
            Assert.assertEquals(homePage.getTextFromAlert(),expectedAlert);
        }
          // option to choose new playlist instead of existing one
        else {
            songsPage.addNewPlaylist(playlistName);
            expectedAlert = "Created playlist \"" + playlistName + ".\"";
            Assert.assertEquals(homePage.getTextFromAlert(),expectedAlert);
        }

    }

    @Test
    public void countSongsInPlaylist() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        LeftNavPanelPage leftNavPanelPage = new LeftNavPanelPage(driver);
        SongsPage songsPage = new SongsPage(driver);

        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

        boolean exists = leftNavPanelPage.selectPlaylistSideMenu("dfg");
        if (exists){
            songsPage.displayAllSongsToConsole();
            Assert.assertTrue(songsPage.getPlaylistDetails().contains(String.valueOf(songsPage.countSongs())));
        }
        else {
            alreadyLoggedIn = true;
            createPlaylist();
        }
    }

    public String newPlaylistName = "Test Edited Playlist3";

    @Test
    public void renamePlaylist()  {
        String updatedPlaylistMsg = "Updated playlist \"Test Edited Playlist3.\"";

        LoginPage loginPage = new LoginPage(driver);
        LeftNavPanelPage leftNavPanelPage = new LeftNavPanelPage(driver);
        HomePage homePage = new HomePage(driver);

        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

        leftNavPanelPage.doubleClickPlaylist();
        leftNavPanelPage.enterNewPlaylistName(newPlaylistName);
        Assert.assertEquals(homePage.getTextFromAlert(), updatedPlaylistMsg);
    }

}





