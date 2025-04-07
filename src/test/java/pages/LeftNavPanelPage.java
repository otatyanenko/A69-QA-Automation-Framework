package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;

public class LeftNavPanelPage extends BasePage{
//Constructor
    public LeftNavPanelPage(WebDriver givenDriver) {
        super(givenDriver);
    }

//Locators
    By homeLink = By.xpath("//a[@class='home']");
    By currentQueueLink = By.xpath("//a[@class='queue']");
    By allSongsLink = By.xpath("//a[@class='songs']");
    By albumsLink = By.xpath("//a[@class='albums']");
    By artistsLink = By.xpath("//a[@class='artists']");
    By playlistPlusSign = By.xpath("//i[@data-testid='sidebar-create-playlist-btn']");
    By favoritesLink = By.xpath("//li[@class='playlist favorites']");
    By recentlyPlayedLink = By.xpath("//li[@class='playlist recently-played']");
    By newPlaylist = By.xpath("//li[@data-testid='playlist-context-menu-create-simple']");
    By playlistNameField = By.xpath("//form[@class='create']//input");
    By searchField = By.cssSelector("[type='search']");
    By playlistElement = By.cssSelector(".playlist:nth-child(5)");
    By playlistInputField = By.cssSelector("[name='name']");
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
        findElement(playlistPlusSign).click();
    }

    public void clickNewPlaylist() {
        wait.until(ExpectedConditions.elementToBeClickable(newPlaylist));
        findElement(newPlaylist).click();
    }

    public void inputPlaylistName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(playlistNameField));
        findElement(playlistNameField).sendKeys(name);
        findElement(playlistNameField).sendKeys(Keys.RETURN);
    }

    //Chooses and clicks All Songs link from Side Menu
    public void chooseAllSongsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(allSongsLink)).click();
    }

    //SEARCH SONG
    public void searchSong(String song){
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        findElement(searchField).clear();
        findElement(searchField).sendKeys(song);
        findElement(searchField).sendKeys(Keys.RETURN);
    }

    public void doubleClickPlaylist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(playlistElement));
        actions.doubleClick(findElement(playlistElement)).perform();
    }

    public String getRenamePlaylistSuccessMsg() {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notification.getText();
    }

    public void enterNewPlaylistName(String newPlaylistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(playlistInputField));
        //clear() doesn't work, element has an attribute of 'required'; we are using ctrl + A to select all then backspace
        findElement(playlistInputField).sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        findElement(playlistInputField).sendKeys(newPlaylistName);
        findElement(playlistInputField).sendKeys(Keys.ENTER);
    }

    public void clickHomeLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeLink)).click();
    }

    public void clickCurrentQueueLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentQueueLink)).click();
    }

    public void clickAlbumsLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(albumsLink)).click();
    }

    public void clickArtistsLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(artistsLink)).click();
    }

    public void clickFavoritesLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(favoritesLink)).click();
    }

    public void clickRecentlyPlayedLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(recentlyPlayedLink)).click();
    }

}
