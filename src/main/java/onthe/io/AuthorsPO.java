package onthe.io;

import io.qameta.allure.Step;
import onthe.io.Utils.POEnum;

import static onthe.io.Utils.ElementWrapper.$;
import static onthe.io.Utils.Props.getPageRelativeURI;

public class AuthorsPO extends BasePO<AuthorsPO.AuthorsElements> {

    enum AuthorsElements implements POEnum {
        URL,
        MEDAL
    }

    public AuthorsPO() {
        super(getPageRelativeURI(AuthorsElements.URL));
    }

    @Step
    public boolean isMedalDisplayed() {
        return $(AuthorsElements.MEDAL).isDisplayed();
    }
}
