import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCart extends TestBase {
    @Test(description = "test app cart")
    @Story("login to tha app, add items to cart")
    @Feature("Adding items to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("validate that user can login with valid data, and add product to cart and purchase")
    public void testCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillFormValid("Sara", "female", "Austria");
        HomePage homePage = new HomePage(driver);
        homePage.scrollToElement("Jordan 6 Rings");
        homePage.clickOnElement("Jordan 6 Rings");
        homePage.scrollByName("Air Jordan 1 Mid SE");
        homePage.clickOnElement("Air Jordan 1 Mid SE");
        homePage.clickOnCartBtn();
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.totalPriceOfTwoEle(),cartPage.getTotalPrice());
        Assert.assertEquals(cartPage.CheckTerms(),"Terms Of Conditions");
        cartPage.stepsToPurchase();
    }
}