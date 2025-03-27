import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaylistTests extends BaseTest{
    @Test
    public void addSongToPlaylist() throws InterruptedException {
        String expectedAlert = "Added 1 song into \"1234.\"";
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
        choosePlaylist();
        Thread.sleep(1000);
        WebElement alert = driver.findElement(By.cssSelector("[class='alertify-logs top right']"));
        Assert.assertEquals(alert.getText(),expectedAlert);
    }

}
