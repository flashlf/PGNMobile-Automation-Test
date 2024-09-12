package pgn.mobile.appiumproject.constants;

/**
 *
 * @author REDHAT
 * List Constant yang dibutuhkan pada apps
 */
public final class AppConstants {
    
    private AppConstants() {}
    
    /** GENERAL CONFIG **/
    public static final String ACC_PHONE = "";
    public static final String ACC_PASS = "";
    public static final String HOST_URL = "http://127.0.0.1:4723/";
    public static final String CAP_DEVICE = ""; // your device name
    public static final String CAP_UDID = ""; // Unique Id in adb device command
    public static final String CAP_PLATFORM_NAME = "Android"; // Android | IOS
    public static final String CAP_PLATFORM_VERSION = "14";
    public static final String CAP_APP_PACKAGE = "com.pgn.customer";
    public static final String CAP_APP_ACTIVITY = "com.pgn.customer.MainActivity";
    public static final String CAP_AUTOMATION_NAME = "UiAutomator2"; // UiAutomator2 = Android | Xcuitest = IOS
    public static final int CAP_COMMAND_TIMEOUT = 800;

    /** NOTIF APPS **/
    public static final String XPATH_ALERT = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]";
    public static final String XPATH_ALERT_BTN_CLOSE = "//android.widget.Button";

    /** ANDROID PERMISSION **/
    public static final String ANDROID_LOCATION_PERMISSION = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]";
    public static final String ANDROID_CAMERA_PERMISSION = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]";
    public static final String ANDROID_NOTIFICATION_PERMISSION = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]";
    
    /**  LIST XPATH **/
    // Onboard Screen
    public static final String XPATH_ONBOARD_BTN_LOGIN = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]";
    public static final String XPATH_ONBOARD_BTN_REGISTER = "";
    
    // Login Screen
    public static final String XPATH_LOGIN_PHONE = "//android.widget.ScrollView/android.widget.EditText[1]";
    public static final String XPATH_LOGIN_PASSWORD = "//android.widget.ScrollView/android.widget.EditText[2]";
    public static final String XPATH_LOGIN_BTN_SUBMIT = "//android.widget.ScrollView/android.view.View[6]";
    
    // Otp Login Screen
    public static final String XPATH_OTP_BACK_BUTTON = "//android.widget.Button";
    public static final String XPATH_OTP_PIN_FIELD = "//android.widget.EditText";
    public static final String XPATH_OTP_RESEND = "//android.widget.ScrollView/android.view.View[5]";
    public static final String XPATH_OTP_BTN_SUBMIT = "//android.widget.ScrollView/android.view.View[7]";
    
    // Dashboard Screen
    public static final String XPATH_TOUR_STEP1_SKIP = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[5]";
    public static final String XPATH_TOUR_STEP1_CONTINUE = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[6]";
    
    / //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[5]
    // 618,1122 Step1 Skip Pointer
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[6]
    // 973, 1117 Step1 Continue
    
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[6]
    // 262, 1590 Step2 back to Step1
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[7]
    // 597, 1590 Step2 Skip
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[8]
    // 973, 1590 Step2 Continue
    
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[10]
    // 268, 1801 Step3 back to Step2
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[11]
    // 618, 1801 Step3 Skip
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[12]
    // 973, 1801 Step3 Continue
    
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[8]
    // 262, 1729 Step4 back to step3
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[9]
    // 612, 1729 Step4 Skip
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[10]
    // 973, 1729 Step4 Continue
    
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[6]
    // 257, 1966 Step5 Back to Step4
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[7]
    // 602, 1966 Step5 Skip
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[8]
    // 962, 1966 Step5 Continue
    
    
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[6]
    // 299, 1966 Step6 Back to Step5
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[7]
    // 597, 1966 Step6 Skip
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[8]
    // 957, 1966 Step6 Continue
    
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[6]
    // 283, 1806 Step7 Back to Step6
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[7]
    // 612, 1806 Step7 Skip
    // //android.widget.FrameLayout[@resource-id="android:id/content"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[8]
    // 978, 1806 Step7 Continue
    
}