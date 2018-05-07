package onthe.io;

import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CabinetTest extends BaseTest {

    @BeforeMethod
    public void beforeEachTest() {
        cabinetPO.open();
    }

    @Story("Checking Sources is Present on Home Page")
    @Test
    public void isSourcesPresentOnHomePage(){
        loginPO.login();
        loginPO.clickProdBtn();
        loginPO.clickPopUpBtn();
        cabinetPO.switchToCurrentWindow();
        Assert.assertTrue(homePO.isSourcesDisplayed());

    }

}
