package org.KozhaevSA;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import static org.KozhaevSA.Capabilities.getCapabilities;

public class MobileDriver {
    private static AndroidDriver<AndroidElement> driver;
    public static AndroidDriver<AndroidElement> getDriver() {
        try {
            driver = new AndroidDriver<AndroidElement>(new
                    URL("http://127.0.0.1:4723/wd/hub"), getCapabilities());
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }
}
