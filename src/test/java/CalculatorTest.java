import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.KozhaevSA.Apps.Calculator.CalculatorElements;
import org.KozhaevSA.Apps.Calculator.CalculatorInstallation;
import org.KozhaevSA.MobileDriver;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    public static AndroidDriver<AndroidElement> driver;

    @Test
    public void setUp() {
        driver = MobileDriver.getDriver();
        CalculatorInstallation.isCalculatorInstalled();
        CalculatorInstallation.calculatorInstallation();
        CalculatorInstallation.calculatorStart();

        CalculatorElements.one();

    }

    @Test
    public void addition_isCorrect() {

    }
}
