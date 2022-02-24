import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.FormTestPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class DemoQAFormTest {

    FormTestPage formTestPage = new FormTestPage();

    String firstName = "Eve";
    String lastName = "Polastri";
    String email = "test@gmail.com";
    String gender = "Female";
    String phoneNumber = "3246547654";
    String birthYear = "2002";
    String birthMonth = "July";
    String birthDay = "15";
    String[] subjects = {"Biology", "Computer Science", "English"};
    String hobby = "Reading";
    String address = "39 Piscally St";
    String state = "Rajasthan";
    String city = "Jaipur";
    File file = new File("src/test/resources/pik.png");


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        //Configuration.browserSize = "1920x1080";
        Configuration.browserSize = "1159x964";
    }

    @CsvSource(value = {
            "Eve|Polastri|Female|3246547654",
            "Konstantin|Vasiliev|Male|8760945673"
    }, delimiter = '|')
    @ParameterizedTest(name = "Проверка отображения анкеты только с обязательными полями")
    void requiredFillInFormTest(String firstName, String lastName, String gender, String phoneNumber) {
        formTestPage.openPage();
        formTestPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .selectGender(gender)
                .setPhone(phoneNumber)
                .submitForm();

        formTestPage
                .checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Student Email", email)
                .checkForm("Gender", gender)
                .checkForm("Mobile", phoneNumber);
    }

    @Test
    void fillInFormTest() {
        formTestPage.openPage();
        //заполнение анкеты
        formTestPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .selectGender(gender)
                .setPhone(phoneNumber)
                .setBirthDate(birthDay, birthMonth, birthYear)
                .setSubjects(subjects)
                .selectHobby(hobby)
                .uploadFile(file)
                .setAddress(address)
                .selectState(state)
                .selectCity(city)
                .submitForm();
        //проверка анкеты
        formTestPage
                .checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Student Email", email)
                .checkForm("Gender", gender)
                .checkForm("Mobile", phoneNumber)
                .checkForm("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                .checkForm("Subjects", subjects)
                .checkForm("Hobbies", hobby)
                .checkForm("Picture", file.getName())
                .checkForm("Address", address)
                .checkForm("State and City", state + " " + city);
    }
}