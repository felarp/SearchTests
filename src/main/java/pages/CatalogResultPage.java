package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CatalogResultPage extends BasePage {


    @Step("Применить фильтр диапазона: {filterType} - {filterValue}")
    public void applyFilterRange(String filterType, String filterValue, String... options) {
        SelenideElement filterInput = $x("//legend[contains(text(), '" + filterType + "')]/following::input[@qa-mark='range-to']");
        filterInput.shouldBe(Condition.visible).clear();
        filterInput.setValue(filterValue).pressEnter();
    }

    @Step("Применить фильтр: {filterType} - {filterValue}")
    public void applyFilter(String filterType, String filterValue, String... options) {
        SelenideElement filterOption = $x("//legend[contains(text(), '" + filterType + "')]/following::*[contains(text(), '" + filterValue + "')]");
        filterOption.shouldBe(Condition.visible).click();
    }

    @Step("Сортировать по: {sortType}")
    public void sortBy(String sortType) {
        SelenideElement sortButton = $x("//button[contains(text(), 'по популярности')]");
        sortButton.click();
        $x("//button[contains(text(), '" + sortType + "')]").click();
    }

    @Step("Сбросить все фильтры")
    public void resetFilters() {
        $x("//button[contains(text(), 'Сбросить все')]").click();
    }

    @Step("Проверить, что все результаты содержат заданный фильтр: {filterValue}")
    public void verifyResultsContainFilter(String filterValue) {
        $$x("//article").forEach(element -> {
            element.shouldHave(Condition.text(filterValue));
        });
    }

    @Step("Копировать название первого товара")
    public String copyFirstProductName() {
        return $$x("//article//h3").first().getText();
    }

    @Step("Проверить, что все результаты содержат текст поиска: {searchText}")
    public void verifyResultsContainSearchText(String searchText) {
        $$x("//article//h3").forEach(element -> {
            element.shouldHave(Condition.text(searchText));
        });
    }
}


