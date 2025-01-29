package Pages.MobileBrowser;

import Pages.PageBase;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TodoLoginPage extends PageBase {
    public TodoLoginPage(AndroidDriver driver) {
        super(driver);
    }
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginBtn = By.id("submit");

    @Step("open browser web application, login with valid data")
    public TodoPage LoginSteps(String email,String password){
        sendKeys(emailInput,email);
        sendKeys(passwordInput,password);
        clickBtn(loginBtn);
        return new TodoPage(driver);
    }
}
