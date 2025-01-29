package MobileBrowser;

import Pages.MobileBrowser.MobileConfiq.APIResources;
import Pages.MobileBrowser.Utilts.CookieUtils;
import Utils.PropertyReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Allure;
import io.restassured.http.Cookie;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Browser;
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
import java.util.List;
import java.util.Properties;

public class MobileBrowserBase {
    AppiumDriverLocalService service;
    AndroidDriver driver;
    PropertyReader propertyReader= new PropertyReader();
    Properties properties =propertyReader.load("./src/main/resources/Environment.properties");
    @BeforeClass
    public void setupService() throws IOException {
        service =new AppiumServiceBuilder()
                .usingPort(Integer.parseInt(properties.getProperty("port")))
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
        options.withBrowserName(Browser.CHROME.browserName());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableAutomatedChromedriverDownload", true);
        try {
            driver = new AndroidDriver( new URI
                    ("http://"+properties.getProperty("ipAddress")+":"+properties.getProperty("port"))
                    .toURL(), options);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        driver.get(APIResources.LOGIN_PAGE);
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
    public void injectCookieIntoSelenium(List<Cookie> restAssuredCookie){
        List<org.openqa.selenium.Cookie> seleniumCookies= CookieUtils.convertRestAssuredCookieIntoSelenium(restAssuredCookie);
        for (org.openqa.selenium.Cookie cookie:seleniumCookies){
            driver.manage().addCookie(cookie);
        }
        driver.navigate().refresh();
    }
    public void takeScreenshots(File destFile) throws IOException {
        TakesScreenshot takeScreen = (TakesScreenshot) driver;
        File file=takeScreen.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,destFile);
        InputStream inputStream=new FileInputStream(destFile);
        Allure.addAttachment("Screenshot",inputStream);
    }
}
