package onthe.io;

import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArticlesTest extends BaseTest {

    @BeforeMethod
    public void beforeEachTest() {
        articlesPO.open();
    }

    @Story("Checking Label is Present on Articles Page")
    @Test
    public void isSourcesPresentOnHomePage(){
        loginPO.login();
        loginPO.clickProdBtn();
        loginPO.clickPopUpBtn();
        cabinetPO.switchToCurrentWindow();
        cabinetPO.clickArticlesBtn();
        cabinetPO.waitForLoaderDissaper();
        Assert.assertTrue(articlesPO.isArticleLabelDispalyed());

    }
}
