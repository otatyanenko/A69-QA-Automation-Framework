import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class BaseTest {
    public WebDriver driver = null;
    public String url = "https://qa.koel.app/";
    public WebDriverWait wait = null;
    public FluentWait waitFluent = null;
    public Actions actions = null;

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @DataProvider(name="IncorrectLoginData")
    public Object[][] getDataFromDataProvider(){
        return new Object[][]{
                {"invalid@testPro.ca", "invalidPass"},
                {"wrong@test.ru", ""},
                {"", ""},
                {"", "invalidPass"},
                {"wrong@test.ru", "8qUBYosp"},
                {"oksana.chaklosh@testpro.io", ""},
                {"", "8qUBYosp"},
                {"oksana.chaklosh@testpro.io", "invalidPass"}
        };
    }

    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL){
        //Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        //
        waitFluent = new FluentWait(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Actions class
        actions = new Actions(driver);
        url = BaseURL;
        navigateToPage();
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    public void navigateToPage() {
        driver.get(url);
    }


    /*// RANDOM GENERATORS
    public String generateRandomPlaylistName(){
        Faker faker = new Faker (new Locale("en-US"));
        String newName = faker.address().country();
        return newName;
    }

    public String generateRandomName(){
        Faker faker = new Faker (new Locale("en-US"));
        String newName = faker.name().firstName;
        return newName;
    }    */

    public String generateRandomName(){
        return UUID.randomUUID().toString().replace("-","");
    }

}