package ru.netology.delivery.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.Data.data.UserInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static ru.netology.delivery.generator.DatatGenerator.generateData;
import static ru.netology.delivery.generator.DatatGenerator.generateUser;

public class PositiveTest {

    public static String generateDate(int days, String formatPattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(formatPattern));
    }


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void happyTest() {
        UserInfo user = generateUser("ru", 4);
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getData());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();

        $("[data-test-id='success-notification'").should(visible, ofSeconds(15))
                .shouldHave(text("Встреча успешно запланирована на " + user.getData()));

        $(".button_size_m").click();

        $("[data-test-id='replan-notification'").should(visible, ofSeconds(15))
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $(".button_size_s").click();

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateData(5));
        $(".button_size_m").click();
        $("[data-test-id='success-notification'").should(visible, ofSeconds(15))
                .shouldHave(text("Встреча успешно запланирована на " + generateData(5)));

    }

    @Test
    public void happyTestNoDateChange() {
        UserInfo user = generateUser("ru", 3);
        $("[data-test-id=city] input").setValue(user.getCity());

        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();

        $("[data-test-id='success-notification'").should(visible, ofSeconds(15))
                .shouldHave(text("Встреча успешно запланирована на " + user.getData()));
    }

    @Test
    public void PositiveTest() {
        UserInfo user = generateUser("ru", 42);
        String myCity = "Магадан";
        $("[data-test-id='city'] input").setValue("Ма");
        $$(".menu-item__control").filter(exactText(myCity)).forEach(SelenideElement::click);
        $(".icon").click();
        if (generateDate(42, "M").equals(generateDate(0, "M"))) {
            ElementsCollection dates = $$(".calendar__day");
            for (SelenideElement element : dates) {
                if (element.getText().equals(generateDate(42, "d"))) {
                    element.click();
                }
            }
        } else {
            $("[data-step='1']").click();
            ElementsCollection dates = $$(".calendar__day");
            for (SelenideElement element : dates) {
                if (element.getText().equals(generateDate(42, "d"))) {
                    element.click();
                }
            }
        }
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();

        $("[data-test-id='success-notification'").should(visible, ofSeconds(15))
                .shouldHave(text("Встреча успешно запланирована на " + user.getData()));
    }

}