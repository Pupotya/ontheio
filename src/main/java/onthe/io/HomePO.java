package onthe.io;

import io.qameta.allure.Step;
import onthe.io.Utils.POEnum;

import static onthe.io.Utils.ConfigurationManager.getDriver;
import static onthe.io.Utils.ElementWrapper.$;
import static onthe.io.Utils.Props.getPageRelativeURI;

public class HomePO extends BasePO<HomePO.HomeElements> {

    enum HomeElements implements POEnum{
        URL,
        SOURCES,
    }

    public HomePO() {
        super(getPageRelativeURI(HomePO.HomeElements.URL));
    }
    @Step
    public boolean isSourcesDisplayed() {
        return $(HomeElements.SOURCES).isDisplayed();
    }

}
