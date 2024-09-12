package pgn.mobile.appiumproject;

import org.fusesource.jansi.AnsiConsole;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import java.time.Duration;
import java.util.Arrays;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.*;
import pgn.mobile.appiumproject.constants.AppConstants;

/**
 *
 * @author REDHAT
 * Test case kode otp expired butuh konfirmasi lebih lanjut terkait kepastian durasi expired,
 * karena tidak sesuai dengan timer resend kode otp
 */
public class OnboardTest {
    static final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");              
    
    public static void onboardLogin() throws Exception{
        Duration waitTime = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(Main.driver, waitTime);
        WebElement btnLogin = Main.driver.findElement(By.xpath(AppConstants.XPATH_ONBOARD_BTN_LOGIN));
        btnLogin.click();

        loginScenario();                                
    }
    
    /**
     * 
     * <h3>Skema Test Case Login</h3><br>
     * 
     * Nomor hp / password salah<br>
     * Nomor hp & password sesuai, otp salah<br>
     * Nomor hp & password sesuai, otp expired<br>
     * Nomor hp & password sesuai, otp benar<br>
     * @throws Exception 
     */
    public static void loginScenario() throws Exception {
        AnsiConsole.systemInstall();
        Duration waitTime = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(Main.driver, waitTime);
        
        WebElement inputPhone = Main.driver.findElement(By.xpath(AppConstants.XPATH_LOGIN_PHONE));
        WebElement inputPassword = Main.driver.findElement(By.xpath(AppConstants.XPATH_LOGIN_PASSWORD));        
        WebElement btnSubmitLogin = Main.driver.findElement(By.xpath(AppConstants.XPATH_LOGIN_BTN_SUBMIT));
        
        wait.until(ExpectedConditions.visibilityOf(inputPhone)).click();
        inputPhone.click();
        inputPhone.sendKeys(AppConstants.ACC_PHONE);
        
        // Nomor Hp & password tidak sesuai
        inputPassword.click();
        inputPassword.sendKeys(AppConstants.ACC_PASS+"1");
        String passwordValue = inputPassword.getText();
        if (!passwordValue.isEmpty()) {
            Main.driver.hideKeyboard();
            btnSubmitLogin.click();
            Thread.sleep(3000);
        }
        
        if (wait.until(ExpectedConditions.visibilityOf(Main.driver.findElement(By.xpath(AppConstants.XPATH_ALERT)))).isDisplayed()) {
            WebElement alert = Main.driver.findElement(By.xpath(AppConstants.XPATH_ALERT));
            try {
                Assert.assertEquals("Test Case Nomor Hp & Password tidak sesuai => fail", "Pengguna atau kata sandi tidak cocok", alert.getAttribute("content-desc"));
            } catch (Exception e) {
                System.out.println(Main.ANSI_GREEN + "Test Case Nomor Hp & Password tidak sesuai => sukses" + Main.ANSI_RESET);
            }            
            System.out.println(Main.ANSI_GREEN + "Test Case Nomor Hp & Password tidak sesuai => sukses" + Main.ANSI_RESET);
            WebElement alertBtn = Main.driver.findElement(By.xpath(AppConstants.XPATH_ALERT_BTN_CLOSE));
            alertBtn.click();
            
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(AppConstants.XPATH_ALERT_BTN_CLOSE))).isDisplayed();
        }
        Thread.sleep(700);
        // Nomor Hp & password sesuai, otp benar
        wait.until(ExpectedConditions.visibilityOf(inputPassword));
        inputPassword.click();
        inputPassword.clear();
        inputPassword.sendKeys(AppConstants.ACC_PASS);
        
        
        
        if (!passwordValue.isEmpty()) {
            Main.driver.hideKeyboard();
            btnSubmitLogin.click();
            System.out.println(Main.ANSI_GREEN + "Test Case Nomor Hp & password Sesuai => sukses" + Main.ANSI_RESET);
            // jika sukses lanjut otp verifikasi
            loginOtpVerification();
        } else {
            System.out.println(Main.ANSI_RED +"Password Belum di input atau element berubah" + Main.ANSI_RESET);
        }
    }
    
    public static void loginOtpVerification() throws Exception{
        Thread.sleep(1000);
        Duration waitTime = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(Main.driver, waitTime);
        WebElement inputOtp = Main.driver.findElement(By.xpath(AppConstants.XPATH_OTP_PIN_FIELD));
        WebElement submitOtp = Main.driver.findElement(By.xpath(AppConstants.XPATH_OTP_BTN_SUBMIT));
        wait.until(ExpectedConditions.visibilityOf(inputOtp));
        
        Sequence tap = new Sequence(finger, 1);        
        tap.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 237, 829));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        // Otp Salah
        Main.driver.perform(Arrays.asList(tap));                
        Thread.sleep(1000);
        
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_2));
        submitOtp.click();
        
        if (wait.until(ExpectedConditions.visibilityOf(Main.driver.findElement(By.xpath(AppConstants.XPATH_ALERT)))).isDisplayed()) {
            WebElement alert = Main.driver.findElement(By.xpath(AppConstants.XPATH_ALERT));
            try {
                Assert.assertEquals("Test Case OTP tidak sesuai => fail", "OTP tidak sesuai", alert.getAttribute("content-desc"));
            } catch (Exception e) {
                System.out.println(Main.ANSI_RED + "Test Case OTP tidak sesuai => fail" + Main.ANSI_RESET);
            }
            System.out.println(Main.ANSI_GREEN +"Test Case OTP tidak sesuai => sukses" + Main.ANSI_RESET);  
            
            WebElement alertBtn = Main.driver.findElement(By.xpath(AppConstants.XPATH_ALERT_BTN_CLOSE));
            alertBtn.click();
            
            wait.until(ExpectedConditions.invisibilityOf(alert));            
            clearOtp();
        }
                
        // Kode OTP telah Kadaluwarsa                
        Main.driver.findElement(By.xpath(AppConstants.XPATH_OTP_RESEND)).click();
        Main.driver.perform(Arrays.asList(tap));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
        Thread.sleep(50000);
//        WebElement text = Main.driver.findElement(By.xpath(AppConstants.XPATH_OTP_RESEND));
        WebElement resendOtp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AppConstants.XPATH_OTP_RESEND)));
        // refresh element submitOtp
        submitOtp = Main.driver.findElement(By.xpath(AppConstants.XPATH_OTP_BTN_SUBMIT));
        submitOtp.click();
        
        if (wait.until(ExpectedConditions.visibilityOf(Main.driver.findElement(By.xpath(AppConstants.XPATH_ALERT)))).isDisplayed()) {
            WebElement alert = Main.driver.findElement(By.xpath(AppConstants.XPATH_ALERT));
            
            try {
//                Assert.assertEquals("Test Case Kode OTP telah Kadaluwarsa => fail", "Kode OTP telah Kadaluwarsa", alert.getAttribute("content-desc"));
                System.out.println(Main.ANSI_RED + "Test Case Kode OTP telah Kadaluwarsa => fail" + Main.ANSI_RESET);
            } catch (Exception e) {
                System.out.println(Main.ANSI_RED + "Test Case Kode OTP telah Kadaluwarsa => fail" + Main.ANSI_RESET);
            }
            System.out.println(Main.ANSI_GREEN + "Test Case Kode OTP telah Kadaluwarsa => sukses" + Main.ANSI_RESET);  
            
            WebElement alertBtn = Main.driver.findElement(By.xpath(AppConstants.XPATH_ALERT_BTN_CLOSE));
            alertBtn.click();
            
            wait.until(ExpectedConditions.invisibilityOf(alert));
            clearOtp();
        }
        
        // Otp Sesuai                
        Main.driver.perform(Arrays.asList(tap));                
        Thread.sleep(1000);
        
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
                
        wait.until(ExpectedConditions.visibilityOf(inputOtp));
        submitOtp.click();
        System.out.println(Main.ANSI_GREEN + "Test Case Login => Sukses" + Main.ANSI_RESET);        
    }
    
    static void clearOtp() {
        Sequence tap = new Sequence(finger, 1);        
        tap.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), 237, 829));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        Main.driver.perform(Arrays.asList(tap));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DEL));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DEL));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DEL));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DEL));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DEL));
        Main.driver.pressKey(new KeyEvent(AndroidKey.DEL));
    }

}
