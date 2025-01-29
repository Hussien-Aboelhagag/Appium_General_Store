import Pages.LoginPage;
import Utils.TestDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestJumiaLoginPage extends TestBase{
    @Test(dataProvider = "testData", dataProviderClass = TestDataProvider.class)
    @Story("Login to the app")
    @Feature("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("login the app")
    public void testLogin(String name, String gender, String country) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillFormValid(name,gender,country);
    }
    @Test
    public void testToast() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillFormValid("", "female", "Australia");
        Assert.assertTrue(loginPage.getToastMessage().contains("Please enter your name"));
    }
}
