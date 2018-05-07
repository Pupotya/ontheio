package onthe.io;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import onthe.io.Utils.ElementWrapper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static onthe.io.Utils.ConfigurationManager.getDriver;

public abstract class BaseTest {

        protected static final CabinetPO cabinetPO = new CabinetPO();
        protected static final LoginPO loginPO = new LoginPO();
        protected static final HomePO homePO = new HomePO();
        protected static final ArticlesPO articlesPO = new ArticlesPO();
        protected static final AuthorsPO authorsPO = new AuthorsPO();
        protected static final DashPO dashPO = new DashPO();


    @AfterClass
    public void postcondition() {
        getDriver().close();
    }

    @Attachment(value = "{0}", type = "image/png")
    @AfterMethod //AfterMethod annotation - This method executes after every test execution
    public byte[] screenShot(ITestResult result) throws IOException {
        // To create reference of TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        byte[] src = new byte[0];
        //using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                return screenshot.getScreenshotAs(OutputType.BYTES);
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }

        return screenshotPassed();
    }

    private byte[] screenshotPassed() throws IOException {

        Path path = Paths.get("/Users/romandubovyi/Downloads/trelloTests/base64_decrypt.bin");
        return Files.readAllBytes(path);

    }
}