package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pages.Commons.getCommons;
import static utils.DriverFactory.getDriver;

public class ProductDetailPage {

    WebDriver driver = getDriver();
    Commons co = getCommons();

    @FindBy(css = "div.product-quantity-info>div.product-quantity-tip>span")
    WebElement quantityLabel;

    private ProductDetailPage(){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static ProductDetailPage getProductDetailPage (){
        return new ProductDetailPage();
    }

    public static By detailNewUserModal(){
        return By.cssSelector("div.next-dialog");
    }

    public static By detailNewUserModalClose(){
        return By.cssSelector("a.next-dialog-close");
    }

    public ProductDetailPage checkAvailableQuantityIsGreaterThan(String expectedQuantity) {
        Pattern p = Pattern.compile("[0-9]+");
        Integer quantity = 0;
        Integer expected = Integer.valueOf(expectedQuantity);
        Matcher match = p.matcher(quantityLabel.getText());
        if (match.find()) {
            String q = match.group();
            quantity = Integer.valueOf(q);
        }
        Assert.assertTrue(quantity >= expected);
        return this;
    }

    public ProductDetailPage switchToProductList() {
        String currentWindowHandle = driver.getWindowHandle();
        String switchToWindow = "";
        Set<String> handlesSet = driver.getWindowHandles();
        for(String handle:handlesSet)
        {
            if (!handle.equals(currentWindowHandle)) {
                switchToWindow = handle;

            }else{
                driver.close();
            }
        }
        driver.switchTo().window(switchToWindow);
        return this;
    }

    public ProductDetailPage scrollDown(){
        if (driver.findElement(By.cssSelector("span.product-price-value")).isDisplayed()){
            Point point = driver.findElement(By.cssSelector("span.product-price-value")).getLocation();
            Integer ybajar = point.y;
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0," + ybajar + ")");

        }
        return this;
    }

}
