package tests;

import base.BaseTest;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CatalogResultPage;
import pages.YandexMarketPage;
import org.testng.annotations.DataProvider;

import static com.codeborne.selenide.Selenide.*;
import static constants.Constant.TimeOuts.Urls.YANDEX_MARKET_URL;


@Epic("Яндекс Маркет")
@Feature("Поиск и Фильтры")
public class YandexMarketTest extends BaseTest {

    YandexMarketPage yandexMarketPage = new YandexMarketPage();
    CatalogResultPage catalogPageResult = new CatalogResultPage();

    @DataProvider(name = "searchFilters", parallel = true)
    public Object[][] createData() {
        return new Object[][]{
                {"Электроника", "Ноутбуки", "Производитель", "HP", "по цене"}
        };
    }

    private void searchAndApplyFilters(String sortType, String filterValue, String filterType, String... categories) {
        open(YANDEX_MARKET_URL);
        yandexMarketPage.openCatalog();
        yandexMarketPage.selectCategory(categories);

        catalogPageResult.applyFilter(filterType, filterValue);
        catalogPageResult.sortBy(sortType);
    }

    private boolean scrollAndFindElement(SelenideElement element, int maxScrollAttempts) {
        for (int i = 0; i < maxScrollAttempts; i++) {
            if (element.exists()) {
                return true;
            }
            actions().sendKeys(Keys.PAGE_DOWN).perform();
            sleep(500);
        }
        return false;
    }

    @Test(dataProvider = "searchFilters")
    @Story("Поиск товара с фильтрами и проверка результатов")
    @Description("Поиск товаров в Яндекс Маркет с заданными фильтрами и проверка соответствия результатов")

    public void testSearchWithFilters(String mainCategory, String subCategory, String filterType, String filterValue, String sortType) {
        searchAndApplyFilters(sortType, filterValue, filterType, mainCategory, subCategory);


        SelenideElement desiredElement = catalogPageResult.getElementContainingText(filterValue);
        boolean elementFound = scrollAndFindElement(desiredElement, 10);


        Assert.assertTrue(elementFound, "Element with filter value '" + filterValue + "' was not found!");

        catalogPageResult.verifyResultsContainFilter(filterValue);
        String firstProductName = catalogPageResult.copyFirstProductName();
        catalogPageResult.resetFilters();
        searchAndApplyFilters(sortType, filterValue, filterType, mainCategory, subCategory);
        catalogPageResult.verifyResultsContainSearchText(firstProductName);
        catalogPageResult.addFirstLaptopToCart();
    }
}

