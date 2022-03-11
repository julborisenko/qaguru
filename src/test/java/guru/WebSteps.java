package guru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Открываем главную страницу")
    public WebSteps openMainPage() {
        open("https://github.com");
        return this;
    }

    @Step("Ищем репозиторий {repository}")
    public WebSteps searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
        return this;
    }

    @Step("Переходим в репозиторий {repository}")
    public WebSteps openRepository(String repository) {
        $(By.linkText(repository)).click();
        return this;
    }

    @Step("Переходим во вкладку Issues")
    public WebSteps openIssueTab() {
        $(By.partialLinkText("Issues")).click();
        return this;
    }

    @Step("Проверяем, что текст первой записи {issueText}")
    public WebSteps shouldSeeIssueWithNumber(String issueText) {
        $("#issue_1753_link").shouldHave(Condition.text(issueText));
        return this;
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
