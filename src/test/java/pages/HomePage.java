package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class HomePage extends BasePage{
//Constructor
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
//Locators
                    //Old way
    //By avatarIcon = By.cssSelector("img[class='avatar']");  // profile avatar
    private By alert = By.cssSelector("[class='alertify-logs top right']"); // alert message box top right
    private By playBtn = By.xpath("//span[@data-testid='play-btn']");
    private By nextSongButton = By.xpath("//i[@data-testid='play-next-btn']");
    //By pauseButton = By.xpath("//span[@class='pause']");
                    //New way with page factory
    @FindBy (css = "img[class='avatar']") private WebElement avatarIcon;
   // @FindBy (css = "[class='alertify-logs top right']") WebElement alert;
    //@FindBy (xpath = "//span[@data-testid='play-btn']") WebElement playBtn;
    //@FindBy (xpath = "//i[@data-testid='play-next-btn']") WebElement nextSongButton;
    @FindBy (xpath = "//span[@class='pause']") private WebElement pauseButton;
//Methods
    public boolean isAvatarIconDisplayed(){
        return avatarIcon.isDisplayed();
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
        return wait.until(ExpectedConditions.visibilityOf(pauseButton)).isDisplayed();
    }




}
