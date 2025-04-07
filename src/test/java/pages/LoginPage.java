package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
//Constructor
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }
//Locators
    By emailField = By.cssSelector("[type='email']");
    By passwordField = By.cssSelector("[type='password']");
    By submitButton = By.cssSelector("[type='submit']");
    By registrationLink = By.cssSelector("a[href='registration']");

//Methods

    public void provideEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        findElement(emailField).clear();
        findElement(emailField).sendKeys(email);
    }
    public void providePassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        findElement(passwordField).clear();
        findElement(passwordField).sendKeys(password);
    }
    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        findElement(submitButton).click();
    }

    public void login(String email, String password){
        provideEmail(email);
        providePassword(password);
        clickSubmit();
    }

    public void clickRegistrationLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(registrationLink));
        findElement(registrationLink).click();
    }
}
