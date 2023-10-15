import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class PaymentFrameTest {
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
    @DisplayName("Online Payment Frame Test")
    public void paymentFrameTest() {
        // Определяем вводимые данные
        String phoneNumber = "297777777";
        String money = "1.99";
        String email = "kozhaev-sergei@mail.ru";

        // Вводим данные в соответствующие поля и нажимаем продолжить
        WebElement phoneNumberInput = driver.findElement(
                By.id("connection-phone"));
        phoneNumberInput.sendKeys(phoneNumber);
        WebElement moneyInput = driver.findElement(
                By.xpath("//input[@class=\"total_rub\"]"));
        moneyInput.sendKeys(money);
        WebElement emailInput = driver.findElement(
                By.cssSelector("input.email"));
        emailInput.sendKeys(email);
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

        // Находим элементы с записанными ранее данными
        WebElement phoneNumberCheck = driver.findElement(
                By.cssSelector("p.header__payment-info"));
        WebElement moneyCheck = driver.findElement(
                By.cssSelector("p.header__payment-amount"));
        WebElement paymentButton = driver.findElement(
                By.cssSelector("button.ng-star-inserted"));

        // Надписи для незаполненных полей реквизитов карты
        String cardNumber = "Номер карты";
        String validityPeriod = "Срок действия";
        String cvc = "CVC";
        String cardholder = "Имя держателя (как на карте)";

        // Находим тексты в полях для ввода реквизитов карты
        String cardNumberInputField = driver.findElement(
                By.cssSelector("label.ng-tns-c46-1")).getText();
        String validityPeriodInputField = driver.findElement(
                By.cssSelector("label.ng-tns-c46-4")).getText();
        String cvcInputField = driver.findElement(
                By.cssSelector("label.ng-tns-c46-5")).getText();
        String cardholderInputField = driver.findElement(
                By.cssSelector("label.ng-tns-c46-3")).getText();
        assertAll(
                () -> assertTrue(phoneNumberCheck.getText().contains(phoneNumber)),
                () -> assertTrue(moneyCheck.getText().contains(money)),
                () -> assertTrue(paymentButton.getText().contains(money)),
                () -> assertEquals(cardNumberInputField, cardNumber),
                () -> assertEquals(validityPeriodInputField, validityPeriod),
                () -> assertEquals(cvcInputField, cvc),
                () -> assertEquals(cardholderInputField, cardholder)
        );
    }
}
