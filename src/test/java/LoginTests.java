import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {
    @Test
    public void loginValidEmailValidPassword() {

//      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://qa.koel.app/";
        driver.get(url);
        //Assert.assertEquals(driver.getCurrentUrl(), url);
        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        WebElement passwordField = driver.findElement(By.cssSelector("[type='password']"));
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));

        emailField.clear();
        emailField.sendKeys("oksana.chaklosh@testpro.io");

        passwordField.clear();
        passwordField.sendKeys("8qUBYosp");

        submitButton.click();

        WebElement avatarIcon = driver.findElement(By.xpath("//img[@class='avatar']"));
        
        Assert.assertTrue(avatarIcon.isDisplayed());

        //close the browser
        driver.quit();
    }
}
