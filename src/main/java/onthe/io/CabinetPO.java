package onthe.io;

import io.qameta.allure.Step;
import onthe.io.Utils.ConfigurationManager;
import onthe.io.Utils.ElementWrapper;
import onthe.io.Utils.POEnum;
import org.openqa.selenium.By;

import static onthe.io.Utils.ConfigurationManager.getDriver;
import static onthe.io.Utils.ElementWrapper.$;
import static onthe.io.Utils.Props.getPageRelativeURI;

public class CabinetPO extends BasePO<CabinetPO.CabinetElements> {

        enum CabinetElements implements POEnum {
            URL,
            HOME_BTN,
            ARTICLES_BTN,
            AUTHORS_BTN,
            DASH_BTN,
            LOADER
        }

    private static final String URL_CABINET = "https://media.onthe.io/Gzb_9oMawcAHAd8s0jAcSuNwbWyIu9Ja?rcoqa9";

    public CabinetPO() {
        super(getPageRelativeURI(CabinetElements.URL));
    }

    @Step
    public void clickHomeBtn() {
        $(CabinetElements.HOME_BTN).click();
    }

    @Step
    public void clickArticlesBtn() {
        $(CabinetElements.ARTICLES_BTN).click();
    }

    @Step
    public void clickAuthorsBtn() {
        $(CabinetElements.AUTHORS_BTN).click();
    }

    @Step
    public void clickDashBtn() {
        $(CabinetElements.DASH_BTN).click();
    }

}
