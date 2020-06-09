package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverFactory() { }

    public static WebDriver getDriver() {
        //using chrome just for simplicity since it's only one test
        if (driver == null) {
            //System.setProperty("webdriver.chrome.driver","if_needed_path_to_chromedriver.exe");
            driver = new ChromeDriver(); }
        return driver;
    }

    public static WebDriverWait getWait(){
        if(wait == null) { wait = new WebDriverWait(getDriver(),20); }
        return wait;
    }
}
