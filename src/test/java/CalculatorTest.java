import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.KozhaevSA.Apps.Calculator.CalculatorInstallation;
import org.KozhaevSA.MobileDriver;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    public static AndroidDriver<AndroidElement> driver;
    static final String RESOURCE_PATH = "C:\\Users\\kozha\\Desktop";
    //    static final String NAME_OF_APK = "Calculator_13.1.28.apk";
//    static final String NAME_OF_PACKAGE = "com.miui.calculator";
//    static final String CALCULATOR_ACTIVITY = ".cal.CalculatorActivity";
    static final String NAME_OF_APK = "Calculator_8.4.1.apk";
    static final String NAME_OF_PACKAGE = "com.google.android.calculator";
    static final String CALCULATOR_ACTIVITY = "com.android.calculator2.Calculator";

    @Test
    public void setUp() {
        driver = MobileDriver.getDriver();
        CalculatorInstallation.isCalculatorInstalled();
        CalculatorInstallation.calculatorInstallation();


//        CalculatorElements calc = new CalculatorElements();
//        calc.caclulator();
//        calc.one();
        //plus(); two(); equal();


    }

    @Test
    public void addition_isCorrect() {

    }
}
