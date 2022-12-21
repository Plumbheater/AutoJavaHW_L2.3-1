import lombok.val;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    private String date1 = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    private String date2 = LocalDate.now().plusDays(40).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Test
    public void test1() {
        open("http://localhost:9999/");
        val user = GeneratorClass.Registration.generateByCard("ru");
        val name = user.getName();
        val phone = user.getPhoneNumber();
        val city = user.getCity();

        $("span[data-test-id = city] input").setValue(city.substring(0,2));
        $$("div.menu div.menu-item").find(exactText(city)).click();
        $("span[data-test-id = date] input").sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        $("span[data-test-id = date] input").setValue(date1);
        $("span[data-test-id = name] input").setValue(name);
        $("span[data-test-id = phone] input").setValue(phone);
        $("label[data-test-id = agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldBe(exactText("Встреча успешно запланирована на " + date1), Duration.ofSeconds(15));
        $("span[data-test-id = date] input").sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        $("span[data-test-id = date] input").setValue(date2);
        $$("button").find(exactText("Запланировать")).click();
        $("div[data-test-id = replan-notification]").shouldBe(exactText("Необходимо подтверждение").visible, Duration.ofSeconds(15));
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldBe(exactText("Встреча успешно запланирована на " + date2), Duration.ofSeconds(15));
    }

}

