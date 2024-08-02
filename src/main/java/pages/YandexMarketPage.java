package pages;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;



public class YandexMarketPage extends BasePage {

    public void openCatalog() {
        $x("//noindex[1]/div/div/button").click();
    }

    public void selectCategory(String categoryName) {
        openCatalog();
        $x("//ul[contains(@data-tid, 'sections')]//a/span[contains(text(),'" + categoryName + "')]").click();
    }

    public void applyFilter(String filterType, String filterValue) {
        SelenideElement filterElement = $x("//fieldset//legend[contains(text(), '" + filterType + "')]/following-sibling::div//input[@qa-mark='range-to']")
                .shouldBe(Condition.visible);
        filterElement.clear();
        filterElement.setValue(filterValue).pressEnter();
    }

    public void sortBy(String sortType) {
        SelenideElement sortElement = $x("//button[contains(text(), 'по популярности')]").shouldBe(Condition.visible);
        sortElement.click();
        SelenideElement sortOption = $x("//button[contains(text(), '" + sortType + "')]").shouldBe(Condition.visible);
        sortOption.click();
    }


}

