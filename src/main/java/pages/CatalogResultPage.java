package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static constants.Constant.TimeOuts.ConstantXpaths.FILTER_RANGE_INPUT_XPATH;
import org.testng.Assert;


public class CatalogResultPage {

    @Step("Применить фильтр: {filterType} - {filters}")
    public void applyFilter(String filterType, String... filters) {
        for (String filter : filters) {
            SelenideElement filterElement = $x(String.format("//*[contains(text(), '%s')]/following-sibling::div//label[.//span[text()='%s']]", filterType, filter));
            filterElement.scrollIntoView(true).shouldBe(Condition.visible).click();
        }
    }

    @Step("Задать диапазон цен: {min} - {max}")
    public void applyPriceRange(String min, String max) {
        ElementsCollection priceInputs = $$x(String.format(FILTER_RANGE_INPUT_XPATH, "Цена"));
        priceInputs.get(0).setValue(min);
        priceInputs.get(1).setValue(max);
    }

    @Step("Сортировать по: {sortTypes}")
    public void sortBy(String... sortTypes) {
        for (String sortType : sortTypes) {
            SelenideElement sortButton = $x(String.format("//button[contains(text(), '%s')]", sortType));
            sortButton.scrollIntoView(true).shouldBe(Condition.visible).click();
            Assert.assertTrue(sortButton.is(Condition.selected), "Сортировка не была применена для: " + sortType);
        }
    }

    @Step("Проверить результаты содержат заданные фильтры: {filterValues}")
    public void verifyResultsContainFilters(String... filterValues) {
        for (String filterValue : filterValues) {
            ElementsCollection products = $$x("//div[contains(@class, 'product-item')]");
            for (SelenideElement product : products) {
                String productText = product.getText();
                Assert.assertTrue(productText.contains(filterValue), "Продукт не содержит фильтр: " + filterValue);
            }
        }
    }
}



