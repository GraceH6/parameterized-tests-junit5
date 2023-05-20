package gmail.zhansayakalsatova;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CsvSourceUsingTest extends TestBase {
    @CsvSource({"beats, Beats Studio Buds"})
    @DisplayName("Тест для проверки работы сайта")
    @ParameterizedTest(name = "Для запроса {0}, должен появиться товар {1} в списке товаров")

    void findItemByUsingSearchButton(String item, String expectedItem) {
        open("https://www.beatsbydre.com/");
        $(".search-btn-holder").$(".search__trigger__external").click();
        $(".search__input ").$("[name='search']").setValue(item);
        $$("ul.search__suggested__items li").filterBy(text(expectedItem)).first().hover();
        $("[href='/earbuds/studio-buds']").click();
        $(".product-main__head").$(".product-detail__title").shouldHave(text(expectedItem));
    }
}