import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.SelenidePage;

public class SoftAssertionsCheckTest {

    SelenidePage selenidePage = new SelenidePage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void checkSoftAssertionsForSelenide() {
        selenidePage.openSelenidePage();
        selenidePage
                .goToWikiTab()
                .checkChaptersContent("Soft assertions")
                .goToChapter("Soft assertions")
                .checkJUnit5Code();
    }
}
