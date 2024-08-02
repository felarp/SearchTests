//package pages;
//
//import com.codeborne.selenide.SelenideElement;
//import io.qameta.allure.Step;
//import org.junit.jupiter.api.Assertions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import static com.codeborne.selenide.Selenide.$x;
//
//
//public class OpenBankPage extends BasePage {
//    private static final Logger logger = LoggerFactory.getLogger(OpenBankPage.class);
//
//    @Step("Проверка валютного курса для {currencyRate}")
//    public OpenBankPage checkCurrencyRate(String currencyRate) {
//
//        logger.info("Проверка валютного курса для: {}", currencyRate);
//        SelenideElement currency = $x("//div[contains(@class, 'CurrencyExchangeItem_currency-exchange-item-wrapper') and .= '" + currencyRate + "']");
//
//        double buy = Double.parseDouble(currency.$x("./following-sibling::div[1]").getText().replace(",", "."));
//        double sell = Double.parseDouble(currency.$x("./following-sibling::div[2]").getText().replace(",", "."));
//        logger.info("Курс покупки: {}", buy);
//        logger.info("Курс продажи: {}", sell);
//
//        Assertions.assertTrue(sell > buy, () -> "Курс продажи (" + sell+ ") больше  курса покупки (" + buy + ")");
//
//        return this;
//    }
//}
//
