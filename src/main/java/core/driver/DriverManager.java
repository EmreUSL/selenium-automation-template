package core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {

     private static final ThreadLocal<WebDriver> driver =  new ThreadLocal<>();

     private DriverManager() {}

     public static WebDriver initDriver(BrowserType browserType) {
         if (driver.get() == null) {
             switch (browserType) {
                 case CHROME:
                     WebDriverManager.chromedriver().setup();
                     driver.set(new ChromeDriver());
                     break;
                 case FIREFOX:
                     WebDriverManager.firefoxdriver().setup();
                     driver.set(new FirefoxDriver());
                     break;
                 case EDGE:
                     WebDriverManager.edgedriver().setup();
                     driver.set(new EdgeDriver());
                     break;
                 default:
                     throw new RuntimeException("Unknown browser type: " + browserType);
             }
         }
         return getDriver();
     }

     public static WebDriver getDriver() {
         if (driver.get() == null) {
             throw  new RuntimeException("Driver is not initialized");
         }
         return driver.get();
     }

     public static void quitDriver() {
         if (driver.get() != null) {
             driver.get().quit();
             driver.remove();
         }
     }
}
