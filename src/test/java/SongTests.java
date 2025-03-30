import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SongTests extends BaseTest{

    @Test
    public void playSong() throws InterruptedException {
        //Steps
        navigateToPage();
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();
        Thread.sleep(2000);

        playNextSong();
        clickPlaySong();

        //Expected results
        //The pause button is displayed to confirm that the song is playing
        WebElement pauseButton = driver.findElement(By.xpath("//span[@class='pause']"));
        Assert.assertTrue(pauseButton.isDisplayed());

    }

}
