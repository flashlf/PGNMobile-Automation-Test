package pgn.mobile.appiumproject;

import org.fusesource.jansi.AnsiConsole;
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

    // ANSI escape codes for colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
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
        dCap.setCapability("appium:newCommandTimeout", AppConstants.CAP_COMMAND_TIMEOUT);
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
        
        wait.until(ExpectedConditions.invisibilityOf(permission1));
        WebElement permission2 = driver.findElement(By.xpath(AppConstants.ANDROID_LOCATION_PERMISSION));
        permission2.click();
        
        wait.until(ExpectedConditions.invisibilityOf(permission2));
        WebElement permission3 = driver.findElement(By.xpath(AppConstants.ANDROID_NOTIFICATION_PERMISSION));
        permission3.click();
    }
}