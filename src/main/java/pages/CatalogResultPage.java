package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CatalogResultPage extends BasePage {
    @Step("Применить фильтр диапазона: {filterType} - {filterValue}")
    public void applyFilterRange(String filterType, String filterValue) {
        SelenideElement filterInput = $x("//legend[contains(text(), '" + filterType + "')]/following::input[@qa-mark='range-to']");
        filterInput.scrollIntoView(true).shouldBe(Condition.visible).clear();
        filterInput.setValue(filterValue).pressEnter();
    }

    @Step("Применить фильтр: {filterType} - {filterValue}")
    public void applyFilter(String filterType, String filterValue) {
        SelenideElement filterOption = $x("//legend[contains(text(), '" + filterType + "')]/following::*[contains(text(), '" + filterValue + "')]");
        filterOption.scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    @Step("Сортировать по: {sortType}")
    public void sortBy(String sortType) {
        SelenideElement sortButton = $x("//button[contains(text(), 'по популярности')]");
        sortButton.scrollIntoView(true).click();
        $x("//button[contains(text(), '" + sortType + "')]").scrollIntoView(true).click();
    }

    @Step("Сбросить все фильтры")
    public void resetFilters() {
        $x("//button[contains(text(), 'Сбросить все')]").scrollIntoView(true).click();
    }

    @Step("Проверить, что все результаты содержат заданный фильтр: {filterValue}")
    public void verifyResultsContainFilter(String filterValue) {
        $$x("//article").forEach(element -> {
            element.scrollIntoView(true).shouldHave(Condition.text(filterValue));
        });
    }

    @Step("Копировать название первого товара")
    public String copyFirstProductName() {
        return $$x("//article//h3").first().scrollIntoView(true).getText();
    }

    @Step("Проверить, что все результаты содержат текст поиска: {searchText}")
    public void verifyResultsContainSearchText(String searchText) {
        $$x("//article//h3").forEach(element -> {
            element.scrollIntoView(true).shouldHave(Condition.text(searchText));
        });
    }

    @Step("Добавить первый ноутбук в корзину")
    public void addFirstLaptopToCart() {
        SelenideElement firstLaptop = $$x("//article").first();
        firstLaptop.scrollIntoView(true).shouldBe(Condition.visible);

        SelenideElement addToCartButton = firstLaptop.$x(".//button[contains(text(),'В корзину')]");
        addToCartButton.scrollIntoView(true).shouldBe(Condition.visible).click();

        SelenideElement cartNotification = $x("//div[contains(@class, 'notification_type_cart')]");
        cartNotification.scrollIntoView(true).shouldBe(Condition.visible);
    }
}