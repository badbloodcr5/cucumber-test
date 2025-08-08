package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>(); 
    private static ThreadLocal<String> browserName = new ThreadLocal<>();

    public static void setBrowser(String browser) 
    {
        browserName.set(browser.toLowerCase());
    }

    // Init WebDirver if it is not available for current thread
    public static WebDriver initDriver() {
    	System.out.println("getDriver function is already called");
        if (driver.get() == null) {
            //String browser = ConfigReader.get("browser").toLowerCase();
        	String browser = browserName.get();
            boolean isHeadless = Boolean.parseBoolean(ConfigReader.get("headless"));

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless) chromeOptions.addArguments("--headless=new");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;

                default:
                    throw new RuntimeException("Trình duyệt không được hỗ trợ: " + browser);
            }
        }
        return driver.get();
    }
    
    // Return current Webdirver of current thread
    public static WebDriver getCurrentDriver()
    {
    	return driver.get();
    }
    
    
    // Close the browser and free the Driver
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            System.out.println("Close browser and release resource");
        }
    }
}