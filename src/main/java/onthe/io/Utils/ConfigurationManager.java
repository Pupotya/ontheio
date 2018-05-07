package onthe.io.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class ConfigurationManager {

    private static  ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if(driver.get()==null) setDriver();
        return driver.get();
    }

    public static void setDriver(){
        ConfigurationManager.driver.set(configureDriver(init()));
    }

    private static WebDriver init() {
        String browserName = System.getProperty("browser") != null ?System.getProperty("browser"): "chrome";

        ClassLoader classLoader = ConfigurationManager.class.getClassLoader();
        String browserDriverFilePath = new File(classLoader.getResource("./drivers/" + browserName + "driver").getFile()).getAbsolutePath();

        switch (browserName) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", browserDriverFilePath);
                return new FirefoxDriver();
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", browserDriverFilePath);
                return new ChromeDriver();
            }
            case "edge":
                return new EdgeDriver();
        }
        return new ChromeDriver();
    }

    private static WebDriver configureDriver(WebDriver driver) {
        return driver;
    }
}
