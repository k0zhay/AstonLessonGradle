import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;
import java.time.Duration;


public class MtsTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void startClass() {
        System.out.println("\nStart testing\n");
        WebDriverManager.chromedriver().setup();
    }

    @AfterAll
    public static void endClass() {
        System.out.println("End testing\n");
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.mts.by");
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        // Перед запуском каждого теста принимаем и закрываем окно cookie
        driver.findElement(By.cssSelector("button.cookie__close")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test
    @DisplayName("Task 1")
    public void checkName() {
        /*
         * Находим элемент, где должен располагаться искомый текст, и
         * сравниваем его с ожидаемым текстом
         */
        List<WebElement> onlinePaymentBlock = driver.
                findElements(By.cssSelector(".pay__wrapper h2"));
        String expected = "Онлайн пополнение\nбез комиссии";
        String actual = onlinePaymentBlock.stream()
                .map(WebElement::getText).collect(Collectors.joining());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Task 2")
    public void checkLogo() {
        /*
         * Проверяем наличие логотипов платежных систем
         */
        WebElement visaLogo = driver.findElement(
                By.cssSelector("[alt=\"Visa\"]"));
        WebElement verifiedByVisaLogo = driver.findElement(
                By.cssSelector("[alt=\"Verified By Visa\"]"));
        WebElement masterCardLogo = driver.findElement(
                By.cssSelector("[alt=\"MasterCard\"]"));
        WebElement masterCardSecureCodeLogo = driver.findElement(
                By.cssSelector("[alt=\"MasterCard Secure Code\"]"));
        WebElement belkartLogo = driver.findElement(
                By.cssSelector("[alt=\"Белкарт\"]"));
        WebElement mirLogo = driver.findElement(
                By.cssSelector("[alt=\"МИР\"]"));
        Assertions.assertAll(
                () -> Assertions.assertTrue(visaLogo.isDisplayed()),
                () -> Assertions.assertTrue(verifiedByVisaLogo.isDisplayed()),
                () -> Assertions.assertTrue(masterCardLogo.isDisplayed()),
                () -> Assertions.assertTrue(masterCardSecureCodeLogo.isDisplayed()),
                () -> Assertions.assertTrue(belkartLogo.isDisplayed()),
                () -> Assertions.assertTrue(mirLogo.isDisplayed())
        );
    }

    @Test
    @DisplayName("Task 3")
    public void checkLink() {
        /*
         * Нажимаем кнопку "Подробнее о сервисе" и проверяем, что открылась
         * ссылка и подробной информацией об оплате
         */
        WebElement moreDetailsButton = driver.findElement(
                By.cssSelector("div.pay__wrapper a"));
        moreDetailsButton.click();
        wait.until(ExpectedConditions.urlContains("/help/poryadok-oplaty"));
    }

    @Test
    @DisplayName("Task 4")
    public void onlinePaymentTest() {
        /*
         * Последовательно вводим все данные (разные локаторы - для примера)
         * и нажимаем кнопку "Продолжить"
         */
        WebElement phoneNumber = driver.findElement(
                By.id("connection-phone"));
        phoneNumber.sendKeys("297777777");
        WebElement money = driver.findElement(
                By.xpath("//input[@class=\"total_rub\"]"));
        money.sendKeys("1");
        WebElement email = driver.findElement(
                By.cssSelector("input.email"));
        email.sendKeys("kozhaev-sergei@mail.ru");
        WebElement enterButton = driver.findElement(
                By.xpath("//*[@id=\"pay-connection\"]/button"));
        enterButton.click();

        // Проверяем, что открылся фрейм для оплаты
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.bepaid-app__container")));
        WebDriver paymentFrame = driver.switchTo().frame(driver.findElement(
                By.cssSelector("iframe.bepaid-iframe")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("section.payment-page_pays")));
        WebElement paymentElement = paymentFrame.findElement(
                By.cssSelector("section.payment-page_pays"));
        Assertions.assertTrue(paymentElement.isDisplayed());
    }
}
