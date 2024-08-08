package constants;

public class Constant {
    public static class TimeOuts {
        public static final int IMPLICIT_WAIT = 4;
        public static final int EXPLICIT_WAIT = 10;


        public static class Urls {
            public static final String YANDEX_MARKET_URL = "https://market.yandex.ru/";
        }
        public static class Xpaths {
            public static final String CATALOG_BUTTON_XPATH = "//span[contains(text(), 'Каталог')]";

           public static final String FILTER_OPTION_XPATH = "//li[descendant::span[text()='%s'] and descendant::label[contains(.,'%s')]]//input";
            public static final String FILTER_RANGE_INPUT_XPATH = "//div[descendant::span[text()='%s']]//input[contains(@name, 'range')]";;
            public static final String CATALOG_MAIN_CATEGORY_XPATH = "//span[contains(text(), '%s') and not(contains(@class, '_3z8Gf'))]";
            public static final String CATALOG_SUB_CATEGORY_XPATH = "//a[contains(text(), '%s') and not(contains (@class, '_2re3U ltlqD _2TBT0'))]";
        }
    }
}



