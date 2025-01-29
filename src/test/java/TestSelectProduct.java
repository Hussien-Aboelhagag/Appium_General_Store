import Pages.HomePage;
import Pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSelectProduct extends TestBase{
    @Test(description = "test select one product")
    @Story("select one product")
    @Feature("Select Product")
    @Severity(SeverityLevel.NORMAL)
    @Description("login the app, add one item to cart")
    public void testSelectProduct() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillFormValid("Sara","female","Austria");
        HomePage homePage = new HomePage(driver);
        homePage.scrollToElement("Jordan 6 Rings");
        homePage.clickOnElement("Jordan 6 Rings");
        homePage.clickOnCartBtn();
        Assert.assertTrue(homePage.isProductsInCart());
        Assert.assertEquals(homePage.getProductsInCart(),"Jordan 6 Rings");
    }
    @Test(description = "test select two products")
    @Story("select two products")
    @Feature("Select Product")
    @Severity(SeverityLevel.NORMAL)
    @Description("login the app, add items to cart")
    public void testSelectTwoProducts() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillFormValid("Sara","female","Austria");
        HomePage homePage = new HomePage(driver);
        homePage.scrollToElement("Jordan 6 Rings");
        homePage.clickOnElement("Jordan 6 Rings");
        homePage.scrollToElement("Jordan Lift Off");
        homePage.clickOnElement("Jordan Lift Off");
        homePage.clickOnCartBtn();
        Assert.assertTrue(homePage.isProductsInCart());
        Assert.assertTrue(homePage.isSecondProductsInCart());
        Assert.assertEquals(homePage.getProductsInCart(),"Jordan 6 Rings");
        Assert.assertEquals(homePage.getSecondProductsInCart(),"Jordan Lift Off");
    }

}
