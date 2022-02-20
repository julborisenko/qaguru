import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.DragAndDropPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTest {

    DragAndDropPage dragAndDropPage = new DragAndDropPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void checkDragAndDrop() {
        dragAndDropPage.openDragAndDropPage();
        dragAndDropPage
                .dragColumnAToB()
                .checkColumnHeader();
    }
}
