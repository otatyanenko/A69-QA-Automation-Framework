package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserProfilePage extends BasePage{
//Constructor
    public UserProfilePage(WebDriver givenDriver) {
        super(givenDriver);
    }
//Locators
                    //Old way
    private By avatarIcon = By.xpath("//a[@class='view-profile']");
    //By currentPasswordField = By.cssSelector("input[id='inputProfileCurrentPassword']");
    //By nameField = By.cssSelector("input[id='inputProfileName']");
    //By newPasswordField = By.cssSelector("input[id='inputProfileNewPassword']");
    //By saveButton = By.cssSelector("[class='btn-submit']");
    //By actualProfileName = By.cssSelector("a.view-profile>span");
                    //New way with page factory
    //@FindBy (xpath = "//a[@class='view-profile']") WebElement avatarIcon;
    @FindBy (css = "input[id='inputProfileCurrentPassword']") private WebElement currentPasswordField;
    @FindBy (css = "input[id='inputProfileName']") private WebElement nameField;
    @FindBy (css = "input[id='inputProfileNewPassword']") private WebElement newPasswordField;
    @FindBy (css = "[class='btn-submit']") private WebElement saveButton;
    @FindBy (css = "a.view-profile>span") private WebElement actualProfileName;

//Methods
    public void clickAvatar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(avatarIcon));
        driver.findElement(avatarIcon).click();
    }

    public void provideCurrentPassword(String currentPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(currentPasswordField));
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);
    }

    public void provideName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField));
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void provideNewPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(newPasswordField));
        newPasswordField.clear();
        newPasswordField.sendKeys(password);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    //actualProfileName.getText()
    public String actualProfileName(){
        return actualProfileName.getText();
    }
}
