package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseDriver {

    public static WebDriver driver;

    public static WebDriver getDriver(String driverType) {
        if(driver == null){
            driver = initializeDriver(driverType);
        }
        return driver;
    }

    private static WebDriver initializeDriver(String driverType) {
        String driverPath = (Utils.readProperty("config.properties","webdriver"));
        if(driverType.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", driverPath);
            final ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        }else if (driverType.equalsIgnoreCase("FireFox")) {
            //TODO: Add firefox's code here
        }else {
            //TODO: Add IE's code here
        }
        return driver;
    }
}