package Pages.MobileBrowser.Utilts;

import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {
    public static List<Cookie> convertRestAssuredCookieIntoSelenium(List<io.restassured.http.Cookie> restAssuredCookie){
        List<org.openqa.selenium.Cookie>seleniumCookies=new ArrayList<>();
        for (io.restassured.http.Cookie cookie:restAssuredCookie){
            org.openqa.selenium.Cookie seleniumCookie=new org.openqa.selenium.Cookie(cookie.getName(), cookie.getValue());
            seleniumCookies.add(seleniumCookie);
        }
        return seleniumCookies;
    }
}
