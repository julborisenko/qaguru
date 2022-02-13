package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setDate(String day, String month, String year) {
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(String.format("[aria-label*='%s'][aria-label*='%s'][aria-label*='%s']", month, day, year)).click();
        // "[aria-label$='July 30th, 2008']"
    }
}