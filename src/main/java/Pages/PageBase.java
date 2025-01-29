package Pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
    public AndroidDriver driver;
    public PageBase(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickBtn(By element) {
        driver.findElement(element).click();
    }
    public void sendKeys(By element, String text) {
        driver.findElement(element).sendKeys(text);
    }
    public void scrollByName(String value){
        driver.findElement(AppiumBy.androidUIAutomator(String.format
                ("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""+value+"\"))")));
    }
    public double getFormattedAmount(String amount){
        return Double.parseDouble(amount.substring(1));
    }
    public void longPress(WebElement element, int duration){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),"duration",duration));
    }

}
