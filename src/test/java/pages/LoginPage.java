package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
//Constructor
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }
//Locators
            //Old way
    //By emailField = By.cssSelector("[type='email']");
    //By passwordField = By.cssSelector("[type='password']");
    //By submitButton = By.cssSelector("[type='submit']");
    //By registrationLink = By.cssSelector("a[href='registration']");

            //New way using page factory
    @FindBy(css="[type='email']") private WebElement emailField;
    @FindBy(css="[type='password']") private WebElement passwordField;
    @FindBy(css="[type='submit']")private WebElement submitButton;
    @FindBy(css= "a[href='registration']") private WebElement registrationLink;

//Methods

    public void provideEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }
    public void providePassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public void login(String email, String password){
        provideEmail(email);
        providePassword(password);
        clickSubmit();
    }

    public void clickRegistrationLink(){
        wait.until(ExpectedConditions.visibilityOf(registrationLink));
        registrationLink.click();
    }
}
