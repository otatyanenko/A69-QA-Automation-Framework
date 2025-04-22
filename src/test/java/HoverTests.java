import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HoverTests extends BaseTest{

    @Test
    public void hoverOverPlayButton(){
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        //Steps
        loginPage.login("oksana.chaklosh@testpro.io", "8qUBYosp" );

        //Expected Results  after hovering over play button is visible
        Assert.assertTrue(homePage.isHoverOverPlayBtnDisplayed());
    }

}
