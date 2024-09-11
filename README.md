# PGNMobile-Automation-Test
Automation UI Testing untuk aplikasi PGN Mobile

### Pre-Requisite
```conf
-- Software
1. Latest Node.js (author using v22.8.0)
2. Latest NPM (author using 10.8.2)
3. Appium v2.11.3 (Driver xcuitest, uiautomator2)
4. ADB (Android Debugging Bridge)
5. Java JDK Latest
```

### Appium Installation
1. Install appium on node using Node Packet Manager ``npm install -g appium``.
2. Install required driver in appium ``appium driver install uiautomator2`` for **ANDROID** or ``appium driver xcuitest`` for **IOS**.
3. After all those step you are ready to use appium testing by run command ``appium`` in terminal

### How to Start Automation Test
1. After you successfully install apium you can go start using these repo for automation testing using java, i prefer IDE Netbeans for easy use, or you can use another java IDE
2. Connect device via usb onto your PC
3. Activate USB Debugging option
4. Just Run the main class java in your IDE


*Note: Don't Forget to Download Declared Dependencies from Maven in these project, i strongly suggest to download javadoc and source too