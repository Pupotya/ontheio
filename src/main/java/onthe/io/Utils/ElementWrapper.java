package onthe.io.Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static onthe.io.Utils.ConfigurationManager.getDriver;
import static onthe.io.Utils.Props.getIntProp;
import static onthe.io.Utils.Props.getProp;

public class ElementWrapper {
    private WebElement webElement;
    private SearchContext sc;
    private By wrappedBy;
    private boolean isDisabledInConfig;

    public boolean isDisabled() {
        return this.isDisabledInConfig;
    }

    private ElementWrapper(POEnum element, SearchContext sc) {
        this.wrappedBy = getProp(element);
        this.isDisabledInConfig = (this.wrappedBy == null);
        this.sc = sc;
    }

    private ElementWrapper(POEnum element, SearchContext sc, WebElement el) {
        this.wrappedBy = getProp(element);
        this.isDisabledInConfig = (this.wrappedBy == null);
        this.sc = sc;
        this.webElement = el;
    }

    public static synchronized ElementWrapper $(POEnum element) {
        By by = $Selector(element);
        WebDriverWait wait = new WebDriverWait(getDriver(), getIntProp("implicitWait"));
        ExpectedCondition<WebElement> waiter = ExpectedConditions.presenceOfElementLocated(by);
        try {
            wait.until(waiter);
        } catch (Exception e) {
            Assert.assertEquals("Test expectation failed", "Wasn't able to find an element: " +
                    element + "by selector:" + by);
        }
        return $(element, getDriver());
    }

    private boolean init() {
        if (isDisabledInConfig) return false;
        if (webElement != null) return true;
        this.webElement = sc.findElement(this.wrappedBy);
        return true;
    }

    public static ElementWrapper $(POEnum element, ElementWrapper el) {
        return $(element, el.getElement());
    }

    public static ElementWrapper $(POEnum element, SearchContext el) {
        return new ElementWrapper(element, el);
    }

    public static List<WebElement> $$we(POEnum element) {
        return getDriver().findElements(getProp(element));
    }

    public static List<ElementWrapper> $$(POEnum element) {
        List<ElementWrapper> result = new ArrayList<>();
        for(WebElement webElement: $$we(element)){
            result.add(new ElementWrapper(element, getDriver(), webElement));
        }
        return result;
    }

    public ElementWrapper click() {
        if (init()) {webElement.click();}
        return this;
    }

    public ElementWrapper clickWithJS() {
        if (init()) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click()", webElement);
        }
        return this;
    }

    public ElementWrapper type(String s) {
        if (init()) webElement.sendKeys(s);
        return this;
    }

    public ElementWrapper type(Keys s) {
        if (init()) webElement.sendKeys(s);
        return this;
    }

    public boolean isDisplayed() {
        return !isDisabledInConfig && getDriver().findElements(wrappedBy).size() > 0;

    }
    public boolean isPresentInDOM() {
        return !isDisabledInConfig && getDriver().findElements(wrappedBy).size() == 1;

    }

    public ElementWrapper clear() {
        if (init()) webElement.clear();
        return this;
    }

    public String getText() {
        return init() ? webElement.getText() : null;
    }

    public BigDecimal getTextIntValue() {
        return this.getText()!=null? new BigDecimal(this.getText().replaceAll("[^\\d.]", "")): null;
    }

    public WebElement getElement() {
        return init() ? webElement : null;
    }

    public static By $Selector(POEnum t) {
        return getProp(t);
    }

}
