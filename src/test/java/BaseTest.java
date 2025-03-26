import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.UUID;

public class BaseTest {
    public WebDriver driver = null;
    public String url = "https://qa.koel.app/";

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    public void launchBrowser(){
        //Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    public void navigateToPage() {
        driver.get(url);
    }

    public void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector("[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();
    }

    public void clickAvatar() {
        WebElement avatarIcon = driver.findElement(By.cssSelector("img[class='avatar']"));
        avatarIcon.click();
    }

    public void provideCurrentPassword(String currentPassword) {
        WebElement currentPasswordField = driver.findElement(By.cssSelector("input[id='inputProfileCurrentPassword']"));
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);
    }

    public void provideName(String name) {
        WebElement nameField = driver.findElement(By.cssSelector("input[id='inputProfileName']"));
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void provideNewPassword(String password) {
        WebElement newPasswordField = driver.findElement(By.cssSelector("input[id='inputProfileNewPassword']"));
        newPasswordField.clear();
        newPasswordField.sendKeys(password);
    }

    public void clickSave() {
        WebElement saveButton = driver.findElement(By.cssSelector("[class='btn-submit']"));
        saveButton.click();
    }

    public String generateRandomName(){
        return UUID.randomUUID().toString().replace("-","");
    }
}