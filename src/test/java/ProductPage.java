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

    // Элемент, содержащий текст с названием
    @FindBy(css = ".product-page__header h1")
    private static WebElement nameElem;

    // Элемент, содержащий текст со стоимостью
    @FindBy(css = ".product-page__aside .price-block__final-price")
    private static WebElement priceElem;

    // Кнопка добавления в корзину
    @FindBy(xpath = "//*[@class=\"product-page__aside\"]" +
                    "//*[text()=\"Добавить в корзину\"]")
    private static WebElement addToBasketButton;

    // Первый попавшийся размер товара (при наличии выбора размера)
    @FindBy(css = ".sizes-list__item:nth-child(1)")
    private static WebElement sizeElem;

    public int getPrice() {
        wait.until(ExpectedConditions.visibilityOf(priceElem));

        // Записываем ценник до последнего пробела в строке (перед знаком рубля)
        String price = priceElem.getText()
                .substring(0, priceElem.getText().lastIndexOf(' '));

        // Не забываем, что у 4-значных ценников стоит лишний пробел
        return Integer.parseInt(price.replace(" ", ""));
    }

    public String getProdName() {
        wait.until(ExpectedConditions.visibilityOf(nameElem));
        return nameElem.getText();
    }

    // Если у товара нужно выбрать размер, выбираем первый попавшийся
    public void chooseSize() {
        try {
            sizeElem.click();
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
