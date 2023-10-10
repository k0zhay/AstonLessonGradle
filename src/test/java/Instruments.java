import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Instruments {
    public static void webElems() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mts.by");

        // Задание №4
        List<WebElement> onlinePaymentBlock = driver.
                findElements(By.cssSelector(".pay__wrapper h2"));

        // Задание №4
        // Для примера работы, поля определены тремя разными локаторами
        WebElement phoneNumber =
                driver.findElement(By.id("connection-phone"));
        WebElement money =
                driver.findElement(By.xpath("//input[@class=\"total_rub\"]"));
        WebElement email =
                driver.findElement(By.cssSelector("input.email"));
        WebElement enter =
                driver.findElement(By.cssSelector("button.button__default"));
    }
}
