import Utils.PropertyReader;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    AppiumDriverLocalService service;
    AndroidDriver driver;
    PropertyReader propertyReader= new PropertyReader();
    Properties properties =propertyReader.load("./src/main/resources/Environment.properties");
    @BeforeClass
    public void setupService() throws IOException {
        service =new AppiumServiceBuilder()
                .usingPort(4273)
                .withIPAddress(properties.getProperty("ipAddress"))
                .withArgument(() -> "--use-drivers", properties.getProperty("usingDriver"))
                .build();
        service.start();
    }

    @BeforeMethod
    public void setupDriver(){
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(Platform.ANDROID.name());
        options.setChromedriverExecutable(properties.getProperty("chromeDriverPath"));
        options.setDeviceName(properties.getProperty("deviceName"));
        options.setApp(properties.getProperty("APKPath"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableAutomatedChromedriverDownload", true);
        try {
            driver = new AndroidDriver( new URI
                    ("http://"+properties.getProperty("ipAddress")+":"+properties.getProperty("port"))
                    .toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDownDriver(ITestResult result) throws IOException {
        String testCaseName=result.getMethod().getMethodName();
        File destFile=new File("target"+File.separator+"Screenshot"+ File.separator+testCaseName+".png");
        takeScreenshots(destFile);
        driver.quit();
    }
    @AfterClass
    public void tearDownService() {
            service.stop();
        }
    public void takeScreenshots(File destFile) throws IOException {
        TakesScreenshot takeScreen = (TakesScreenshot) driver;
        File file=takeScreen.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,destFile);
        InputStream inputStream=new FileInputStream(destFile);
        Allure.addAttachment("Screenshot",inputStream);
    }
    public void startActivity(){
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent",properties.getProperty("appPackage")+"/"+properties.getProperty("appActivity")
        ));
    }
}
