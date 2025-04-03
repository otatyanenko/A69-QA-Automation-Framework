import com.fasterxml.jackson.databind.ser.Serializers;
import org.testng.annotations.Test;

public class CountPlaylistSongs extends BaseTest {
    public String playlistName = "345";
    @Test
    public void countSongsInPlaylist(){
        //Steps
        provideEmail("oksana.chaklosh@testpro.io");
        providePassword("8qUBYosp");
        clickSubmit();

        //choosePlaylistByName();
        selectPlaylist(playlistName);
        displayAllSongs();
    }

    private void displayAllSongs() {

    }
/*
    public String getPlaylistDetails(){

        return ;
    }  */
}
