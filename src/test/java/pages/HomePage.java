package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class HomePage extends BasePage{
//Constructor
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
//Locators
    By avatarIcon = By.cssSelector("img[class='avatar']");  // profile avatar
    By alert = By.cssSelector("[class='alertify-logs top right']"); // alert message box top right
    By playBtn = By.xpath("//span[@data-testid='play-btn']");
    By nextSongButton = By.xpath("//i[@data-testid='play-next-btn']");
    By playButton = By.xpath("//span[@data-testid='play-btn']");
    By pauseButton = By.xpath("//span[@class='pause']");

//Methods
    public boolean isAvatarIconDisplayed(){
        return findElement(avatarIcon).isDisplayed();
    }

    public boolean isAlertDisplayed(){
        return findElement(alert).isDisplayed();
    }

    public String getTextFromAlert (){
        return findElement(alert).getText();
    }

    public WebElement hoverPlay() {
        WebElement play = wait.until(ExpectedConditions.elementToBeClickable(playBtn));

        actions.moveToElement(play).perform();
        return wait.until(ExpectedConditions.visibilityOf(play));
    }

    public boolean isHoverOverPlayBtnDisplayed() {
        WebElement play = driver.findElement(playBtn);
        actions.moveToElement(play).perform();
        return wait.until(ExpectedConditions.visibilityOf(play)).isDisplayed();
    }

    public void playNextSong() {
        wait.until(ExpectedConditions.presenceOfElementLocated(nextSongButton)).click();
    }

    public void clickPlaySong() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(playBtn)).click();
    }

    public boolean isPauseBtnDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pauseButton)).isDisplayed();
    }




}
