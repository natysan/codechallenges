package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.Commons;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.ProductListPage;

import static pages.Commons.getCommons;
import static pages.HomePage.*;
import static pages.ProductDetailPage.*;
import static pages.ProductListPage.*;

public class HomePageTests extends BaseTestClass{

    WebDriver driver = utils.DriverFactory.getDriver();
    HomePage home = getHomePage();
    Commons common = getCommons();
    ProductListPage productList = getProductListPage();
    ProductDetailPage productDetail = getProductDetailPage();

    @Test
    public void verifyQuantityForSale() {
        common
                .verifyModalIsDisplayedAndClose(homeNewUserModal(), homeNewUserModalClose());
        home
                .hoverItem("Phones & Telecommunications")
                .selectItem("iPhones");
        common
                .verifyModalIsDisplayedAndClose(listNewUserModal(), listNewUserModalClose());
        productList
                .navigateToPage("2")
                .selectSpecificResult("2")
                .switchToProductDetail();
        common
                .verifyModalIsDisplayedAndClose(detailNewUserModal(), detailNewUserModalClose());
        productDetail
                .scrollDown()
                .checkAvailableQuantityIsGreaterThan("1")
                .switchToProductList();
    }
}
