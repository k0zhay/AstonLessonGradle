import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    private int getPrice(WebElement priceElem) {
        String price = priceElem.getText();
        return Integer.parseInt(price.substring(0, price.indexOf(' ')));
    }

    public int[] getProdPrices() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".list-item__price-new")));
        List<WebElement> listOfPrices = driver.
                findElements(By.cssSelector(".list-item__price-new"));
        int[] prices = new int[listOfPrices.size()];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = getPrice(listOfPrices.get(i));
        }
        return prices;
    }

    public int getSumPrice() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".list-item__price-new")));
        List<WebElement> listOfPrices = driver.
                findElements(By.cssSelector(".list-item__price-new"));
        int sumPrice = 0;
        for (WebElement listOfPrice : listOfPrices) {
            sumPrice += getPrice(listOfPrice);
        }
        return sumPrice;
    }

    public String[] getProdNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".good-info__good-name")));
        List<WebElement> listOfNames = driver.findElements(
                By.cssSelector(".good-info__good-name"));
        String[] names = new String[listOfNames.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = listOfNames.get(i).getText();
        }
        return names;
    }
}
