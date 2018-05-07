package onthe.io;

import io.qameta.allure.Step;
import onthe.io.Utils.POEnum;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static onthe.io.Utils.ConfigurationManager.getDriver;
import static onthe.io.Utils.ElementWrapper.$;
import static onthe.io.Utils.Props.getPageRelativeURI;

public class DashPO extends BasePO<DashPO.DashElements> {

    enum DashElements implements POEnum {
        URL,
        SOURCES_ART_AUTH,
        FULLSCREEN
    }

    public DashPO(){
        super(getPageRelativeURI(DashElements.URL));
    }

    @Step
    public void chooseSourcesArtiAuth() {
        waitFor$(DashElements.SOURCES_ART_AUTH);
        $(DashElements.SOURCES_ART_AUTH).click();
    }

    @Step
    public boolean isFullScreenButtonDisplayed() {
        waitFor$(DashElements.FULLSCREEN);
        return $(DashElements.FULLSCREEN).isDisplayed();
    }
}
