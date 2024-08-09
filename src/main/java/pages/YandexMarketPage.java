package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import constants.Constant;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;



public class YandexMarketPage extends BasePage {


    @Step("Открыть каталог")
    public void openCatalog() {
        SelenideElement catalogButton = $x(Constant.TimeOuts.ConstantXpaths.CATALOG_BUTTON_XPATH);
        catalogButton.scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    @Step("Выбрать категории: {categories}")
    public void selectCategory(String... categories) {
        for (int i = 0; i < categories.length; i++) {
            String categoryXpath;
            if (i == 0) {
                categoryXpath = String.format(Constant.TimeOuts.ConstantXpaths.CATALOG_MAIN_CATEGORY_XPATH, categories[i]);
//            } else {
//                categoryXpath = String.format(Constant.TimeOuts.ConstantXpaths.CATALOG_SUB_CATEGORY_XPATH, categories[i]);
//            }
                SelenideElement categoryElement = $x(categoryXpath);
                categoryElement.shouldBe(Condition.visible).click();
            }
        }
    }
}







