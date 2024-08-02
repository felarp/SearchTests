package configuration;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;


public class WebDriverManager {
   static {
       Configuration.browserPosition = "0x0";
       Configuration.browser = "chrome";
       Configuration.browserVersion = "126.0";
       Configuration.timeout = 20 * 1000;
       Configuration.pageLoadTimeout = 30 * 1000;
       Configuration.browserSize = "1920x1080";
   }

   public static void openBrowser (String url) {
       Selenide.open(url);
   }

   public static void closeBrowser() {
       Selenide.closeWebDriver();
   }
}
