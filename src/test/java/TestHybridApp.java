import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.Hybrid.WebPageGoogle;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHybridApp extends TestBase {
    @Test(description = "test hybrid app")
    @Story("search in browser")
    @Feature("Hybrid App")
    @Severity(SeverityLevel.NORMAL)
    @Description("login the app, add items to cart, purchase, and search in browser")
    public void testHybrid() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillFormValid("Sara", "female", "Austria");
        HomePage homePage = new HomePage(driver);
        homePage.scrollByName("Air Jordan 1 Mid SE");
        homePage.clickOnElement("Air Jordan 1 Mid SE");
        homePage.clickOnCartBtn();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.totalPriceOfTwoEle(),cartPage.getTotalPrice());
        cartPage.stepsToPurchase();
        Thread.sleep(5000);
        driver.context("WEBVIEW_com.androidsample.generalstore");
        WebPageGoogle webPageGoogle = new WebPageGoogle(driver);
        webPageGoogle.search("Jumia Egypt");
        Thread.sleep(3000);
        webPageGoogle.back();
        driver.context("NATIVE_APP");
    }
}