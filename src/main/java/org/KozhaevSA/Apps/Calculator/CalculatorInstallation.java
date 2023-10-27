package org.KozhaevSA.Apps.Calculator;

import java.io.IOException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.KozhaevSA.MobileDriver;

public class CalculatorInstallation {
    private static final AndroidDriver<AndroidElement> driver = MobileDriver.getDriver();
    static final String RESOURCE_PATH = "C:\\Users\\kozha\\Desktop";
    static final String NAME_OF_APK = "Calculator_8.4.1.apk";
    static final String NAME_OF_PACKAGE = "com.google.android.calculator";
    static final String CALCULATOR_ACTIVITY = "com.android.calculator2.Calculator";

    public static void isCalculatorInstalled() {
        if (driver.isAppInstalled(NAME_OF_PACKAGE)) {
            try {
                Runtime.getRuntime().exec("adb uninstall " + NAME_OF_PACKAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void calculatorInstallation() {
        try {
            Runtime.getRuntime().exec("adb install " + RESOURCE_PATH + "\\" + NAME_OF_APK);
            Thread.sleep(20000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void calculatorStart() {
        try {
            Runtime.getRuntime().exec("adb shell am start -n "
                    + NAME_OF_PACKAGE + "/" + CALCULATOR_ACTIVITY);
            Thread.sleep(20000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
