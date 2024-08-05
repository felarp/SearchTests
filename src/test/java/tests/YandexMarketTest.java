package tests;

import base.BaseTest;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pages.CatalogResultPage;
import pages.YandexMarketPage;
import org.testng.annotations.DataProvider;


@Epic("Яндекс Маркет")
@Feature("Поиск и Фильтры")
public class YandexMarketTest extends BaseTest {

    YandexMarketPage yandexMarketPage = new YandexMarketPage();
    CatalogResultPage catalogPageResult = new CatalogResultPage();

    @DataProvider(name = "searchFilters", parallel = true)
    public Object[][] createData() {
        return new Object[][]{
                {"Ноутбуки","Планшеты", "Цена", "30000", "по цене"}
        };
    }

    private void searchAndApplyFilters(String sortType, String filterValue, String filterType, String... categories) {
        open("https://market.yandex.ru/");
        yandexMarketPage.openCatalog();
        yandexMarketPage.selectCategory(categories);
        catalogPageResult.applyFilterRange(filterType, filterValue);
        catalogPageResult.applyFilter("Производитель", "HP");
        catalogPageResult.sortBy(sortType);
    }

    @Test(dataProvider = "searchFilters")
    @Story("Поиск товара с фильтрами и проверка результатов")
    @Description("Поиск товаров в Яндекс Маркет с заданными фильтрами и проверка соответствия результатов")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchWithFilters(String mainCategory, String subCategory, String filterType, String filterValue, String sortType) {
        searchAndApplyFilters(sortType, filterValue, filterType, mainCategory, subCategory);
        catalogPageResult.verifyResultsContainFilter(filterValue);

        String firstProductName = catalogPageResult.copyFirstProductName();


        catalogPageResult.resetFilters();
        searchAndApplyFilters(sortType, filterValue, filterType, mainCategory, subCategory);
        catalogPageResult.verifyResultsContainSearchText(firstProductName);


        catalogPageResult.addFirstLaptopToCart();
    }
}