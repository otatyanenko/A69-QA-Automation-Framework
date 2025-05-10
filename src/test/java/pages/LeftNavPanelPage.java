package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;

public class LeftNavPanelPage extends BasePage{
//Constructor
    public LeftNavPanelPage(WebDriver givenDriver) {
        super(givenDriver);
    }

//Locators
            //Old Way
    //By homeLink = By.xpath("//a[@class='home']");
    //By currentQueueLink = By.xpath("//a[@class='queue']");
    //By allSongsLink = By.xpath("//a[@class='songs']");
    //By albumsLink = By.xpath("//a[@class='albums']");
    //By artistsLink = By.xpath("//a[@class='artists']");
    //By playlistPlusSign = By.xpath("//i[@data-testid='sidebar-create-playlist-btn']");
    //By favoritesLink = By.xpath("//li[@class='playlist favorites']");
    //By recentlyPlayedLink = By.xpath("//li[@class='playlist recently-played']");
    //By newPlaylist = By.xpath("//li[@data-testid='playlist-context-menu-create-simple']");
    //By playlistNameField = By.xpath("//form[@class='create']//input");
    //By searchField = By.cssSelector("[type='search']");
    //By playlistElement = By.cssSelector(".playlist:nth-child(5)");
    //By playlistInputField = By.cssSelector("[name='name']");
                        //New way using page factory
    @FindBy (xpath = "//a[@class='home']") private WebElement homeLink;
    @FindBy (xpath = "//a[@class='queue']") private WebElement currentQueueLink;
    @FindBy (xpath = "//a[@class='songs']") private WebElement allSongsLink;
    @FindBy (xpath = "//a[@class='albums']") private WebElement albumsLink;
    @FindBy (xpath = "//a[@class='artists']") private WebElement artistsLink;
    @FindBy (xpath = "//i[@data-testid='sidebar-create-playlist-btn']") private WebElement playlistPlusSign;
    @FindBy (xpath = "//li[@class='playlist favorites']") private WebElement favoritesLink;
    @FindBy (xpath = "//li[@class='playlist recently-played']") private WebElement recentlyPlayedLink;
    @FindBy (xpath = "//li[@data-testid='playlist-context-menu-create-simple']") private WebElement newPlaylist;
    @FindBy (xpath = "//form[@class='create']//input") private WebElement playlistNameField;
    @FindBy (css = "[type='search']") private WebElement searchField;
    @FindBy (css = ".playlist:nth-child(5)") private WebElement playlistElement;
    @FindBy (css = "[name='name']") private WebElement playlistInputField;
//Methods

    // Checks if playlist exists, if yes Selects Playlist by name from the LEFT SIDE MENU
    public boolean selectPlaylistSideMenu(String playlistName) {
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

    // These next 3 methods are for the Add NEW PLAYLIST on the SIDE MENU
    public void clickAddPlaylistButtonPlusSign() {
        wait.until(ExpectedConditions.elementToBeClickable(playlistPlusSign));
        playlistPlusSign.click();
    }

    public void clickNewPlaylist() {
        wait.until(ExpectedConditions.elementToBeClickable(newPlaylist));
        newPlaylist.click();
    }

    public void inputPlaylistName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(playlistNameField));
        playlistNameField.sendKeys(name);
        playlistNameField.sendKeys(Keys.RETURN);
    }

    //Chooses and clicks All Songs link from Side Menu
    public void chooseAllSongsList() {
        wait.until(ExpectedConditions.visibilityOf(allSongsLink)).click();
    }

    //SEARCH SONG
    public void searchSong(String song){
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.clear();
        searchField.sendKeys(song);
        searchField.sendKeys(Keys.RETURN);
    }

    public void doubleClickPlaylist() {
        wait.until(ExpectedConditions.visibilityOf(playlistElement));
        actions.doubleClick(playlistElement).perform();
    }
/*
    public String getRenamePlaylistSuccessMsg() {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notification.getText();
    }
*/
    public void enterNewPlaylistName(String newPlaylistName) {
        wait.until(ExpectedConditions.visibilityOf(playlistInputField));
        //clear() doesn't work, element has an attribute of 'required'; we are using ctrl + A to select all then backspace
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public void clickHomeLink(){
        wait.until(ExpectedConditions.visibilityOf(homeLink)).click();
    }

    public void clickCurrentQueueLink(){
        wait.until(ExpectedConditions.visibilityOf(currentQueueLink)).click();
    }

    public void clickAlbumsLink(){
        wait.until(ExpectedConditions.visibilityOf(albumsLink)).click();
    }

    public void clickArtistsLink(){
        wait.until(ExpectedConditions.visibilityOf(artistsLink)).click();
    }

    public void clickFavoritesLink(){
        wait.until(ExpectedConditions.visibilityOf(favoritesLink)).click();
    }

    public void clickRecentlyPlayedLink(){
        wait.until(ExpectedConditions.visibilityOf(recentlyPlayedLink)).click();
    }

}
