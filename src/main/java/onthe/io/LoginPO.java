package onthe.io;

import io.qameta.allure.Step;
import onthe.io.Utils.POEnum;

import static onthe.io.Utils.ElementWrapper.$;
import static onthe.io.Utils.Props.getPageRelativeURI;

public class LoginPO extends BasePO<LoginPO.LoginElements> {

    enum LoginElements implements POEnum {
        URL,
        EMAIL,
        PASSWORD,
        SUBMIT_BTN,
        PROD_BTN,
        CONTINUE_POP_UP_BTN
    }

    public LoginPO() {
        super(getPageRelativeURI(LoginElements.URL));
    }

    @Step
    public void typeEmail() {
        $(LoginElements.EMAIL).type("gexibawer@one2mail.info");
    }

    @Step
    public void typePassword() {
        $(LoginElements.PASSWORD).type("q");
    }

    @Step
    public void clickSubmitBtn() {
        $(LoginElements.SUBMIT_BTN).click();
    }

    @Step
    public void login(){
        typePassword();
        typeEmail();
        clickSubmitBtn();
    }

    @Step
    public void clickProdBtn(){
        $(LoginElements.PROD_BTN).click();
    }

    @Step
    public void clickPopUpBtn(){
        $(LoginElements.CONTINUE_POP_UP_BTN).click();
    }
}
