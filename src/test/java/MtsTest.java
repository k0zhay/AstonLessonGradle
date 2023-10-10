import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
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
        driver.manage().window().maximize();
        driver.get("https://www.mts.by");
        driver.manage().deleteAllCookies();
        driver.findElement(By.cssSelector("button.cookie__close")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test
    @DisplayName("Задание №1")
    public void checkName() throws UnsupportedEncodingException {
        List<WebElement> onlinePaymentBlock = driver.
                findElements(By.cssSelector(".pay__wrapper h2"));

        /*
         * Вместо ожидаемого текста в строке expected записывался набор непонятных
         * символов, поэтому эксклюзивно для неё я перевел строку в байты, а потом
         * со сменной кодировки - обратно в строку (примечание: если менять
         * кодировку глобально, то ломался текст в @DisplayName)
         */
        String expected = "Онлайн пополнение\nбез комиссии";
        byte[] array = expected.getBytes("windows-1251");
        expected = new String(array, "UTF-8");
        String actual = onlinePaymentBlock.stream()
                .map(WebElement::getText).collect(Collectors.joining());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Задание №2")
    @Disabled
    public void checkLogo() {

    }

    @Test
    @DisplayName("Задание №3")
    public void checkLink() {
        WebElement moreDetailsButton = driver.findElement(By.cssSelector("div.pay__wrapper a"));
        moreDetailsButton.click();
        wait.until(ExpectedConditions.urlContains("/poryadok-oplaty"));
    }

    @Test
    @DisplayName("Задание №4")
    public void onlinePaymentTest() {
        /*
         * Последовательно введем все данные (разные локаторы - для примера)
         * и нажмем кнопку "Продолжить"
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

        // Проверим, что открылся фрейм для оплаты
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
