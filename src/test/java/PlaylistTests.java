import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaylistTests extends BaseTest{
    public String playlistName = "1234";
    public boolean alreadyLoggedIn = false;

    @Test
    public void deletePlaylist() throws InterruptedException {
        String expectedAlert = "Deleted playlist \"" + playlistName + ".\"";
        //Steps
        navigateToPage();
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();
        Thread.sleep(1000);

        boolean exists = selectPlaylist(playlistName);
        if (!exists){
            alreadyLoggedIn = true;
            createPlaylist();
        }
        else {
            removePlaylist();
            confirmDelete();
            Thread.sleep(1000);
            WebElement alert = driver.findElement(By.cssSelector("[class='alertify-logs top right']"));
            Assert.assertEquals(alert.getText(),expectedAlert);
        }

    }

    @Test
    public void createPlaylist() throws InterruptedException {
        String expectedAlert = "Created playlist \"" + playlistName + ".\"";
        //Steps
        if(!alreadyLoggedIn){
            navigateToPage();
            provideEmail("oksana.chaklosh@testpro.io");
            providePassword("8qUBYosp");
            clickSubmit();
            Thread.sleep(1000);
        }

        boolean exists = selectPlaylist(playlistName);
        if (!exists){
            clickAddPlaylistButton();
            clickNewPlaylist();
            inputPlaylistName(playlistName);
            Thread.sleep(1000);
            WebElement alert = driver.findElement(By.cssSelector("[class='alertify-logs top right']"));
            Assert.assertEquals(alert.getText(),expectedAlert);
        }

    }

    @Test
    public void addSongToPlaylist() throws InterruptedException {
        String expectedAlert = "Added 1 song into \"" + playlistName + ".\"";
        //Steps
        navigateToPage();
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();
        Thread.sleep(2000);

        searchSong("Dee");
        viewSearchResults();
        chooseFirstSong();
        clickAddToButton();
        Thread.sleep(2000);
        choosePlaylistToAddSongTo(playlistName);
        Thread.sleep(1000);
        WebElement alert = driver.findElement(By.cssSelector("[class='alertify-logs top right']"));
        Assert.assertEquals(alert.getText(),expectedAlert);
    }



}
