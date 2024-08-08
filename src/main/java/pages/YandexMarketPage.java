package pages;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import constants.Constant;
import io.qameta.allure.Step;



import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class YandexMarketPage extends BasePage {

    @Step("Открыть каталог")
    public void openCatalog() {
        SelenideElement catalogButton = $x(Constant.TimeOuts.Xpaths.CATALOG_BUTTON_XPATH);
        logger.info("Нахожу кнопку каталога по XPath: {}");
        catalogButton.scrollIntoView(true).shouldBe(Condition.visible).click();
    }

    @Step("Выбрать категорию: {mainCategory} и подкатегорию: {subCategory}")
    public void selectCategory(String mainCategory, String subCategory) {

        String mainCategoryXpath = String.format(Constant.TimeOuts.Xpaths.CATALOG_MAIN_CATEGORY_XPATH, mainCategory);
        SelenideElement mainCategoryElement = $x(mainCategoryXpath);
        logger.info("Нахожу основную категорию по XPath: {}");
        sleep(1000);
        mainCategoryElement.scrollIntoView(true).shouldBe(Condition.visible).click();
        mainCategoryElement.shouldNotBe(Condition.interactable);

        logger.info("Клик по основной категории выполнен.");

        String subCategoryXpath = String.format(Constant.TimeOuts.Xpaths.CATALOG_SUB_CATEGORY_XPATH, subCategory);
        SelenideElement subCategoryElement = $x(subCategoryXpath);
        logger.info("Нахожу подкатегорию по XPath: {}");

        logger.info("Ожидание видимости подкатегории перед кликом.");
        sleep(1000);

        subCategoryElement.scrollIntoView(true).shouldBe(Condition.visible).click();
        logger.info("Клик по подкатегории выполнен.");
    }
}





