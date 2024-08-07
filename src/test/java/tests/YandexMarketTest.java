package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.CatalogResultPage;
import pages.YandexMarketPage;
import org.testng.annotations.DataProvider;
import java.util.Arrays;
import java.util.List;

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

    @Test(dataProvider = "searchFilters")
    @Story("Поиск товара с фильтрами и проверка результатов")
    @Description("Поиск товаров в Яндекс Маркет с заданными фильтрами и проверка соответствия результатов")
    public void testSearchWithFilters(String mainCategory, String subCategory, String filterType, String filterValue, String sortType) {
        open(YANDEX_MARKET_URL);
        yandexMarketPage.openCatalog();
        yandexMarketPage.selectCategory(mainCategory, subCategory);

        catalogPageResult.applyFilter(filterType, filterValue);
        catalogPageResult.sortBy(sortType);

        catalogPageResult.verifyResultsContainFilters(Arrays.asList(filterValue));
        String firstProductName = catalogPageResult.copyFirstProductName();
        catalogPageResult.resetFilters();
        catalogPageResult.applyFilter(filterType, filterValue);
        catalogPageResult.sortBy(sortType);

        List<String> searchTexts = Arrays.asList(firstProductName);
        catalogPageResult.verifyResultsContainSearchTexts(searchTexts);
        catalogPageResult.addFirstLaptopToCart();
    }
}



