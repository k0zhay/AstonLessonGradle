import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage {
    WebDriver driver;
    WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Кнопка перехода в корзину
    @FindBy(css = ".navbar-pc [href=\"/lk/basket\"]")
    private static WebElement toBasketButton;

    // Блок "Хиты продаж", из которого берем товары
    @FindBy(css = ".goods--1[data-start=\"0\"]")
    private static WebElement hits;

    public void scrollTo(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".goods--1[data-start=\"0\"]")));
        new Actions(driver).scrollToElement(element).perform();
    }

    public void openProdInNewTab(int prodIndex) {
        scrollTo(hits);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".goods--1[data-start=\"0\"] [data-index=\"0\"]")));
        WebElement prod = driver.findElement(By.cssSelector(".goods--1[data-start=\"0\"] " +
                "[data-index=\"" + prodIndex + "\"]"));
        scrollTo(prod);
        Actions newTab = new Actions(driver);

        // Открытие нового окна браузера с выбранным товаром
        newTab.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).click(prod)
                .keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).build().perform();

        // Переключение на окно товара
        List<String> windowHandles = new
                ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
    }

    public void goToBasket() {
        toBasketButton.click();
    }

    /*
     * Следующие методы писал, считая, что смогу получить цену, названия
     * товаров и добавить их в корзину, не выходя с главной страницы.
     * Но по какой-то причине сделать так получается не всегда: кнопка
     * "В корзину" не появляется даже при ручном наведении мыши на товар.
     */
    public int getProdPrice(int prodIndex) {
        scrollTo(hits);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".goods--1[data-start=\"0\"] [data-index=\"0\"]")));
        String strPrice = driver.findElement(
                By.cssSelector(".goods--1[data-start=\"0\"] " +
                "[data-index=\"" + prodIndex + "\"] .price__lower-price"))
                .getText();
        return Integer.parseInt(strPrice.substring(0, strPrice.indexOf(' ')));
    }

    public String getProdName(int prodIndex) {
        scrollTo(hits);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".goods--1[data-start=\"0\"]")));
        return driver.findElement(By.cssSelector(".goods--1[data-start=\"0\"] " +
                "[data-index=\"" + prodIndex + "\"] .product-card__name")).getText();
    }

    public void addToBasket(int prodIndex) {
        scrollTo(hits);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".goods--1[data-start=\"0\"]")));
        driver.findElement(By.cssSelector(".goods--1[data-start=\"0\"] " +
                "[data-index=\"" + prodIndex + "\"] .product-card__add-basket")).click();
    }
}
