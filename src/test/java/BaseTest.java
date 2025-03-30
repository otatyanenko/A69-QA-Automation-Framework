import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.UUID;

public class BaseTest {
    public WebDriver driver = null;
    public String url = "https://qa.koel.app/";

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

    public void searchSong(String song){
        WebElement searchField = driver.findElement(By.cssSelector("[type='search']"));
        searchField.clear();
        searchField.sendKeys(song);
        searchField.sendKeys(Keys.RETURN);
    }

    public void viewSearchResults() {
        WebElement viewAll = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn']"));
        viewAll.click();
    }

    public void chooseFirstSong() {
        WebElement firstSong = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]"));
        firstSong.click();
    }

    public void clickAddToButton() {
        WebElement addToButton = driver.findElement(By.cssSelector("[class='btn-add-to']"));
        addToButton.click();
    }

    public void choosePlaylistToAddSongTo(String playlistName) {
        //WebElement playlist = driver.findElement(By.cssSelector("section[id='queueWrapper'] li:nth-of-type(5)"));
        //WebElement playlist = driver.findElement(By.xpath("//section[@id='queueWrapper']//li[5]"));
        WebElement playlist = driver.findElement(By.xpath("//section[@id='songResultsWrapper']//li[contains(text(),'" + playlistName + "')]"));
        playlist.click();
    }

    protected void selectPlaylist() {
        WebElement selectedPlaylist = driver.findElement(By.xpath("//section[@id='playlists']//li[6]"));
        selectedPlaylist.click();
    }

    protected void removePlaylist() {
        WebElement deleteButton = driver.findElement(By.xpath("//button[@class='del btn-delete-playlist']"));
        deleteButton.click();
    }

    protected void confirmDelete() {
        WebElement okButton = driver.findElement(By.xpath("//button[@class='ok']"));
        okButton.click();
    }

    protected void clickAddPlaylistButton() {
        WebElement addPlaylistButton = driver.findElement(By.xpath("//i[@data-testid='sidebar-create-playlist-btn']"));
        addPlaylistButton.click();
    }

    protected void clickNewPlaylist() {
        WebElement newPlaylist = driver.findElement(By.xpath("//li[@data-testid='playlist-context-menu-create-simple']"));
        newPlaylist.click();
    }

    protected void inputPlaylistName(String name) {
        WebElement playlistNameField = driver.findElement(By.xpath("//form[@class='create']//input"));
        playlistNameField.sendKeys(name);
        playlistNameField.sendKeys(Keys.RETURN);
    }

    protected void playNextSong() {
        WebElement nextSongButton = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        nextSongButton.click();
    }

    protected void clickPlaySong() {
        WebElement playButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        playButton.click();
    }
}