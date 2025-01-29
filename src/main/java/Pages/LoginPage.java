package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase{

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }
    private final By name = AppiumBy.id("com.androidsample.generalstore:id/nameField");
    private final By male = AppiumBy.id("com.androidsample.generalstore:id/radioMale");
    private final By female = AppiumBy.id("com.androidsample.generalstore:id/radioFemale");
    private final By shopBtn = AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop");
    private final By dropdownOption = AppiumBy.id("android:id/text1");

    private void selectDropdownOption(String option){
        By select = AppiumBy.xpath("//android.widget.TextView[@text='"+option+"']");
        clickBtn(dropdownOption);
        scrollByName(option);
        clickBtn(select);
    }
    public void fillFormValid(String name,String gender,String option){
        sendKeys(this.name,name);
        driver.hideKeyboard();
        if(gender.equalsIgnoreCase("male")){
            clickBtn(male);
        } else if (gender.equalsIgnoreCase("female")) {
            clickBtn(female);
        }
        selectDropdownOption(option);
        clickBtn(shopBtn);
    }

    public String getToastMessage(){
        WebElement toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]"));
        return toastMessage.getDomAttribute("name");
    }
}
