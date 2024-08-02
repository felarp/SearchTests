package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;


public class BasePage {
   protected static final Logger logger = Logger.getLogger(BasePage.class.getName());
    protected WebDriver driver;

    public String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Получен заголовок страницы: " + title);
        return title;
    }

   @Step("Логирование: {message}")
    protected static void log(String message) {
       logger.info(message);
    }
}

