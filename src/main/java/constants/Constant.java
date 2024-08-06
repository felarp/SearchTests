package constants;

public class Constant {
    public static class TimeOuts {
        public static final int IMPLICIT_WAIT = 4;
        public static final int EXPLICIT_WAIT = 10;


        public static class Urls {
            public static final String YANDEX_MARKET_URL = "https://market.yandex.ru/";
        }
        public static class Xpaths {
            public static final String CATALOG_BUTTON_XPATH = "//span[text()='Каталог']";

            public static final String FILTER_OPTION_XPATH = "//li[descendant::span[text()='%s'] and descendant::label[contains(.,'%s')]]//input";
            public static String FILTER_RANGE_INPUT_XPATH = "//div[descendant::span[text()='%s']]//input[contains(@name, 'range')]";
        }
    }
}



