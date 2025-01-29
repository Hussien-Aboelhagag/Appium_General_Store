package Pages.Hybrid;

import Pages.PageBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Set;


public class WebPageGoogle extends PageBase {
    public WebPageGoogle(AndroidDriver driver) {
        super(driver);
    }
    private final By searchBox = By.name("q");

    public void search(String text) {
        driver.findElement(searchBox).sendKeys(text);
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
        driver.get("https://www.jumia.com.eg/ar/");
    }
    public void back(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }
    public void getContextHandles(){
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName);
            if (contextName.contains("WEBVIEW_com.androidsample.generalstore")) {
                driver.context(contextName);
            }
        }
    }
}
