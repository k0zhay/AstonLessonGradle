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
                "Redmi Note 9 Pro");
        // Pixel_3a_API_34_extension_level_7_x86_64
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                "com.miui.calculator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                "cal.CalculatorActivity");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
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
