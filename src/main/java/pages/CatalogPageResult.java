package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;

public class CatalogPageResult extends BasePage {

    @Step("Проверить, что все результаты содержат заданный фильтр: {filterValue}")
    public void verifyResultsContainFilter(String filterValue) {
        $$x("//div[contains(@class, 'n-snippet-cell2__title')]").forEach(element -> {
            element.shouldHave(Condition.text(filterValue));
        });
    }

    @Step("Копировать название первого товара")
    public String copyFirstProductName() {
        return $$x("//div[contains(@class, 'n-snippet-cell2__title')]").first().getText();
    }

    @Step("Проверить, что все результаты содержат текст поиска: {searchText}")
    public void verifyResultsContainSearchText(String searchText) {
        $$x("//div[contains(@class, 'n-snippet-cell2__title')]").forEach(element -> {
            element.shouldHave(Condition.text(searchText));
        });
    }
}

