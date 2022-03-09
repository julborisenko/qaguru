package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import pages.components.CalendarComponent;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTestPage {

    // components
    private CalendarComponent calendarComponent = new CalendarComponent();

    // locators
    private SelenideElement
            pageTitle = $(".main-header"),
            formHeader = $("h5"),
            firstNameField = $("#firstName"),
            lastNameField = $("#lastName"),
            emailField = $("#userEmail"),
            phoneField = $("#userNumber"),
            birthDayInput = $("#dateOfBirthInput"),
            uploadPictureForm = $("#uploadPicture"),
            addressField = $("#currentAddress"),
            subjectsField = $("#subjectsInput"),
            stateSelector = $("#state"),
            citySelector = $("#city"),
            submitButton = $("#submit"),
            resultsTable = $(".table-responsive"),
            adButton = $("#close-fixedban");

    // actions
    public void openPage() {
        open("/automation-practice-form");
        zoom(0.7);
        pageTitle.shouldHave(text("Practice Form"));
        formHeader.shouldHave(text("Student Registration Form"));
    }

    public FormTestPage setFirstName(String input) {
        firstNameField.setValue(input);
        return this;
    }

    public FormTestPage setLastName(String input) {
        lastNameField.setValue(input);
        return this;
    }

    public FormTestPage setEmail(String input) {
        emailField.setValue(input);
        return this;
    }

    public FormTestPage selectGender(String input) {
        $(byText(input)).click();
        return this;
    }

    public FormTestPage setPhone(String input) {
        phoneField.setValue(input);
        return this;
    }

    public FormTestPage setBirthDate(String day, String month, String year) {
        birthDayInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public FormTestPage setSubjects(List<String> input) {
        for (int i = 0; i < input.size(); ++i) {
            subjectsField.setValue(input.get(i));
            $("#react-select-2-option-0").click();
        }
        return this;
    }

    public FormTestPage selectHobby(String input) {
        $(byText(input)).click();
        return this;
    }

    public FormTestPage uploadFile(File input) {
        uploadPictureForm.uploadFile(input);
        return this;
    }

    public FormTestPage setAddress(String input) {
        addressField.scrollTo().setValue(input);
        return this;
    }

    public FormTestPage selectState(String input) {
        stateSelector.click();
        $(byText(input)).click();
        return this;
    }

    public FormTestPage selectCity(String input) {
        citySelector.click();
        $(byText(input)).click();
        return this;
    }

    public FormTestPage submitForm() {
        submitButton.scrollTo();
        submitButton.click();
        return this;
    }

    public FormTestPage closeAd(){
        adButton.click();
        return this;
    }

    public FormTestPage checkForm(String fieldName, String value) {
        resultsTable.$(byText(fieldName))
                .parent().shouldHave(text(value));
        return this;
    }

    public FormTestPage checkForm(String fieldName, List<String> value) {
        String joinedString = String.join(", ", value);
        resultsTable.$(byText(fieldName))
                .parent().shouldHave(text(joinedString));
        return this;
    }
}