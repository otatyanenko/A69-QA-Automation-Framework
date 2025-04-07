package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserProfilePage extends BasePage{
//Constructor
    public UserProfilePage(WebDriver givenDriver) {
        super(givenDriver);
    }
//Locators
    By avatarIcon = By.xpath("//a[@class='view-profile']");
    By currentPasswordField = By.cssSelector("input[id='inputProfileCurrentPassword']");
    By nameField = By.cssSelector("input[id='inputProfileName']");
    By newPasswordField = By.cssSelector("input[id='inputProfileNewPassword']");
    By saveButton = By.cssSelector("[class='btn-submit']");
    By actualProfileName = By.cssSelector("a.view-profile>span");

//Methods
    public void clickAvatar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(avatarIcon));
        driver.findElement(avatarIcon).click();
    }

    public void provideCurrentPassword(String currentPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(currentPasswordField));
        findElement(currentPasswordField).clear();
        findElement(currentPasswordField).sendKeys(currentPassword);
    }

    public void provideName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField));
        findElement(nameField).clear();
        findElement(nameField).sendKeys(name);
    }

    public void provideNewPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(newPasswordField));
        findElement(newPasswordField).clear();
        findElement(newPasswordField).sendKeys(password);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        findElement(saveButton).click();
    }

    //actualProfileName.getText()
    public String actualProfileName(){
        return findElement(actualProfileName).getText();
    }
}
