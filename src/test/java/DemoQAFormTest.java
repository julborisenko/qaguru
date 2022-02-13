import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.FormTestPage;

import java.io.File;

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
        Configuration.browserSize = "1920x1080";
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