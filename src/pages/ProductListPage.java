package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

import static pages.Commons.getCommons;
import static utils.DriverFactory.getDriver;

public class ProductListPage{

    WebDriver driver = getDriver();

    @FindBy(css = "span.next-input.next-large>input")
    public WebElement goToPageInput;

    @FindBy(css = "span.jump-btn")
    WebElement goButton;

    @FindBy(css = "div.list-pagination")
    public WebElement navigationBar;

    Commons co = getCommons();

    private ProductListPage(){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static ProductListPage getProductListPage(){
        return new ProductListPage();
    }

    public static By listNewUserModal(){
        return By.cssSelector("div.newuser-container");
    }

    public static By listNewUserModalClose(){
        return By.cssSelector("a.next-dialog-close");
    }

    public ProductListPage goToResultsPage(String pageNumber){
        goToPageInput.sendKeys(pageNumber);
        goButton.click();
        return this;
    }

    public ProductListPage navigateToPage(String page){
        if (navigationBar.isDisplayed()){
            co.scrollUntilVisible(navigationBar);
            goToPageInput.sendKeys(page);
            goButton.click();
        }
        return this;
    }

    public ProductListPage selectSpecificResult(String resultNumber){
        int result = Integer.valueOf(resultNumber) - 1;
        String position = Integer.toString(result);
        String selectorForResult = ".list-items div[class*='product-card'][product-index='" +position + "']>div[class*='product-info'] div.item-title-wrap>a";
        WebElement ele = driver.findElement(By.cssSelector(selectorForResult));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()",ele);
        return this;
    }

    public void switchToProductDetail() {
        String parentWindowHandle = driver.getWindowHandle();
        String newHandle = "";
        Set<String> windows = driver.getWindowHandles();
        for(String handle:windows)
        {
            if (!handle.equals(parentWindowHandle)) {
                newHandle = handle;
            }
        }
        driver.switchTo().window(newHandle);
    }

}
