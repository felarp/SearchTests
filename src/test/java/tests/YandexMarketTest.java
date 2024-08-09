package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.CatalogResultPage;
import pages.YandexMarketPage;
import org.testng.annotations.DataProvider;

import static constants.Constant.TimeOuts.ConstantXpaths.YANDEX_MARKET_URL;



public class YandexMarketTest extends BaseTest {
    YandexMarketPage yandexMarketPage = new YandexMarketPage();
    CatalogResultPage catalogPageResult = new CatalogResultPage();

    @DataProvider(name = "searchFilters", parallel = true)
    public Object[][] createData() {
        return new Object[][]{
                {"Электроника", "Смартфоны", "Цены по убыванию", "Производитель: Samsung", "цвет:черный, белый", "бренд:Apple, Samsung"}
        };
    }

    @Test(dataProvider = "searchFilters")
    @Story("Поиск товаров с фильтрами и проверкой результатов")
    public void testSearchWithFilters(String mainCategory, String subCategory, String sortType, String... filterValues) {
        open(YANDEX_MARKET_URL);

        yandexMarketPage.openCatalog();
        yandexMarketPage.selectCategory(mainCategory, subCategory);

        for (String filterValue : filterValues) {
            String[] filterParts = filterValue.split(":");
            String filterType = filterParts[0];
            String filter = filterParts[1];
            catalogPageResult.applyFilter(filterType, filter.split(", "));
        }

        catalogPageResult.applyPriceRange("40000", "80000");
        catalogPageResult.sortBy(sortType);

        for (String filterValue : filterValues) {
            String[] filterParts = filterValue.split(":");
            catalogPageResult.verifyResultsContainFilters(filterParts[1].split(", "));
        }
    }
}





