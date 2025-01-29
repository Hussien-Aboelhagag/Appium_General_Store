package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends PageBase{
    public CartPage(AndroidDriver driver) {
        super(driver);
    }
    private final By elePrice = By.id("com.androidsample.generalstore:id/productPrice");
    private final By eleTotalPrice = By.id("com.androidsample.generalstore:id/totalAmountLbl");
    private final By checkboxBtn=By.className("android.widget.CheckBox");
    private final By termsBtn=By.id("com.androidsample.generalstore:id/termsButton");
    private final By visitBtn=By.id("com.androidsample.generalstore:id/btnProceed");
    public double totalPriceOfTwoEle(){
        List<WebElement> products = driver.findElements(elePrice);
        double sum=0;
        for (WebElement product : products) {
            String value =  product.getText();
            double total = getFormattedAmount(value);
            sum += total;
        }
        return sum;
    }
    public double getTotalPrice(){
        String totalValue = driver.findElement(eleTotalPrice).getText();
        return getFormattedAmount(totalValue);
    }
    public String CheckTerms(){
        longPress(driver.findElement(termsBtn),3000);
        String termsMsg= driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
        clickBtn(By.id("android:id/button1"));
        return termsMsg;
    }
    public void stepsToPurchase(){
        clickBtn(checkboxBtn);
        clickBtn(visitBtn);
    }
}
