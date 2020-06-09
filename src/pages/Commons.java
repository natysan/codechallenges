package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static utils.DriverFactory.getDriver;

public class Commons {

    WebDriver driver = getDriver();

    private Commons(){ }

    public static Commons getCommons(){
        return new Commons();
    }

    public void scrollUntilVisible(WebElement element){
        Point point = element.getLocation();
        WebElement stickyHeader = driver.findElement(By.cssSelector(".header-wrap"));
        Integer removeStickyZone = stickyHeader.getSize().height;
        Integer xPoint = point.getY() - removeStickyZone;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + xPoint + ")");
    }

    public Commons verifyModalIsDisplayedAndClose(By elementModal, By elementClose){
        if (!driver.findElements(elementModal).isEmpty()){
            driver.findElement(elementClose).click();
        }
        return this;
    }

}
