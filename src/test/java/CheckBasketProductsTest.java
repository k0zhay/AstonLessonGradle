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

//    @AfterAll
//    public static void endClass() {
//        driver.manage().deleteAllCookies();
//        driver.quit();
//        System.out.println("End testing\n");
//    }

    @Test
    @DisplayName("Check Bucket Products")
    public void checkBucketProductsTest() throws InterruptedException {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        ProductPage productPage = PageFactory
                .initElements(driver, ProductPage.class);

        /*
         * Можно при необходимости задать количество товаров в пределах 1-7 для
         * выбранного блока (в нашем случае код для блока "Хиты продаж").
         */
        int goodsAmount = 7;
        int[] prices = new int[goodsAmount];
        String[] names = new String[goodsAmount];
        int totalPrice = 0;
        for (int prodIndex = 0; prodIndex < goodsAmount; prodIndex++) {
            mainPage.openProdInNewTab(prodIndex);
            prices[prodIndex] = productPage.getPrice();
            totalPrice += prices[prodIndex];
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
        int totalPriceInBasket = basketPage.getTotalPrice();
        int finalTotalPrice = totalPrice;
        Assertions.assertAll(
                () -> Assertions.assertArrayEquals(prices, pricesInBasket),
                () -> Assertions.assertArrayEquals(names, namesInBasket),
                () -> Assertions.assertEquals(finalTotalPrice, totalPriceInBasket)
        );
    }
}
