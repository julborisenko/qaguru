import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.href;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.text;

public class DifferenceTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void differentWaysSameElement(){
        open("/selenide/selenide");
        $("h1 span").shouldHave(text("selenide")); //ищем элемент h1 с ребенком span
        $("h1").$("span").shouldHave(text("selenide")); //ищем первый элемент h1, в нем ищем span
        //нужный текст находится в первом h1 элементе на странице
    }

    @Test
    void differentWaysDifferentElement(){
        open("/selenide/selenide");
        $("h1 .anchor").shouldHave(href("selenide--ui-testing-framework-powered-by-selenium-webdriver"));
        //ищем элемент h1 с классом anchor в каком-то из его детей
        $("h1").$(".anchor").shouldHave(href("selenide--ui-testing-framework-powered-by-selenium-webdriver"));
        //ищем первый элемент h1, в нем ищем ребенка с классом anchor, тест падает
        //нужная ссылка находится в последнем h1 элементе на странице
    }
}
