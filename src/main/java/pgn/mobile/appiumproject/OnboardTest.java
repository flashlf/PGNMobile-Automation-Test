/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pgn.mobile.appiumproject;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.*;
import pgn.mobile.appiumproject.constants.AppConstants;

/**
 *
 * @author REDHAT
 * TODO : 
 * - [ ] Nomor hp / password salah
 * - [ ] Nomor hp & password sesuai, otp salah
 * - [ ] Nomor hp & password sesuai, otp expired
 * - [x] Nomor hp & password sesuai, otp benar
 * 
 */
public class OnboardTest {
    static final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");              
    
    public static void onboardLogin() throws Exception{
        Duration waitTime = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(Main.driver, waitTime);
        WebElement btnLogin = Main.driver.findElement(By.xpath(AppConstants.XPATH_ONBOARD_BTN_LOGIN));
        btnLogin.click();

        WebElement inputPhone = Main.driver.findElement(By.xpath(AppConstants.XPATH_LOGIN_PHONE));
        wait.until(ExpectedConditions.visibilityOf(inputPhone)).click();
        inputPhone.click();
        inputPhone.sendKeys(AppConstants.ACC_PHONE);
        
        WebElement inputPassword = Main.driver.findElement(By.xpath(AppConstants.XPATH_LOGIN_PASSWORD));        
        wait.until(ExpectedConditions.visibilityOf(inputPassword));
        inputPassword.click();
        inputPassword.sendKeys(AppConstants.ACC_PASS);
        
        String passwordValue = inputPassword.getText();
        
        if (!passwordValue.isEmpty()) {
            WebElement btnSubmitLogin = Main.driver.findElement(By.xpath(AppConstants.XPATH_LOGIN_BTN_SUBMIT));
            Main.driver.hideKeyboard();
            btnSubmitLogin.click();
            // jika sukses lanjut otp verifikasi
            loginOtpVerification();
        } else {
            System.out.println("Password Belum di input atau element berubah");
        }                
        
    }
    
    public static void loginOtpVerification() throws Exception{
        Thread.sleep(1000);
        Duration waitTime = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(Main.driver, waitTime);
        WebElement inputOtp = Main.driver.findElement(By.xpath(AppConstants.XPATH_OTP_PIN_FIELD));
        
        wait.until(ExpectedConditions.visibilityOf(inputOtp));
        
        Sequence tap = new Sequence(finger, 1);
        
        tap.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 237, 829));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        Main.driver.perform(Arrays.asList(tap));        
        
        Thread.sleep(1000);
        
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        
        WebElement submitOtp = Main.driver.findElement(By.xpath(AppConstants.XPATH_OTP_BTN_SUBMIT));
        wait.until(ExpectedConditions.visibilityOf(inputOtp));
        submitOtp.click();
    }

}
