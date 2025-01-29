package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends PageBase{
    public HomePage(AndroidDriver driver) {
        super(driver);
    }
    public void scrollToElement(String element){
        scrollByName(element);
    }
    public void clickOnElement(String element){
        int productCount= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for (int i = 0; i < productCount; i++) {
            String text = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (text.equalsIgnoreCase(element)) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
    }
    public void clickOnCartBtn() {
        clickBtn(By.id("com.androidsample.generalstore:id/appbar_btn_cart"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title")
                ,"text","Cart"));
    }
    public String getProductsInCart(){
        return driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
    }
    public String getSecondProductsInCart(){
        return driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(1).getText();
    }
    public Boolean isProductsInCart(){
        return driver.findElement(By.id("com.androidsample.generalstore:id/productName")).isDisplayed();
    }
    public Boolean isSecondProductsInCart(){
        return driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(1).isDisplayed();
    }
}
