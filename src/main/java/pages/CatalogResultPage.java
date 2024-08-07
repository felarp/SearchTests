package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static constants.Constant.TimeOuts.Xpaths.FILTER_OPTION_XPATH;
import static constants.Constant.TimeOuts.Xpaths.FILTER_RANGE_INPUT_XPATH;
import org.testng.Assert;

import java.util.List;


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
        Assert.assertTrue(filter.is(Condition.selected), "Фильтр не был применен!");
    }

    @Step("Задать диапазон сумм: {min} - {max}")
    public void applyPriceRange(String min, String max) {
        List<SelenideElement> priceInputs = (List<SelenideElement>) $$x(String.format(FILTER_RANGE_INPUT_XPATH, "Цена"));
        SelenideElement minInput = priceInputs.get(0);
        SelenideElement maxInput = priceInputs.get(1);

        minInput.setValue(min);
        maxInput.setValue(max);


        Assert.assertTrue(minInput.getValue().equals(min), "Не удалось установить минимальное значение цены!");
        Assert.assertTrue(maxInput.getValue().equals(max), "Не удалось установить максимальное значение цены!");
    }

    @Step("Сортировать по: {sortType}")
    public void sortBy(String sortType) {
        SelenideElement sortButton = $x("//button[contains(text(), 'по популярности')]");
        sortButton.scrollIntoView(true).shouldBe(Condition.visible).click();
        SelenideElement sortTypeButton = $x("//button[contains(text(), '" + sortType + "')]");
        sortTypeButton.scrollIntoView(true).shouldBe(Condition.visible).click();
        Assert.assertTrue(sortTypeButton.is(Condition.selected), "Сортировка не была применена!");
    }

    @Step("Сбросить все фильтры")
    public void resetFilters() {
        SelenideElement resetButton = $x("//button[contains(text(), 'Сбросить все')]");
        resetButton.scrollIntoView(true).shouldBe(Condition.visible).click();
        Assert.assertTrue(resetButton.is(Condition.not(Condition.visible)), "Фильтры не были сброшены!");
    }

    @Step("Проверить, что все результаты содержат заданные фильтры: {filterValues}")
    public void verifyResultsContainFilters(List<String> filterValues) {
        $$x("//article").forEach(element -> {
            filterValues.forEach(filterValue -> {
                element.scrollIntoView(true).shouldHave(Condition.text(filterValue));
            });
        });

        filterValues.forEach(filterValue ->
                Assert.assertTrue($$x("//article").texts().contains(filterValue),
                        "Результаты не содержат фильтр: " + filterValue)
        );
    }

    @Step("Копировать название первого товара")
    public String copyFirstProductName() {
        SelenideElement firstProductName = $$x("//article//h3").first();
        firstProductName.scrollIntoView(true).shouldBe(Condition.visible);
        String productName = firstProductName.getText();
        Assert.assertNotNull("Название первого товара не может быть пустым!", productName);
        return productName;
    }

    @Step("Проверить, что все результаты содержат текст поиска: {searchTexts}")
    public void verifyResultsContainSearchTexts(List<String> searchTexts) {
        $$x("//article//h3").forEach(element -> {
            searchTexts.forEach(searchText -> {
                element.scrollIntoView(true).shouldHave(Condition.text(searchText));
            });
        });

        searchTexts.forEach(searchText ->
                Assert.assertTrue($$x("//article//h3").texts().contains(searchText),
                        "Результаты не содержат текст поиска: " + searchText)
        );
    }

    @Step("Добавить первый ноутбук в корзину")
    public void addFirstLaptopToCart() {
        SelenideElement firstLaptop = $$x("//article").first();
        firstLaptop.scrollIntoView(true).shouldBe(Condition.visible);

        SelenideElement addToCartButton = firstLaptop.$x(".//button[contains(text(),'В корзину')]");
        addToCartButton.scrollIntoView(true).shouldBe(Condition.visible).click();

        SelenideElement cartNotification = $x("//div[contains(@class, 'notification_type_cart')]");
        cartNotification.scrollIntoView(true).shouldBe(Condition.visible);

        Assert.assertTrue(cartNotification.exists(), "Уведомление о добавлении в корзину не отображается!");
    }
}




