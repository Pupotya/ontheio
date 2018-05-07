package onthe.io;

import io.qameta.allure.Step;
import onthe.io.Utils.POEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import static onthe.io.Utils.ConfigurationManager.getDriver;
import static onthe.io.Utils.ElementWrapper.$;
import static onthe.io.Utils.ElementWrapper.$Selector;
import static onthe.io.Utils.Props.getIntProp;
import static onthe.io.Utils.Props.getProp;

public abstract class BasePO<T extends Enum<T>> {
    protected final String REL_URL;
    protected final String BASE_URL;

    public BasePO(String url) {
        REL_URL = url;
        BASE_URL = getProp("baseUrl");
    }


    private BasePO open(String url) {
        getDriver().get(url);
        return this;
    }

    public BasePO open() {
        String s;
        if (REL_URL == "/") {
            s = BASE_URL;
        } else s = BASE_URL + REL_URL;
        s = s.replaceAll("[?]","&").
        replaceFirst("&","?");
        open(s);

        return this;
    }

    public String getColor(WebElement el) {
        return el.getCssValue("color");
    }

    public BasePO sleep(int secondsOfSleep) {
        try {
            Thread.sleep(secondsOfSleep * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public WebElement waitUntilElementIsClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilElementIsDisplayed(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForText(POEnum el, String s) {
        if ($(el).isDisabled()) return;
        By by = $Selector(el);
        WebDriverWait wait = new WebDriverWait(getDriver(), getIntProp("implicitWait"));
        ExpectedCondition<Boolean> waiter = (s != null) ? ExpectedConditions.textToBePresentInElementLocated(by, s)
                : ExpectedConditions.invisibilityOfElementLocated(by);
        try {
            wait.until(waiter);
        } catch (Exception e) {
            if (s != null) Assert.assertEquals("Test expectetion failed ", "Wasn't able to find an element: " +
                    el + "by selector: " + by + " with text: " + s + " Actual text was:" + $(el).getText());
        }
    }

    @Step
    public void waitFor$(POEnum el) {
        if ($(el).isDisabled()) return;
        By by = $Selector(el);
        WebDriverWait wait = new WebDriverWait(getDriver(), getIntProp("implicitWait"));
        ExpectedCondition<WebElement> waiter = ExpectedConditions.visibilityOfElementLocated(by);
        try {
            wait.until(waiter);
        } catch (Exception e) {
             Assert.assertEquals("Test expectetion failed ", "Wasn't able to find an element: " +
                    el + "by selector: " + by);
        }
    }

    public void waitForDissapear$(POEnum el) {
        if ($(el).isDisabled()) return;
        By by = $Selector(el);
        WebDriverWait wait = new WebDriverWait(getDriver(), getIntProp("implicitWait"));
        ExpectedCondition<Boolean> waiter = ExpectedConditions.invisibilityOfElementLocated(by);
        try {
            wait.until(waiter);
        } catch (Exception e) {
             Assert.assertEquals("Test expectetion failed ", "Element doesn't disappear: " +
                    el + "by selector: " + by);
        }

    }

    @Step
    public void waitForLoaderDissaper() {
        waitForDissapear$(CabinetPO.CabinetElements.LOADER);
    }

    @Step
    public void switchToCurrentWindow(){
        String winHandleBefore = getDriver().getWindowHandle();
        getDriver().switchTo().window(winHandleBefore).close();
        for(String winHandle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(winHandle);
        }
//        getDriver().close();
//        getDriver().switchTo().window(winHandleBefore);
    }

}
