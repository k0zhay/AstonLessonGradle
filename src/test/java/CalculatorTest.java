import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CalculatorTest {
    AndroidDriver driver;
    DesiredCapabilities capabilities;

    @BeforeEach
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
                              "Pixel 7 Pro API 34");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                              "src/test/resources/Calculator_8.4.1.apk");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                              "cal.CalculatorActivity");
        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);

        try {
            driver = new AndroidDriver(new
                     URL("http://127.0.0.0:4723/wd/hub"),capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test() {
        try {
            driver = new AndroidDriver(new
                    URL("http://127.0.0.0:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }
}
