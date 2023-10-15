import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnlinePaymentFieldsTest {
    private static WebDriver driver;

    // Следующие две строки есть во всех вариантах, поэтому выпишем их отдельно
    String money = "Сумма";
    String email = "E-mail для отправки чека";

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
        driver.manage().window().maximize();
        // Перед запуском каждого теста принимаем и закрываем окно cookie
        driver.findElement(By.cssSelector("button.cookie__close")).click();

        // Определяем кнопку, на которой отображается выбранная услуга
        WebElement selectHeaderButton = driver.findElement(
                By.cssSelector("span.select__now"));
        selectHeaderButton.click();
    }

    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test
    @DisplayName("Check Connection Services Fields")
    public void checkConnectionServicesFields() {
        /*
         * Список услуг в выпадающем списке идет в строго определенном порядке,
         * поэтому здесь и далее для выбора варианта в выпадающем списке
         * используем nth-child() (и разнообразия ради)
         */
        WebElement connection = driver.findElement(
                By.cssSelector(".select__item:nth-child(1)"));
        connection.click();

        // Ожидаемое содержимое полей из варианта "Услуги связи"
        String phone = "Номер телефона";
        String money = "Сумма";
        String email = "E-mail для отправки чека";

        // Фактическое содержимое полей из варианта "Услуги связи"
        String phonePlaceholder = driver.findElement(
                By.cssSelector("#connection-phone"))
                .getAttribute("placeholder");
        String moneyPlaceholder = driver.findElement(
                By.cssSelector("#connection-sum"))
                .getAttribute("placeholder");
        String emailPlaceholder = driver.findElement(
                By.cssSelector("#connection-email"))
                .getAttribute("placeholder");

        // Сравнение ожидаемого и фактического текста полей "Услуги связи"
        assertAll(
                () -> assertEquals(phone, phonePlaceholder),
                () -> assertEquals(money, moneyPlaceholder),
                () -> assertEquals(email, emailPlaceholder)
        );
    }

    @Test
    @DisplayName("Check Home Internet Fields")
    public void checkHomeInternetFields() {
        /*
         * Список услуг в выпадающем списке идет в строго определенном порядке,
         * поэтому используем nth-child() (заодно вспомню, как он работает)
         */
        WebElement internet = driver.findElement(
                By.cssSelector(".select__item:nth-child(2)"));
        internet.click();

        // Ожидаемое содержимое полей из варианта "Услуги связи"
        String phone = "Номер абонента";

        // Фактическое содержимое полей из варианта "Услуги связи"
        String phonePlaceholder = driver.findElement(
                        By.cssSelector("#internet-phone"))
                .getAttribute("placeholder");
        String moneyPlaceholder = driver.findElement(
                        By.cssSelector("#internet-sum"))
                .getAttribute("placeholder");
        String emailPlaceholder = driver.findElement(
                        By.cssSelector("#internet-email"))
                .getAttribute("placeholder");

        // Сравнение ожидаемого и фактического текста полей "Услуги связи"
        assertAll(
                () -> assertEquals(phone, phonePlaceholder),
                () -> assertEquals(money, moneyPlaceholder),
                () -> assertEquals(email, emailPlaceholder)
        );
    }

    @Test
    @DisplayName("Check Instalment Fields")
    public void checkInstalmentFields() {
        WebElement instalment = driver.findElement(
                By.cssSelector(".select__item:nth-child(3)"));
        instalment.click();

        // Ожидаемое содержимое полей из варианта "Услуги связи"
        String score = "Номер счета на 44";

        // Фактическое содержимое полей из варианта "Услуги связи"
        String scorePlaceholder = driver.findElement(
                        By.cssSelector("#score-instalment"))
                .getAttribute("placeholder");
        String moneyPlaceholder = driver.findElement(
                        By.cssSelector("#instalment-sum"))
                .getAttribute("placeholder");
        String emailPlaceholder = driver.findElement(
                        By.cssSelector("#instalment-email"))
                .getAttribute("placeholder");

        // Сравнение ожидаемого и фактического текста полей "Услуги связи"
        assertAll(
                () -> assertEquals(score, scorePlaceholder),
                () -> assertEquals(money, moneyPlaceholder),
                () -> assertEquals(email, emailPlaceholder)
        );
    }

    @Test
    @DisplayName("Check Arrears Fields")
    public void checkArrearsFields() {
        WebElement arrears = driver.findElement(
                By.cssSelector(".select__item:nth-child(4)"));
        arrears.click();

        // Ожидаемое содержимое полей из варианта "Услуги связи"
        String score = "Номер счета на 2073";

        // Фактическое содержимое полей из варианта "Услуги связи"
        String scorePlaceholder = driver.findElement(
                        By.cssSelector("#score-arrears"))
                .getAttribute("placeholder");
        String moneyPlaceholder = driver.findElement(
                        By.cssSelector("#arrears-sum"))
                .getAttribute("placeholder");
        String emailPlaceholder = driver.findElement(
                        By.cssSelector("#arrears-email"))
                .getAttribute("placeholder");

        // Сравнение ожидаемого и фактического текста полей "Услуги связи"
        assertAll(
                () -> assertEquals(score, scorePlaceholder),
                () -> assertEquals(money, moneyPlaceholder),
                () -> assertEquals(email, emailPlaceholder)
        );
    }
}
