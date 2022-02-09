

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoQAFormTest {

    String firstName = "Eve";
    String lastName = "Polastri";
    String email = "test@gmail.com";
    String gender = "Female";
    String phoneNumber = "3246547654";
    String birthYear = "2002";
    String birthMonth = "July";
    String birthDay = "15";
    String subject = "Biology";
    String hobby = "Reading";
    String address = "39 Piscally St";
    String state = "Rajasthan";
    String city = "Jaipur";
    File file = new File("src/test/resources/pik.png");

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillInFormTest() {
        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
        $("h5").shouldHave(text("Student Registration Form"));

        //заполнение анкеты
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(birthYear);
        $(".react-datepicker__month-select").selectOptionContainingText(birthMonth);
        $(String.format("[class^='react-datepicker__day '][aria-label*='%s %s']", birthMonth, birthDay)).click();
        $("#subjectsInput").setValue(subject);
        $("#react-select-2-option-0").click();
        $(byText(hobby)).click();
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").scrollTo();
        $("#currentAddress").setValue(address);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        $("#submit").click();

        //проверка анкеты
        $x("//td[text()='Student Name']/following-sibling::td").shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']/following-sibling::td").shouldHave(text(email));
        $x("//td[text()='Gender']/following-sibling::td").shouldHave(text(gender));
        $x("//td[text()='Mobile']/following-sibling::td").shouldHave(text(phoneNumber));
        $x("//td[text()='Date of Birth']/following-sibling::td").shouldHave(text(birthDay+" "+birthMonth+","+birthYear));
        $x("//td[text()='Mobile']/following-sibling::td").shouldHave(text(phoneNumber));
        $x("//td[text()='Subjects']/following-sibling::td").shouldHave(text(subject));
        $x("//td[text()='Hobbies']/following-sibling::td").shouldHave(text(hobby));
        $x("//td[text()='Picture']/following-sibling::td").shouldHave(text(file.getName()));
        $x("//td[text()='Address']/following-sibling::td").shouldHave(text(address));
        $x("//td[text()='State and City']/following-sibling::td").shouldHave(text(state+" "+city));
    }
}