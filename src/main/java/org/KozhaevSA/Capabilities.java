package org.KozhaevSA;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities {
    public static DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","Emulator");
        capabilities.setCapability("BROWSER_NAME","Android");
        capabilities.setCapability("newCommandTimeout","500");
        return capabilities;
    }
}
