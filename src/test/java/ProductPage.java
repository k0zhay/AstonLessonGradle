import org.openqa.selenium.NoSuchElementException;
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

    // Первый попавшийся размер товара (при наличии выбора размера)
    @FindBy(css = ".sizes-list__item:nth-child(1)")
    private static WebElement prodSize;

    public int getProdPrice() {
        wait.until(ExpectedConditions.visibilityOf(prodPrice));
        return Integer.parseInt(prodPrice.getText()
                .substring(0, prodPrice.getText().indexOf(' ')));
    }

    public String getProdName() {
        wait.until(ExpectedConditions.visibilityOf(prodName));
        return prodName.getText();
    }

    // Если у товара можно выбрать размер, выбираем первый попавшийся
    public void chooseSize() {
        try {
            prodSize.click();
        } catch (NoSuchElementException ignored) {
        }
    }

    public void addToBasket() throws InterruptedException {
        addToBasketButton.click();
        Thread.sleep(1000);
    }

    public void closeProdTab() {
        driver.close();
        List<String> windowHandles = new
                ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(0));
    }
}
