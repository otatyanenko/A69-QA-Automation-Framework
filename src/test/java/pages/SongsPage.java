package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

//Constructor
    public class SongsPage extends BasePage{
        public SongsPage(WebDriver givenDriver) {
            super(givenDriver);
    }

//Locators
    By firstSongElement = By.xpath("//table[@class='items']//tr[1]"); //first song in the list
    By playback = By.xpath("//li[@class='playback']");
    By soundBarVisualizer = By.xpath("//div[@data-testid='sound-bar-play']");
    By viewAll = By.cssSelector("button[data-test='view-all-songs-btn']");
    // 1st song in the search result list after viewAll button was clicked
    By firstSong = By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]");
    By addToButton = By.cssSelector("[class='btn-add-to']");
    By playlistNameField = By.xpath("//section[@id='songResultsWrapper']//input");
    By submitButton = By.xpath("//section[@id='songResultsWrapper']//button[@type='submit']");
    By meta = By.xpath("//span[@class='meta']");
    By deleteButton = By.xpath("//button[@class='del btn-delete-playlist']");
    By okButton = By.xpath("//button[@class='ok']");
    By songListElement = By.cssSelector("section#playlistWrapper td.title");
    By playlistDetails = By.cssSelector("span.meta.text-secondary span.meta");

//Methods

    //Right click first song in the All Songs list
    public void contextClickFirstSong() {
        actions.contextClick(wait.until(ExpectedConditions.visibilityOfElementLocated(firstSongElement))).perform();
    }

    //right click menu choice Play
    public void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(playback)).click();
    }

    public boolean isSongPlaying() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(soundBarVisualizer)).isDisplayed();
    }

    public void viewSearchResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(viewAll)).click();
    }

    public void chooseFirstSong() {
        wait.until(ExpectedConditions.elementToBeClickable(firstSong)).click();
    }

    //Green button ADD To in the middle of the page  SEVERAL NEXT METHODS ARE RELATED TO THIS MENU
    public void clickAddToButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToButton)).click();
    }

    //  Playlist menu with choice of list of existing Playlist as well as the option for new playlist shown
    // #1  This is OPTION for an EXISTING Playlist in the menu
    public boolean choosePlaylistToAddSongTo(String playlistName) {
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
        wait.until(ExpectedConditions.elementToBeClickable(playlistNameField));
        findElement(playlistNameField).clear();
        findElement(playlistNameField).sendKeys(playlistName);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public boolean checkIfPlaylistEmpty() {
        boolean empty = false;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(meta));

        } catch (Exception e) {
            empty = true;
        }
        return empty;
    }

    public void removePlaylist() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    //Needed ONLY if Playlist is not Empty
    public void confirmDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
    }

    public void displayAllSongsToConsole() {
        List<WebElement> songList = driver.findElements(songListElement);
        System.out.println("number of songs found:" + countSongs());
        for (WebElement e : songList) {
            System.out.println(e.getText());
        }
    }

    public String getPlaylistDetails() {
        return driver.findElement(playlistDetails).getText();
    }

    public int countSongs() {
        return driver.findElements(songListElement).size();
    }
}


