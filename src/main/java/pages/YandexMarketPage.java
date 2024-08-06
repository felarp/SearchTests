package pages;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;
import static constants.Constant.TimeOuts.Xpaths.CATALOG_BUTTON_XPATH;


public class YandexMarketPage extends BasePage {



    public void openCatalog() {
        SelenideElement catalogButton = $x(CATALOG_BUTTON_XPATH);
        catalogButton.scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    @Step("Выбрать категорию: {categoryPath}")
    public void selectCategory(String... categoryPath) {
        for (String category : categoryPath) {
            SelenideElement categoryElement = $x("//span[contains(text(), '" + category + "') and not(contains(@class, '_3z8Gf'))]");
            categoryElement.scrollIntoView(true).shouldBe(Condition.visible).click();
        }
    }
}


