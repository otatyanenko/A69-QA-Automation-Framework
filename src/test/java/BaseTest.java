import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import javax.swing.*;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.UUID;

public class BaseTest {
    public WebDriver driver = null;
    public String url = "https://qa.koel.app/";
    public WebDriverWait wait = null;
    public FluentWait waitFluent = null;
    public Action actions = null;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        //
        waitFluent = new FluentWait(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Actions class

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

    public void provideEmail(String email) {
        //WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        //New way using waits
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='email']")));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void providePassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='password']")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='submit']")));
        submitButton.click();
    }

    public void clickAvatar() {
        WebElement avatarIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img[class='avatar']")));
        avatarIcon.click();
    }

    public void provideCurrentPassword(String currentPassword) {
        WebElement currentPasswordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id='inputProfileCurrentPassword']")));
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);
    }

    public void provideName(String name) {
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id='inputProfileName']")));
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void provideNewPassword(String password) {
        WebElement newPasswordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id='inputProfileNewPassword']")));
        newPasswordField.clear();
        newPasswordField.sendKeys(password);
    }

    public void clickSave() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-submit']")));
        saveButton.click();
    }

    public String generateRandomName(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public void searchSong(String song){
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[type='search']")));
        searchField.clear();
        searchField.sendKeys(song);
        searchField.sendKeys(Keys.RETURN);
    }

    public void viewSearchResults() {
        WebElement viewAll = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-test='view-all-songs-btn']")));
        viewAll.click();
    }

    public void chooseFirstSong() {
        WebElement firstSong = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]")));
        firstSong.click();
    }

    public void clickAddToButton() {
        WebElement addToButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-add-to']")));
        addToButton.click();
    }

    public boolean choosePlaylistToAddSongTo(String playlistName) {
        //WebElement playlist = driver.findElement(By.cssSelector("section[id='queueWrapper'] li:nth-of-type(5)"));
        //WebElement playlist = driver.findElement(By.xpath("//section[@id='queueWrapper']//li[5]"));
        boolean exists = true;
        try {
            WebElement playlist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'" + playlistName + "')]")));
            playlist.click();
        } catch (Exception e){
            exists = false;
        }
        return exists;
    }
        // Selects Playlist from the left side menu
    public boolean selectPlaylist(String playlistName) {
        boolean exists = false; //default playlist does not exist
        try {
            //Find the element using xpath
            WebElement selectedPlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='playlists']//a[contains(text(), '" + playlistName + "')]")));
            //check if element is displayed / exists
           if(selectedPlaylist.isDisplayed()) {
                selectedPlaylist.click();
                exists = true; // set to true element found
           }
        } catch (NoSuchElementException e){
            // we are allowing for our program to continue
        } catch (Exception e){
            // just in case there are other exceptions like timeout
        }

        return exists;
    }

    public void removePlaylist() {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='del btn-delete-playlist']")));
        deleteButton.click();
    }

    public void confirmDelete() {
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='ok']")));
        okButton.click();
    }

    public void clickAddPlaylistButton() {
        WebElement addPlaylistButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@data-testid='sidebar-create-playlist-btn']")));
        addPlaylistButton.click();
    }

    public void clickNewPlaylist() {
        WebElement newPlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-testid='playlist-context-menu-create-simple']")));
        newPlaylist.click();
    }

    public void inputPlaylistName(String name) {
        WebElement playlistNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@class='create']//input")));
        playlistNameField.sendKeys(name);
        playlistNameField.sendKeys(Keys.RETURN);
    }

    public void playNextSong() {
        WebElement nextSongButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@data-testid='play-next-btn']")));
        nextSongButton.click();
    }

    public void clickPlaySong() {
        WebElement playButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-testid='play-btn']")));
        playButton.click();
    }

    public void addNewPlaylist(String playlistName) {
        WebElement playlistNameField;
          playlistNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']//input")));
          playlistNameField.clear();
          playlistNameField.sendKeys(playlistName);
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']//button[@type='submit']")));
        submitButton.click();
    }
}