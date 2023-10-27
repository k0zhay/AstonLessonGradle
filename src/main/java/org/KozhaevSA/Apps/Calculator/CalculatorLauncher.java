/*
 * В данном классе описаны все методы, необходимые для установки, удаления и
 * запуска приложения
 */
package org.KozhaevSA.Apps.Calculator;

import java.io.IOException;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorLauncher {
    private static AndroidDriver<AndroidElement> driver;
    /*
     * Для корректной работы, необходимо указать путь до Calculator_8.4.1.apk,
     * находящемся в папке src/test/resources
     */
    static final String RESOURCE_PATH = "C:\\Users\\kozha\\AndroidStudioProjects\\AstonLessonGradle\\src\\test\\resources";
    static final String NAME_OF_APK = "Calculator_8.4.1.apk";
    static final String NAME_OF_PACKAGE = "com.google.android.calculator";

    public CalculatorLauncher(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public  void isCalculatorInstalled() {
        if (driver.isAppInstalled(NAME_OF_PACKAGE)) {
            try {
                Runtime.getRuntime().exec("adb uninstall " + NAME_OF_PACKAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  void calculatorInstallation() {
        try {
            Runtime.getRuntime().exec("adb install " + RESOURCE_PATH + "\\" + NAME_OF_APK);
            Thread.sleep(20000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public  void calculatorStart() {
        WebElement swipe = driver.findElement(
                MobileBy.AccessibilityId("Search"));
        int startX = swipe.getLocation().getX();
        int startY  = swipe.getLocation().getY();
        int endY = driver.manage().window().getSize().getWidth() - 1;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startX,startY))
                .moveTo(PointOption.point(startX, endY)).release().perform();
        AndroidElement calcApp = driver.findElement(
                MobileBy.AccessibilityId("Calculator"));
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOf(calcApp)).click();

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
