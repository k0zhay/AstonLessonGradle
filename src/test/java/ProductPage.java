import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(css = ".product-page__header h1")
    private static WebElement prodName;

    @FindBy(css = ".product-page__aside .price-block__final-price")
    private static WebElement prodPrice;

    @FindBy(xpath = "//*[@class=\"product-page__aside\"]" +
                    "//*[text()=\"Добавить в корзину\"]")
    private static WebElement addToBasketButton;

    public int getProdPrice(int prodIndex) {
        wait.until(ExpectedConditions.visibilityOf(prodPrice));
        return Integer.parseInt(prodPrice.getText()
                .substring(0, prodPrice.getText().indexOf(' ')));
    }

    public String getProdName(int prodIndex) {
        wait.until(ExpectedConditions.visibilityOf(prodName));
        return prodName.getText();
    }

    public void addToBasket() {
        // wait.until(ExpectedConditions.visibilityOf(addToBasketButton));
        addToBasketButton.click();
    }

    public void closeProdTab() {
        driver.close();
        List<String> windowHandles = new
                ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(0));
    }
}
