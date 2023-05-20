package gmail.zhansayakalsatova;

import com.codeborne.selenide.CollectionCondition;
import gmail.zhansayakalsatova.domain.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static gmail.zhansayakalsatova.domain.Locale.*;

public class MethodSourceUsingTest extends TestBase {
    @DisplayName("Тест для демонстрации работы аннотации @MethodSource")
    static Stream<Arguments> testWithMethodSource() throws InterruptedException {
        return Stream.of(
                Arguments.of(en, List.of("Log in", "Register")),
                Arguments.of(de, List.of("Einloggen", "Anmelden"))
        );
    }

    @MethodSource("testWithMethodSource")
    @ParameterizedTest

    void testDataProvider(Locale siteLocale, List<String> expectedButtons) {
        open("https://stepik.org/catalog/search");
        $(".language-selector").click();
        $(".navbar__submenu-toggler").should(appear);

        $(byAttribute("data-lang", siteLocale.name())).click();
        $$(".navbar__auth").shouldHave(CollectionCondition.texts(expectedButtons));

    }
}
