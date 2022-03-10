import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.ValueSource;
import pages.FormTestPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

public class DemoQAFormTest {

    FormTestPage formTestPage = new FormTestPage();

    File file = new File("src/test/resources/pik.png");

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @ValueSource(strings = {"26", "39"})
    @ParameterizedTest(name = "Проверка отображения значения слайдера")
    void checkSliderTest(String testData) {
        formTestPage.openSliderPage();
        formTestPage
                .setSliderValue(testData)
                .checkSliderValue(testData);
    }

    @CsvSource(value = {
            "Eve|Polastri|Female|3246547654",
            "Konstantin|Vasiliev|Male|8760945673"
    }, delimiter = '|')
    @ParameterizedTest(name = "Проверка отображения анкеты только с обязательными полями")
    void requiredFillInFormTest(String firstName, String lastName, String gender, String phoneNumber) {
        formTestPage.openFormPage();
        formTestPage
                .closeAd()
                .setFirstName(firstName)
                .setLastName(lastName)
                .selectGender(gender)
                .setPhone(phoneNumber)
                .submitForm();

        formTestPage
                .checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Gender", gender)
                .checkForm("Mobile", phoneNumber);
    }

    static Stream<Arguments> mixedArgumentsTestDataProvider() {
        return Stream.of(
                Arguments.of("Eve", "Polastri", "test@gmail.com", "Female", "3246547654", "2002", "July", "15", List.of("Biology", "Computer Science", "English"), "Reading", "39 Piscally St", "Rajasthan", "Jaipur"),
                Arguments.of("Konstantin", "Vasiliev", "test02@mail.ru", "Male", "8760945673", "1997", "September", "21", List.of("Arts", "History", "Civics"), "Sports", "23 Rusanna St", "Haryana", "Karnal")
        );
    }

    @MethodSource(value = "mixedArgumentsTestDataProvider")
    @ParameterizedTest(name = "Проверка отображения анкеты cо всеми заполненными полями")
    void fillInFormTest(String firstName, String lastName, String email, String gender, String phoneNumber, String birthYear, String birthMonth, String birthDay, List<String> subjects, String hobby, String address, String state, String city) {
        formTestPage.openFormPage();
        //заполнение анкеты
        formTestPage
                .closeAd()
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