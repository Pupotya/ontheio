package onthe.io;

import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashTest extends BaseTest {

    @BeforeMethod
    public void beforeEachTest() {
        dashPO.open();
    }

    @Story("Assert that button is Displayed in Dash Mode")
    @Test
    public void openDash(){
        loginPO.login();
        loginPO.clickProdBtn();
        loginPO.clickPopUpBtn();
        cabinetPO.switchToCurrentWindow();
        cabinetPO.clickDashBtn();
        cabinetPO.switchToCurrentWindow();
        dashPO.chooseSourcesArtiAuth();
        Assert.assertTrue(dashPO.isFullScreenButtonDisplayed());

    }
}
