package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenidePage {

    // locators
    private SelenideElement
    wikiTab = $("#wiki-tab"),
    pageBody = $(".markdown-body"),
    junitParagraph = $(byAttribute("start","3"));

    // actions
    public void openSelenidePage() {
        open("/selenide/selenide");
    }

    public SelenidePage goToWikiTab() {
        wikiTab.click();
        return this;
    }

    public SelenidePage checkChaptersContent(String name) {
        pageBody.$(byText("Chapters")).sibling(0).shouldHave(text(name));
        return this;
    }

    public SelenidePage goToChapter(String name) {
        pageBody.$(byText(name)).click();
        return this;
    }

    public SelenidePage checkJUnit5Code() {
        junitParagraph.shouldHave(text("JUnit5"));
        junitParagraph.sibling(0).shouldHave(cssClass("highlight"));
        return this;
    }

}
