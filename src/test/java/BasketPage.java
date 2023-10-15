import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage {
    WebDriver driver;
    WebDriverWait wait;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(css = ".product-page__header h1")
    private static WebElement prodName;
}
