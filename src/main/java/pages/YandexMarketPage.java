package pages;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;


public class YandexMarketPage extends BasePage {

    @Step("Открыть каталог")
    public void openCatalog() {
        $x("//noindex[@class='_1yVxH']").click();
    }

    @Step("Выбрать категорию: {categoryPath}")
    public void selectCategory(String... categoryPath) {
        for (String category : categoryPath) {
            SelenideElement categoryElement = $x("//a[contains(@class, '_3yHCR') and text()='" + category + "']");
            categoryElement.shouldBe(Condition.visible).click();
        }
    }

    @Step("Применить фильтр диапазона: {filterValue}")
    public void applyFilterRange(String filterType, String filterValue) {
        SelenideElement filterInput = $x("//legend[contains(text(), '" + filterType + "')]/following::input[@qa-mark='range-to']");
        filterInput.shouldBe(Condition.visible).clear();
        filterInput.setValue(filterValue).pressEnter();
    }

    @Step("Применить фильтр: {filterValue}")
    public void applyFilter(String filterType, String filterValue) {
        SelenideElement filterOption = $x("//legend[contains(text(), '" + filterType + "')]/following::*[contains(text(), '" + filterValue + "')]");
        filterOption.shouldBe(Condition.visible).click();
    }

    @Step("Сортировать по: {sortType}")
    public void sortBy(String sortType) {
        SelenideElement sortButton = $x("//button[contains(text(), 'по популярности')]");
        sortButton.click();
        $x("//button[contains(text(), '" + sortType + "')]").click();
    }
}


