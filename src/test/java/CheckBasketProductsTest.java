import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckBasketProductsTest {
    private static WebDriver driver;

    @BeforeAll
    public static void startClass() {
        System.out.println("\nStart testing\n");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.wildberries.ru/");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void endClass() {
        driver.manage().deleteAllCookies();
        driver.quit();
        System.out.println("End testing\n");
    }

    @Test
    @DisplayName("Check Bucket Products")
    public void checkBucketProductsTest() throws InterruptedException {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        ProductPage productPage = PageFactory
                .initElements(driver, ProductPage.class);
        int[] prices = new int[3];
        String[] names = new String[3];
        int sumPrice = 0;
        for (int prodIndex = 0; prodIndex < 3; prodIndex++) {
            mainPage.openProdInNewTab(prodIndex);
            prices[prodIndex] = productPage.getProdPrice();
            sumPrice += prices[prodIndex];
            names[prodIndex] = productPage.getProdName();
            productPage.chooseSize();
            productPage.addToBasket();
            productPage.closeProdTab();
        }
        mainPage.goToBasket();

        /*
         * При открытии корзины ценники товаров не сразу становятся
         * такими, какими должны быть, а, видимо, очень быстро прокручиваются
         * от нуля до актуальной суммы, даже если вся страница загрузилась.
         * Поэтому, чтобы программа не "выхватила" ещё не установившуюся сумму,
         * добавлено ожидание в одну секунду.
         */
        Thread.sleep(1000);
        BasketPage basketPage = PageFactory
                .initElements(driver, BasketPage.class);
        int[] pricesInBasket = basketPage.getProdPrices();
        String[] namesInBasket = basketPage.getProdNames();
        int actualSumPrice = basketPage.getSumPrice();

    }
}
