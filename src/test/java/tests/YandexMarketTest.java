package tests;

import base.BaseTest;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.YandexMarketPage;


public class YandexMarketTest extends BaseTest {

    private static YandexMarketPage marketPage;

    @BeforeAll
    public static void setUp() {
        marketPage = new YandexMarketPage();
    }

    @Test
    public void testSearchWithFilter() {
        open("https://market.yandex.ru/");
        marketPage.selectCategory("ноутбуки");
        marketPage.applyFilter("Цена до", "60000");
        marketPage.sortBy("по цене: сначала дорогие");
    }
}





