package gmail.zhansayakalsatova;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CsvFileSourceUsingTest extends TestBase {
    @CsvFileSource(resources = "/successfulSearchTest.csv")
    @ParameterizedTest(name = "Запрос {0} возвращает результат {1}")

    void finfItemUsingSearchButton(String item, String expectedItem) {
        open("https://www.beatsbydre.com/");
        $(".search-btn-holder").$(".search__trigger__external").click();
        $(".search__input ").$("[name='search']").setValue(item);
        $$("ul.search__suggested__items li").filterBy(text(expectedItem)).first().hover();
        $("[href='/earbuds/studio-buds']").click();
        $(".product-main__head").$(".product-detail__title").shouldHave(text(expectedItem));
    }
}
