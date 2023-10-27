package org.KozhaevSA.Apps.Calculator;

import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CalculatorElements {
    private static AndroidDriver<AndroidElement> driver;

    @FindBy(id = "com.google.android.calculator:id/main_calculator")
    private static AndroidElement calculatorMain;



    @FindBy(id = "com.google.android.calculator:id/digit_0")
    private AndroidElement zeroBtn;

//    @FindBy(xpath = "\"//android.widget.ImageButton[@content-desc=\"1\"]")
//    private static AndroidElement oneBtn;


    //private AndroidElement oneBtn = driver.findElement(By.id("com.google.android.calculator:id/digit_1"));
    @FindBy(xpath = "com.google.android.calculator:id/digit_1")
    private static AndroidElement oneBtn;

    @FindBy(id = "com.google.android.calculator:id/digit_2")
    private static AndroidElement twoBtn;

    @FindBy(id = "com.google.android.calculator:id/digit_3")
    private static AndroidElement threeBtn;

    @FindBy(id = "com.google.android.calculator:id/digit_4")
    private static AndroidElement fourBtn;

    @FindBy(id = "com.google.android.calculator:id/digit_5")
    private static AndroidElement fiveBtn;

    @FindBy(id = "com.google.android.calculator:id/digit_6")
    private static AndroidElement sixBtn;

    @FindBy(id = "com.google.android.calculator:id/digit_7")
    private static AndroidElement sevenBtn;

    @FindBy(id = "com.google.android.calculator:id/digit_8")
    private static AndroidElement eightBtn;

    @FindBy(id = "com.google.android.calculator:id/digit_9")
    private static AndroidElement nineBtn;

    @FindBy(id = "com.google.android.calculator:id/op_add")
    private static AndroidElement plusBtn;

    @FindBy(id = "com.google.android.calculator:id/op_sub")
    private static AndroidElement minusBtn;

    @FindBy(id = "com.google.android.calculator:id/op_mul")
    private static AndroidElement multiplyBtn;

    @FindBy(id = "com.google.android.calculator:id/op_div")
    private static AndroidElement divideBtn;

    @FindBy(id = "com.google.android.calculator:id/eq")
    private static AndroidElement equalBtn;

    @FindBy(id = "com.miui.calculator:id/expression")
    private static AndroidElement expressionBtn;

    @FindBy(id = "com.google.android.calculator:id/result_final")
    private static AndroidElement resultBtn;

    @FindBy(id = "com.miui.calculator:id/btn_c_s")
    private static AndroidElement clearBtn;

//    public WebElement waitVisibility(AndroidDriver<AndroidElement> driver, AndroidElement element) {
//        return new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
//    }

    public static void caclulator() {
        //waitVisibility(driver, caclulatorMain);
        calculatorMain.click();
    }

    public static void one() {
        //waitVisibility(driver, oneBtn);
        oneBtn.click();
    }

//    public static void two() {
//        waitVisibility(driver, twoBtn);
//        twoBtn.click();
//    }
//
//    public static void plus() {
//        waitVisibility(driver, plusBtn);
//        plusBtn.click();
//    }
//
//    public static void equal() {
//        waitVisibility(driver, equalBtn);
//        equalBtn.click();
//    }
}
