package pgn.mobile.appiumproject;

import io.appium.java_client.flutter.android.FlutterAndroidDriver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pgn.mobile.appiumproject.constants.AppConstants;

/**
 * @author M.I.A
 * Automation UI Testing for PGNMobile using Appium 
 * Version 1.0
 * 11 September 2024
 */

public class Main {
    
    static FlutterAndroidDriver driver;
    static DesiredCapabilities dCap;
    static WebDriverWait wait; 

    public static void main(String[] args) {
        try {
            initiateDevice();
            OnboardTest.onboardLogin();
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void initiateDevice() throws Exception {
        dCap = new DesiredCapabilities();
        dCap.setCapability("appium:deviceName", AppConstants.CAP_DEVICE);
        dCap.setCapability("appium:udid", AppConstants.CAP_UDID);
        dCap.setCapability("platformName", AppConstants.CAP_PLATFORM_NAME);
        dCap.setCapability("appium:platformVersion", AppConstants.CAP_PLATFORM_VERSION);
        dCap.setCapability("appium:appPackage", AppConstants.CAP_APP_PACKAGE);
        dCap.setCapability("appium:appActivity", AppConstants.CAP_APP_ACTIVITY);
        dCap.setCapability("appium:automationName", AppConstants.CAP_AUTOMATION_NAME);
        // dCap.setCapability("appium:noReset", true); // Clear Cache ?
        
        driver = new FlutterAndroidDriver(new URL(AppConstants.HOST_URL), dCap);
        System.out.println("Testing Appium Started");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Duration waitTime = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        
        // Permission Apps
        WebElement permission1 = driver.findElement(By.xpath(AppConstants.ANDROID_LOCATION_PERMISSION));
        wait.until(ExpectedConditions.visibilityOf(permission1));
        permission1.click();
        
        WebElement permission2 = driver.findElement(By.xpath(AppConstants.ANDROID_LOCATION_PERMISSION));
        wait.until(ExpectedConditions.visibilityOf(permission2));
        permission2.click();
        
        WebElement permission3 = driver.findElement(By.xpath(AppConstants.ANDROID_NOTIFICATION_PERMISSION));
        wait.until(ExpectedConditions.visibilityOf(permission3));
        permission3.click();
    }
}