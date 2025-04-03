import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContextClick extends BaseTest{

    @Test
    public void ContextClick(){
        //Steps
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();

        chooseAllSongsList();
        contextClickFirstSong();
        choosePlayOption();
        Assert.assertTrue(isSongPlaying());
    }

    public boolean isSongPlaying(){
        WebElement soundBarVisualizer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='sound-bar-play']")));
        return soundBarVisualizer.isDisplayed();
    }

    private void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='playback']"))).click();
    }

    private void contextClickFirstSong() {
        WebElement firstSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='items']//tr[1]")));
       // actions.contextClick(firstSongElement).perform();
    }

    private void chooseAllSongsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='songs']"))).click();
    }
}
