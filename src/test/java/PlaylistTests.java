import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaylistTests extends BaseTest{
    public String playlistName = "12345";
    public boolean alreadyLoggedIn = false; // user not logged in
    // if createPlaylist was called from addSong, needs to rerun addSong
    public boolean addSong = false;

    @Test
    public void deletePlaylist() throws InterruptedException {
        String expectedAlert = "Deleted playlist \"" + playlistName + ".\"";
        //Steps
        //navigateToPage();   moved to Base class
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();

        boolean exists = selectPlaylist(playlistName);
        if (!exists){
            alreadyLoggedIn = true;
            createPlaylist();
        }
        else {
            boolean empty = checkIfPlaylistEmpty();
            removePlaylist();
            if(!empty) {
                confirmDelete();    //SOMETIMES NEEDS TO BE DISABLED WHEN Confirmation Box does not appear
            }
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='alertify-logs top right']")));
            Assert.assertEquals(alert.getText(),expectedAlert);
        }

    }

    @Test
    public void createPlaylist() throws InterruptedException {
        String expectedAlert = "Created playlist \"" + playlistName + ".\"";
        //Steps
        if(!alreadyLoggedIn){
            //navigateToPage();   moved to Base class
            provideEmail("oksana.chaklosh@testpro.io");
            providePassword("8qUBYosp");
            clickSubmit();
        }

        boolean exists = selectPlaylist(playlistName);
        if (!exists){
            clickAddPlaylistButton();
            clickNewPlaylist();
            inputPlaylistName(playlistName);

            WebElement alert = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='alertify-logs top right']")));
            Assert.assertEquals(alert.getText(),expectedAlert);
            
        }
    }

    @Test
    public void addSongToPlaylist() throws InterruptedException {
        String expectedAlert = "Added 1 song into \"" + playlistName + ".\"";
        //Steps
        //navigateToPage();   moved to Base class
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();
        
        searchSong("Dee");
        viewSearchResults();
        chooseFirstSong();
        clickAddToButton();
        boolean exists = choosePlaylistToAddSongTo(playlistName);
        if (exists){
            WebElement alert = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='alertify-logs top right']")));
            Assert.assertEquals(alert.getText(),expectedAlert);
        }
          // option to choose new playlist instead of existing one
        else {
            addNewPlaylist(playlistName);
        }

    }
}
