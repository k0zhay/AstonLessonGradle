import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage {
    WebDriver driver;
    WebDriverWait wait;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int getProdPrice(int prodIndex) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".goods--1[data-start=\"0\"] [data-index=\"0\"]")));
        String strPrice = driver.findElement(
                        By.cssSelector(".goods--1[data-start=\"0\"] " +
                                "[data-index=\"" + prodIndex + "\"] .price__lower-price"))
                .getText();
        return Integer.parseInt(strPrice.substring(0, strPrice.indexOf(' ')));
    }

    public String getProdName(int prodIndex) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".goods--1[data-start=\"0\"]")));
        return driver.findElement(By.cssSelector(".goods--1[data-start=\"0\"] " +
                "[data-index=\"" + prodIndex + "\"] .product-card__name")).getText();
    }
}
