import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaylistTests extends BaseTest{
    public String playlistName = "1234";
    public boolean alreadyLoggedIn = false; // user not logged in
    // if createPlaylist was called from addSong, needs to rerun addSong
    public boolean addSong = false;

    @Test
    public void deletePlaylist() throws InterruptedException {
        String expectedAlert = "Deleted playlist \"" + playlistName + ".\"";
        //Steps
        navigateToPage();
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();

        boolean exists = selectPlaylist(playlistName);
        if (!exists){
            alreadyLoggedIn = true;
            createPlaylist();
        }
        else {
            removePlaylist();
            confirmDelete();

            WebElement alert = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='alertify-logs top right']")));
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
        navigateToPage();
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


    private void addNewPlaylist(String playlistName) {
        WebElement playlistNameField;
        playlistNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@id='songsWrapper']//input")));
        playlistNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='songsWrapper']//input")));
        playlistNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songsWrapper']//input")));
      //playlistNameField = wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='songsWrapper']//input")), ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songsWrapper']//input"))));
        //WebElement playlistNameField = driver.findElement(By.xpath("//section[@id='songsWrapper']//input"));
      playlistNameField.clear();
      playlistNameField.sendKeys(playlistName);
      WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songsWrapper']//button[@type='submit']")));
      submitButton.click();
    }


}
