package onthe.io;

import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void beforeEachTest() {
        loginPO.open();
    }

    @Story("Simple Login Test")
    @Test
    public void testLogin(){
        loginPO.typeEmail();
        loginPO.typePassword();
        loginPO.clickSubmitBtn();
    }
}
