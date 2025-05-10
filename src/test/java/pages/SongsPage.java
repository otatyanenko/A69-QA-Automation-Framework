package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

//Constructor
    public class SongsPage extends BasePage{
        public SongsPage(WebDriver givenDriver) {
            super(givenDriver);
    }

//Locators
                        //Old Way
    //By firstSongElement = By.xpath("//table[@class='items']//tr[1]"); //first song in the list
    //By playback = By.xpath("//li[@class='playback']");
    //By soundBarVisualizer = By.xpath("//div[@data-testid='sound-bar-play']");
    //By viewAll = By.cssSelector("button[data-test='view-all-songs-btn']");
    // 1st song in the search result list after viewAll button was clicked
    //By firstSong = By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]");
    //By addToButton = By.cssSelector("[class='btn-add-to']");
    //By playlistNameField = By.xpath("//section[@id='songResultsWrapper']//input");
    //By submitButton = By.xpath("//section[@id='songResultsWrapper']//button[@type='submit']");
    //By meta = By.xpath("//span[@class='meta']");
    //By deleteButton = By.xpath("//button[@class='del btn-delete-playlist']");
    //By okButton = By.xpath("//button[@class='ok']");
    private By songListElement = By.cssSelector("section#playlistWrapper td.title");
    private By playlistDetails = By.cssSelector("span.meta.text-secondary span.meta");
                        //New way with page factory
    @FindBy (xpath = "//table[@class='items']//tr[1]") private WebElement firstSongElement;
    @FindBy (xpath = "//li[@class='playback']") private WebElement playback;
    @FindBy (xpath = "//div[@data-testid='sound-bar-play']") private WebElement soundBarVisualizer;
    @FindBy (css = "button[data-test='view-all-songs-btn']") private WebElement viewAll;
    @FindBy (xpath = "//section[@id='songResultsWrapper']//table[@class='items']//tr[1]") private WebElement firstSong;
    @FindBy (css = "[class='btn-add-to']") private WebElement addToButton;
    @FindBy (xpath = "//section[@id='songResultsWrapper']//input") private WebElement playlistNameField;
    @FindBy (xpath = "//section[@id='songResultsWrapper']//button[@type='submit']") private WebElement submitButton;
    @FindBy (xpath = "//span[@class='meta']") private WebElement meta;
    @FindBy (xpath = "//button[@class='del btn-delete-playlist']") private WebElement deleteButton;
    @FindBy (xpath = "//button[@class='ok']") private WebElement okButton;
    //@FindBy (css = "section#playlistWrapper td.title") WebElement songListElement;
    //@FindBy (css = "span.meta.text-secondary span.meta") WebElement playlistDetails;
//Methods

    //Right click first song in the All Songs list
    public void contextClickFirstSong() {
        actions.contextClick(wait.until(ExpectedConditions.visibilityOf(firstSongElement))).perform();
    }

    //right click menu choice Play
    public void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOf(playback)).click();
    }

    public boolean isSongPlaying() {
    return wait.until(ExpectedConditions.visibilityOf(soundBarVisualizer)).isDisplayed();
    }

    public void viewSearchResults() {
        wait.until(ExpectedConditions.visibilityOf(viewAll)).click();
    }

    public void clickFirstSong() {
        wait.until(ExpectedConditions.elementToBeClickable(firstSong)).click();
    }

    //Green button ADD To in the middle of the page  SEVERAL NEXT METHODS ARE RELATED TO THIS MENU
    public void clickAddToButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToButton)).click();
    }

    //  Playlist menu with choice of list of existing Playlist as well as the option for new playlist shown
    // #1  This is OPTION for an EXISTING Playlist in the menu
    public boolean clickExistingPlaylistToAddSongTo(String playlistName) {
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
        playlistNameField.clear();
        playlistNameField.sendKeys(playlistName);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public boolean checkIfPlaylistEmpty() {
        boolean empty = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(meta));

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
/*
    public List<String> getAllPlaylistNames(){
        List<WebElement> playlistElements = driver.findElements(playlists);
        List<String> playlistNames = new ArrayList<>();

        for(int i=0; i< playlistElements.size(); i++){
            String playlistName1 = playlistElements.get
        }
    }*/
}


