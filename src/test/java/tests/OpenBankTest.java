//package tests;
//
//import base.BaseTest;
//import io.qameta.allure.Step;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import pages.OpenBankPage;
//
//
//
//public class OpenBankTest extends BaseTest {
//
//    private static OpenBankPage openBankPage;
//
//    @BeforeAll
//    public static void set() {
//        openBankPage = new OpenBankPage();
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = { "EUR" })
//    @Step("Тестирование валютного курса для {currency}")
//    void checkCurrencyRate(String currency) {
//        open("https://open.ru");
//        openBankPage.checkCurrencyRate(currency);
//    }
//}
//
//
//
//
