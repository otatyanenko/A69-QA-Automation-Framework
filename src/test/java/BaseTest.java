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

import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        //
        waitFluent = new FluentWait(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
    //Methods for LOG IN
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
    //Methods for Profile
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

    //SEARCH SONG
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

    //Green button ADD To in the middle of the page  SEVERAL NEXT METHODS ARE RELATED TO THIS MENU
    public void clickAddToButton() {
        WebElement addToButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn-add-to']")));
        addToButton.click();
    }

    //  Playlist menu with choice of list of existing Playlist as well as the option for new playlist shown
            // #1  This is OPTION for an EXISTING Playlist in the menu
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
            // #2  This is an OPTION for a NEW Playlist  after ADD TO green button clicked
    public void addNewPlaylist(String playlistName) {
        WebElement playlistNameField;
        playlistNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']//input")));
        playlistNameField.clear();
        playlistNameField.sendKeys(playlistName);
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='songResultsWrapper']//button[@type='submit']")));
        submitButton.click();
    }


    public boolean checkIfPlaylistEmpty() {
        boolean empty = false;
        try {
            WebElement meta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='meta']")));

        } catch (Exception e) {
            empty = true;
        }
        return empty;
    }

    public void removePlaylist() {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='del btn-delete-playlist']")));
        deleteButton.click();
    }
    //Needed ONLY if Playlist is not Empty
    public void confirmDelete() {
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='ok']")));
        okButton.click();
    }
// These next 3 methods are for the Add NEW PLAYLIST on the SIDE MENU
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

    //Chooses and clicks All Songs link from Side Menu
    public void chooseAllSongsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='songs']"))).click();
    }

    //Right click first song in the All Songs list
    public void contextClickFirstSong() {
        WebElement firstSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='items']//tr[1]")));
        actions.contextClick(firstSongElement).perform();
    }
    //right click menu choice Play
    public void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='playback']"))).click();
    }

    public boolean isSongPlaying() {
        WebElement soundBarVisualizer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='sound-bar-play']")));
        return soundBarVisualizer.isDisplayed();
    }

    // Selects Playlist from the LEFT SIDE MENU
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


    public void displayAllSongs() {
        List<WebElement> songList = driver.findElements(By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("number of songs found:" + countSongs());
        for (WebElement e : songList) {
            System.out.println(e.getText());
        }
    }
        public String getPlaylistDetails() {
            return driver.findElement(By.cssSelector("span.meta.text-secondary span.meta")).getText();
        }

    public int countSongs() {
        return driver.findElements(By.cssSelector("section#playlistWrapper td.title")).size();
    }


    public void doubleClickPlaylist() {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(5)")));
        actions.doubleClick(playlistElement).perform();
    }

    public String getRenamePlaylistSuccessMsg() {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notification.getText();
    }

    public void enterNewPlaylistName(String newPlaylistName) {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        //clear() doesn't work, element has an attribute of 'required'; we are using ctrl + A to select all then backspace
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }
}