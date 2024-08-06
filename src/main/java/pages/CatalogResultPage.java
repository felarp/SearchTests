package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static constants.Constant.TimeOuts.Xpaths.FILTER_OPTION_XPATH;
import static constants.Constant.TimeOuts.Xpaths.FILTER_RANGE_INPUT_XPATH;


public class CatalogResultPage extends BasePage {



    @Step("Применить фильтр: {filterType} - {filterValue}")
    public void applyFilter(String filterType, String filterValue) {
        SelenideElement filter;
        if (filterValue.contains("-")) {
            filter = $x(String.format(FILTER_RANGE_INPUT_XPATH, filterType));
        } else {
            filter = $x(String.format(FILTER_OPTION_XPATH, filterType, filterValue));
        }
        filter.scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    @Step("Сортировать по: {sortType}")
    public void sortBy(String sortType) {
        SelenideElement sortButton = $x("//button[contains(text(), 'по популярности')]");
        sortButton.scrollIntoView(true).shouldBe(Condition.visible).click();
        $x("//button[contains(text(), '" + sortType + "')]").scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    @Step("Сбросить все фильтры")
    public void resetFilters() {
        $x("//button[contains(text(), 'Сбросить все')]").scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    @Step("Проверить, что все результаты содержат заданный фильтр: {filterValue}")
    public void verifyResultsContainFilter(String filterValue) {
        $$x("//article").forEach(element -> {
            element.scrollIntoView(true).shouldHave(Condition.text(filterValue));
        });
    }

    @Step("Копировать название первого товара")
    public String copyFirstProductName() {
        return $$x("//article//h3").first().scrollIntoView(true).shouldBe(Condition.visible).getText();
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

    public SelenideElement getElementContainingText(String text) {
        return $x("//*[contains(text(), '" + text + "')]");
    }
}




