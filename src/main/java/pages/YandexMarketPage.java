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
            SelenideElement categoryElement = $x("//span[contains(@class, '_3W4t0') and text()='" + category + "']");
            categoryElement.shouldBe(Condition.visible).click();
        }
    }
}



