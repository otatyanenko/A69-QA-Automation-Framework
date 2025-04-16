import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class BaseTest {
    public WebDriver driver = null;
    public String url = "https://qa.koel.app/";
    public WebDriverWait wait = null;
    public FluentWait waitFluent = null;
    public Actions actions = null;

    @BeforeSuite
    static void setupClass() {
        //now we will be using Grid
        //WebDriverManager.chromedriver().setup();
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
    public void launchBrowser(String BaseURL) throws MalformedURLException {
        //Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");

        //driver = new ChromeDriver(options);
        //We are preparing to use Grid
        driver = pickBrowser(System.getProperty("browser"));

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

    private WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String gridURL = "http://192.168.1.200:4444";
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "safari":
                WebDriverManager.safaridriver().setup();
                return driver = new SafariDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return driver = new EdgeDriver(edgeOptions);
            case "grid-firefox":
                capabilities.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);
            case "grid-edge":
                capabilities.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);
            /*    IE must be installed even though it was showing on the grid
            I was getting following ERROR
                You're using an unsupported command-line flag: --ie-mode-force
             What it means is that it comes from Microsoft Edge, it is
             trying to run in IE mode or something resembling it.
                I decided to comment out IE use
            case "grid-ie":
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.ignoreZoomSettings();
                ieOptions.introduceFlakinessByIgnoringSecurityDomains();
                ieOptions.requireWindowFocus();
                ieOptions.enablePersistentHovering();
                ieOptions.destructivelyEnsureCleanSession();
                ieOptions.setCapability("browserName", "internet explorer");
                //capabilities.setCapability("browserName", "internet explorer");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), ieOptions);
            */
            case "grid-safari":
                capabilities.setCapability("browserName", "safari");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), capabilities);
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                options.addArguments("--start-maximized");
                return driver = new ChromeDriver(options);
        }
    }
    //java -jar selenium-server-4.31.0.jar standalone

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    public void navigateToPage() {
        driver.get(url);
    }


    /*/ RANDOM GENERATORS
    public String generateRandomPlaylistName(){
        Faker faker = new Faker (new Locale("en-US"));
        String newName = faker.address().country();
        return newName;
    }

    public String generateRandomNameOtherWay(){
        Faker faker = new Faker (new Locale("en-US"));
        String newName = faker.name().firstName;
        return newName;
    }    */

    public String generateRandomName(){
        return UUID.randomUUID().toString().replace("-","");
    }

}