import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.WebSteps;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class IssueCheckTest {

    final static String repository = "selenide/selenide";
    final static String issueText = "add method to set date value in";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void listenerSelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();

        $(By.linkText(repository)).click();
        $(By.partialLinkText("Issues")).click();
        $("#issue_1753_link").shouldHave(Condition.text(issueText));
    }

    @Test
    void lambdaStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий " + repository, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(repository);
            $(".header-search-input").submit();
        });

        step("Переходим в репозиторий " + repository, () -> {
            $(By.linkText(repository)).click();
        });

        step("Переходим во вкладку Issues", () -> {
            $(By.partialLinkText("Issues")).click();
        });

        step("Проверяем, что текст первой записи " + issueText, () -> {
            $("#issue_1753_link").shouldHave(Condition.text(issueText));
        });
    }

    @Test
    void annotationStepsTest() {

        WebSteps steps = new WebSteps();

        steps.openMainPage()
                .searchForRepository(repository)
                .openRepository(repository)
                .openIssueTab()
                .shouldSeeIssueWithNumber(issueText);

        steps.takeScreenshot();
    }

}