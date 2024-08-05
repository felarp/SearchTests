package pages;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;


public class YandexMarketPage extends BasePage {


    public void openCatalog() {
        SelenideElement catalogButton = $x("//span[text()= 'Каталог']");
        catalogButton.scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    @Step("Выбрать категорию: {categoryPath}")
    public void selectCategory(String... categoryPath) {
        for (String category : categoryPath) {
            SelenideElement categoryElement = $x("//span[contains(text(), '" + category + "')]");
            categoryElement.scrollIntoView(true).shouldBe(Condition.visible).click();
        }
    }
}


