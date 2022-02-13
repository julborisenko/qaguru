package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
            resultsTable = $(".table-responsive");

    // actions
    public void openPage() {
        open("/automation-practice-form");
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

    public FormTestPage setSubjects(String[] input) {
        for (int i = 0; i < input.length; ++i) {
            subjectsField.setValue(input[i]);
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
        submitButton.click();
        return this;
    }

    public FormTestPage checkForm(String fieldName, String value) {
        resultsTable.$(byText(fieldName))
                .parent().shouldHave(text(value));
        return this;
    }

    public FormTestPage checkForm(String fieldName, String[] value) {
        String joinedString = String.join(", ", value);
        resultsTable.$(byText(fieldName))
                .parent().shouldHave(text(joinedString));
        return this;
    }
}