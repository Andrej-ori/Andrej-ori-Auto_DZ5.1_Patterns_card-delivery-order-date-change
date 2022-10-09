package ru.netology.delivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.Data.data.UserInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.delivery.generator.DatatGenerator.generateUser;

public class NegativeTest {


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void invalidCity() {
        UserInfo user = generateUser("ru", 4);
        $("[data-test-id='city'] input").setValue("Moscow");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getData());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();
        $("[data-test-id='city']").shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    public void noCity() {
        UserInfo user = generateUser("ru", 4);

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getData());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();
        $("[data-test-id='city']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void invalidDate() {
        UserInfo user = generateUser("ru", 4);
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue("32.01.2025");
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();
        $("[data-test-id='date']").shouldHave(text("Неверно введена дата"));
    }

    @Test
    public void invalidDate2() {
        UserInfo user = generateUser("ru", 1);
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getData());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();
        $("[data-test-id='date']").shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void noData() {
        UserInfo user = generateUser("ru", 1);
        $("[data-test-id='city'] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();
        $("[data-test-id='date']").shouldHave(text("Неверно введена дата"));
    }

    @Test
    public void invalidName() {
        UserInfo user = generateUser("ru", 4);
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getData());
        $("[data-test-id='name'] input").setValue("Ivanov Ivan");
        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();
        $("[data-test-id='name']").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void noName() {
        UserInfo user = generateUser("ru", 4);
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getData());

        $("[data-test-id='phone'] input").setValue(user.getPhone());
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();
        $("[data-test-id='name']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void invalidPhone() {
        UserInfo user = generateUser("ru", 4);
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getData());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue("8999123");
        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();
        $("[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void noPhone() {
        UserInfo user = generateUser("ru", 4);
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getData());
        $("[data-test-id='name'] input").setValue(user.getName());

        $("[data-test-id='agreement']").click();
        $(".button_size_m").click();
        $("[data-test-id='phone']").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void n0CheckBox() {
        UserInfo user = generateUser("ru", 4);
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(user.getData());
        $("[data-test-id='name'] input").setValue(user.getName());
        $("[data-test-id='phone'] input").setValue(user.getPhone());

        $(".button_size_m").click();
        $("[data-test-id='agreement']").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}