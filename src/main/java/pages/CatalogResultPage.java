package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
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
        ElementsCollection priceInputs = $$x(String.format(FILTER_RANGE_INPUT_XPATH, "Цена"));
        SelenideElement minInput = priceInputs.get(0);
        SelenideElement maxInput = priceInputs.get(1);

        minInput.setValue(min);
        maxInput.setValue(max);
    }

    @Step("Сортировать по: {sortType}")
    public void sortBy(String sortType) {
        SelenideElement sortButton = $x("//button[contains(text(), 'По популярности')]");
        sortButton.scrollIntoView(true).shouldBe(Condition.visible).click();
        SelenideElement sortTypeButton = $x(String.format("//button[contains(text(),'%s')]", sortType));
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
    public void verifyResultsContainFilters(String... filterValues) {
        List<String> filterValuesList = List.of(filterValues);

        $$x("//article").forEach(element -> {
            filterValuesList.forEach(filterValue -> {
                element.scrollIntoView(true).shouldHave(Condition.text(filterValue));
            });
        });
    }

    @Step("Проверить, что все результаты содержат текст поиска: {searchTexts}")
    public void verifyResultsContainSearchTexts(String... searchTexts) {
        List<String> searchTextsList = List.of(searchTexts);


        $$x("//article//h3").filter(Condition.visible).forEach(element -> {
            searchTextsList.forEach(searchText -> {
                element.scrollIntoView(true).shouldHave(Condition.text(searchText));
            });
        });

        searchTextsList.forEach(searchText ->
                Assert.assertTrue($$x("//article//h3").texts().contains(searchText),
                        "Результаты не содержат текст поиска: " + searchText)
        );
    }
}



