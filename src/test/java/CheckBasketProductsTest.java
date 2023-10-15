import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    public void checkBucketProductsTest() {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        ProductPage productPage = PageFactory
                .initElements(driver, ProductPage.class);
        int[] prices = new int[3];
        String[] names = new String[3];
        for (int prodIndex = 0; prodIndex < 3; prodIndex++) {
            mainPage.openProdInNewTab(prodIndex);
            prices[prodIndex] = productPage.getProdPrice(prodIndex);
            names[prodIndex] = productPage.getProdName(prodIndex);
            productPage.addToBasket();
            productPage.closeProdTab();
        }
    }


//    @Test
//    @DisplayName("Check Bucket Products")
//    public void checkBucketProductsTest() {
//        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
//        int[] prices = new int[3];
//        String[] names = new String[3];
//        for (int prodIndex = 0; prodIndex < 3; prodIndex++) {
//            prices[prodIndex] = mainPage.getProdPrice(prodIndex);
//            names[prodIndex] = mainPage.getProdName(prodIndex);
//            mainPage.addBasket(prodIndex);
//        }
//        mainPage.goToBasket();
//    }



}
