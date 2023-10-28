import static org.KozhaevSA.Apps.Calculator.CalculatorLauncher.clickAt;
import static org.KozhaevSA.Apps.Calculator.CalculatorLauncher.getResult;
import org.KozhaevSA.Apps.Calculator.CalculatorLauncher;
import org.KozhaevSA.MobileDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Owner("Kozhaev")
@Epic("Calculating Valid Numbers")
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
    @DisplayName("Addition Test")
    @Description("Adding Two Numbers")
    @Severity(SeverityLevel.CRITICAL)
    public void plusTest() {
        clickAt("com.google.android.calculator:id/digit_1");
        clickAt("com.google.android.calculator:id/op_add");
        clickAt("com.google.android.calculator:id/digit_2");
        clickAt("com.google.android.calculator:id/eq");
        Assertions.assertEquals("3", getResult());
    }

    @Test
    @DisplayName("Difference Test")
    @Description("Difference Of Two Numbers")
    @Severity(SeverityLevel.CRITICAL)
    public void minusTest() {
        clickAt("com.google.android.calculator:id/digit_2");
        clickAt("com.google.android.calculator:id/op_sub");
        clickAt("com.google.android.calculator:id/digit_1");
        clickAt("com.google.android.calculator:id/eq");
        Assertions.assertEquals("1", getResult());
    }

    @Test
    @DisplayName("Multiplication Test")
    @Description("Multiplication Of Two Numbers")
    @Severity(SeverityLevel.CRITICAL)
    public void multiplyTest() {
        clickAt("com.google.android.calculator:id/digit_2");
        clickAt("com.google.android.calculator:id/op_mul");
        clickAt("com.google.android.calculator:id/digit_1");
        clickAt("com.google.android.calculator:id/eq");
        Assertions.assertEquals("2", getResult());
    }

    @Test
    @DisplayName("Division Test")
    @Description("Dividing One Number By Another")
    @Severity(SeverityLevel.CRITICAL)
    public void divideTest() {
        clickAt("com.google.android.calculator:id/digit_2");
        clickAt("com.google.android.calculator:id/op_div");
        clickAt("com.google.android.calculator:id/digit_1");
        clickAt("com.google.android.calculator:id/eq");
        Assertions.assertEquals("2", getResult());
    }
}
