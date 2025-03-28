import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaylistTests extends BaseTest{
    public String playlistName = "1234";

    @Test
    public void addPlaylist() throws InterruptedException {
        String expectedAlert = "Created playlist \"" + playlistName + ".\"";
        //Steps
        navigateToPage();
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();
        Thread.sleep(2000);

        clickAddPlaylistButton();
        clickNewPlaylist();
        inputPlaylistName(playlistName);
        Thread.sleep(2000);
        WebElement alert = driver.findElement(By.cssSelector("[class='alertify-logs top right']"));
        Assert.assertEquals(alert.getText(),expectedAlert);
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
    @Test
    public void deletePlaylist() throws InterruptedException {
        String expectedAlert = "Deleted playlist \"" + playlistName + ".\"";
        //Steps
        navigateToPage();
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();
        Thread.sleep(2000);

        selectPlaylist();
        removePlaylist();
        confirmDelete();
        Thread.sleep(1000);
        WebElement alert = driver.findElement(By.cssSelector("[class='alertify-logs top right']"));
        Assert.assertEquals(alert.getText(),expectedAlert);
    }


}
