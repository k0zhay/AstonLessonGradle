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
        driver.get("https://www.mts.by");
        driver.manage().deleteAllCookies();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Задание №1")
    public void checkName() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mts.by");

        List<WebElement> onlinePaymentBlock = driver.
                findElements(By.cssSelector(".pay__wrapper h2"));
        String expected = "Онлайн пополнение\nбез комиссии";
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
        WebElement moreDetailLink = driver.findElement(By.cssSelector("div.pay__wrapper a"));
        moreDetailLink.click();
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(
                ExpectedConditions.urlContains(
                        "/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));
    }

    @Test
    @DisplayName("Задание №4")
    @Disabled
    public void onlinePaymentTest() {
        WebElement phoneNumber = driver
                .findElement(By.id("connection-phone"));
        phoneNumber.sendKeys("297777777");
        WebElement money = driver
                .findElement(By.xpath("//input[@class=\"total_rub\"]"));
        money.sendKeys("1");
        WebElement email = driver
                .findElement(By.cssSelector("input.email"));
        email.sendKeys("kozhaev-sergei@mail.ru");
        WebElement enterButton = driver
                .findElement(By.name("Продолжить"));
        enterButton.click();

//        WebDriver paymentFrame = driver.switchTo().frame(
//                driver.findElement(By.cssSelector("iframe.bepaid-iframe")));
//        WebElement paymentElement = paymentFrame.
//                findElement(By.cssSelector("section.payment-page_pays"));
//        Assertions.assertTrue(paymentElement.isDisplayed());
    }
}
