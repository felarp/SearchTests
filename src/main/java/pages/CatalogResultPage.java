package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.*;
import static constants.Constant.TimeOuts.Xpaths.FILTER_OPTION_XPATH;
import static constants.Constant.TimeOuts.Xpaths.FILTER_RANGE_INPUT_XPATH;
import org.testng.Assert;


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

    @Step("Скролл и поиск элемента: {element}")
    public void scrollAndFindElement(SelenideElement element, int maxScrollAttempts) {
        boolean elementFound = false;

        for (int i = 0; i < maxScrollAttempts; i++) {
            if (element.exists()) {
                elementFound = true;
                break;
            }
            actions().sendKeys(Keys.PAGE_DOWN).perform();
            sleep(500);
        }
        Assert.assertTrue(elementFound, "Элемент не был найден!");
    }
}


