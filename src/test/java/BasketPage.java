import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasketPage {
    WebDriver driver;
    WebDriverWait wait;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//*[contains(@data-link, \"totalPaymentFeeAmount\")]")
    private static WebElement totalPayment;

    private int getPrice(WebElement priceElem) {
        wait.until(ExpectedConditions.visibilityOf(priceElem));

        // Записываем ценник до последнего пробела в строке (перед знаком рубля)
        String price = priceElem.getText()
                .substring(0, priceElem.getText().lastIndexOf(' '));

        // Не забываем, что у 4-значных ценников стоит лишний пробел
        return Integer.parseInt(price.replace(" ", ""));
    }

    public int[] getProdPrices() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".list-item__price-new")));
        List<WebElement> listOfPrices = driver.
                findElements(By.cssSelector(".list-item__price-new"));
        int[] prices = new int[listOfPrices.size()];

        // Записываем с конца корзины, где находится первый добавленный товар
        for (int i = 0; i < prices.length; i++) {
            prices[i] = getPrice(listOfPrices.get(prices.length - 1 - i));
        }
        return prices;
    }

    public int getTotalPrice() {
        wait.until(ExpectedConditions.visibilityOf(totalPayment));
        String price = totalPayment.getText()
                .substring(0, totalPayment.getText().lastIndexOf(' '));
        return Integer.parseInt(price.replace(" ", ""));
    }

    public String[] getProdNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".good-info__good-name")));
        List<WebElement> listOfNames = driver.findElements(
                By.cssSelector(".good-info__good-name"));
        String[] names = new String[listOfNames.size()];

        // Записываем с конца корзины, где находится первый добавленный товар
        for (int i = 0; i < names.length; i++) {
            names[i] = listOfNames.get(names.length - 1 - i).getText();
        }
        return names;
    }
}
