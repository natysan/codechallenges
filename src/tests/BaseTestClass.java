package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseTestClass {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void startUpBrowser(){
        driver = utils.DriverFactory.getDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void goToHomepage(){
        driver.get("https://www.aliexpress.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser(){
        driver.close();
    }
}
