package base;

import configuration.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;



public class BaseTest {
protected static WebDriverManager driver = new WebDriverManager();

    @Step("Открытие URL: {url}")
    public static void open(String url) {
        System.out.println("Открытие URL: " + url);
       driver.openBrowser(url);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Закрытие браузера...");
        driver.closeBrowser();
    }
}





