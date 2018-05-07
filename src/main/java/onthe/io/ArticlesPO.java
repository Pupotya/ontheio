package onthe.io;

import io.qameta.allure.Step;
import onthe.io.Utils.POEnum;

import static onthe.io.Utils.ElementWrapper.$;
import static onthe.io.Utils.Props.getPageRelativeURI;

public class ArticlesPO extends BasePO<ArticlesPO.ArticlesElements> {

    enum ArticlesElements implements POEnum {
        URL,
        ARTICLE_LABEL
    }

    public ArticlesPO() {
        super(getPageRelativeURI(ArticlesElements.URL));
    }

    @Step
    public boolean isArticleLabelDispalyed(){
        return $(ArticlesElements.ARTICLE_LABEL).isDisplayed();
    }
}
