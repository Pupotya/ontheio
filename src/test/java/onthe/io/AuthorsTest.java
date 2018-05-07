package onthe.io;

import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AuthorsTest extends BaseTest {

    @BeforeMethod
    public void beforeEachTest() {
        authorsPO.open();
    }

    @Story("Checking Medal is Present on Authors Page")
    @Test
    public void isMedalPresentOnAuthorsPage() {
        loginPO.login();
        loginPO.clickProdBtn();
        loginPO.clickPopUpBtn();
        cabinetPO.switchToCurrentWindow();
        cabinetPO.clickAuthorsBtn();
        cabinetPO.waitForLoaderDissaper();
        Assert.assertTrue(authorsPO.isMedalDisplayed());

    }
}
