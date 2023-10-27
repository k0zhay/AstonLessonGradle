/*
 * Я полностью отдаю себе отчет в том, что это худшее моё ДЗ, и здесь по сути
 * не используются знания, накопленные в прошлых уроках, которые тогда успешно
 * (более-менее) применялись. К сожалению, у меня нет ни времени, ни
 * возможности привести эту работу к приемлемому виду
 * Прошу прощения за такое решение "в лоб"
 */
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.KozhaevSA.Apps.Calculator.CalculatorLauncher;
import org.KozhaevSA.MobileDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CalculatorTest {
    public static AndroidDriver<AndroidElement> driver;

    @BeforeAll
    public static void beforeAll() {
        driver = MobileDriver.getDriver();
        CalculatorLauncher launcher = new CalculatorLauncher(driver);
        launcher.isCalculatorInstalled();
        launcher.calculatorInstallation();
        launcher.calculatorStart();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @AfterEach
    public void setUp() {
        driver.findElement(By.id("com.google.android.calculator:id/clr")).click();
    }

    /*
     * Тестирование, конечно, неполное, но я и так ни в какие сроки не
     * уложился, поэтому приходится обходиться основными операциями
     */
    @Test
    public void plusTest() {
        clickAt("com.google.android.calculator:id/digit_1");
        clickAt("com.google.android.calculator:id/op_add");
        clickAt("com.google.android.calculator:id/digit_2");
        clickAt("com.google.android.calculator:id/eq");
        Assertions.assertEquals("3", getResult());
    }

    @Test
    public void minusTest() {
        clickAt("com.google.android.calculator:id/digit_2");
        clickAt("com.google.android.calculator:id/op_sub");
        clickAt("com.google.android.calculator:id/digit_1");
        clickAt("com.google.android.calculator:id/eq");
        Assertions.assertEquals("1", getResult());
    }

    @Test
    public void multiplyTest() {
        clickAt("com.google.android.calculator:id/digit_2");
        clickAt("com.google.android.calculator:id/op_mul");
        clickAt("com.google.android.calculator:id/digit_1");
        clickAt("com.google.android.calculator:id/eq");
        Assertions.assertEquals("2", getResult());
    }

    @Test
    public void divideTest() {
        clickAt("com.google.android.calculator:id/digit_2");
        clickAt("com.google.android.calculator:id/op_div");
        clickAt("com.google.android.calculator:id/digit_1");
        clickAt("com.google.android.calculator:id/eq");
        Assertions.assertEquals("2", getResult());
    }

    public void clickAt(String id) {
        driver.findElement(By.id(id)).click();
    }

    public String getResult() {
        return driver.findElement(
                By.id("com.google.android.calculator:id/result_final"))
                .getAttribute("text");
    }
}
