package tests;

import base.BaseTest;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pages.CatalogPageResult;
import pages.YandexMarketPage;
import org.testng.annotations.DataProvider;


@Epic("Яндекс Маркет")
@Feature("Поиск и Фильтры")
public class YandexMarketTest extends BaseTest {

    YandexMarketPage catalogPage = new YandexMarketPage();
    CatalogPageResult catalogPageResult = new CatalogPageResult();

    @DataProvider(name = "searchFilters", parallel = true)
    public Object[][] createData() {
        return new Object[][]{
                {"Электроника", "Смартфоны", "Цена", "50000", "по цене"}
        };
    }
    private void searchAndApplyFilters(String sortType, String filterValue, String filterType, String... categories) {
        open("https://market.yandex.ru/");
        catalogPage.openCatalog();
        catalogPage.selectCategory(categories);
        catalogPage.applyFilterRange(filterType, filterValue);
        catalogPage.sortBy(sortType);
    }

    @Test(dataProvider = "searchFilters")
    @Story("Поиск товара с фильтрами и проверка результатов")
    @Description("Поиск товаров в Яндекс Маркет с заданными фильтрами и проверка соответствия результатов")

    public void testSearchWithFilters(String mainCategory, String subCategory, String filterType, String filterValue, String sortType) {

        searchAndApplyFilters(sortType, filterValue, filterType, mainCategory, subCategory);

        catalogPageResult.verifyResultsContainFilter(filterValue);

        String firstProductName = catalogPageResult.copyFirstProductName();

        searchAndApplyFilters(sortType, filterValue, filterType, mainCategory, subCategory);
        catalogPageResult.verifyResultsContainSearchText(firstProductName);
    }
}



