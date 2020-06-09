package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static utils.DriverFactory.getDriver;
import static utils.DriverFactory.getWait;

public class HomePage {

    WebDriver driver = getDriver();
    WebDriverWait wait = getWait();

    @FindBy(css = "a[data-spm-anchor-id='a2g0o.home.16005.1']")
    WebElement categoriesSelector;

    @FindBy(css = "div.categories-list-box")
    WebElement categoriesListBox;

    private HomePage(){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static HomePage getHomePage(){
        return new HomePage();
    }

    public HomePage hoverItem(String category) {
        Actions action = new Actions(driver);
        WebElement hi = driver.findElement(By.linkText(category));
        action.moveToElement(hi).build().perform();
        return this;
    }

    public HomePage selectItem(String itemSubcategory) {
        Actions action = new Actions(driver);
        WebElement si = driver.findElement(By.linkText(itemSubcategory));
        action.moveToElement(si).click().build().perform();
        return this;
    }

    public static By homeNewUserModal(){
        return By.cssSelector("div.newuser-container");
    }

    public static By homeNewUserModalClose(){
        return By.cssSelector("a.close-layer");
    }

}
