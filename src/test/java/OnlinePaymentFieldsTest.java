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

    // ��������� ��� ������ ���� �� ���� ���������, ������� ������� �� ��������
    String money = "�����";
    String email = "E-mail ��� �������� ����";

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
        // ����� �������� ������� ����� ��������� � ��������� ���� cookie
        driver.findElement(By.cssSelector("button.cookie__close")).click();

        // ���������� ������, �� ������� ������������ ��������� ������
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
         * ������ ����� � ���������� ������ ���� � ������ ������������ �������,
         * ������� ����� � ����� ��� ������ �������� � ���������� ������
         * ���������� nth-child() (� ������������ ����)
         */
        WebElement connection = driver.findElement(
                By.cssSelector(".select__item:nth-child(1)"));
        connection.click();

        // ��������� ���������� ����� �� �������� "������ �����"
        String phone = "����� ��������";
        String money = "�����";
        String email = "E-mail ��� �������� ����";

        // ����������� ���������� ����� �� �������� "������ �����"
        String phonePlaceholder = driver.findElement(
                By.cssSelector("#connection-phone"))
                .getAttribute("placeholder");
        String moneyPlaceholder = driver.findElement(
                By.cssSelector("#connection-sum"))
                .getAttribute("placeholder");
        String emailPlaceholder = driver.findElement(
                By.cssSelector("#connection-email"))
                .getAttribute("placeholder");

        // ��������� ���������� � ������������ ������ ����� "������ �����"
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
         * ������ ����� � ���������� ������ ���� � ������ ������������ �������,
         * ������� ���������� nth-child() (������ �������, ��� �� ��������)
         */
        WebElement internet = driver.findElement(
                By.cssSelector(".select__item:nth-child(2)"));
        internet.click();

        // ��������� ���������� ����� �� �������� "������ �����"
        String phone = "����� ��������";

        // ����������� ���������� ����� �� �������� "������ �����"
        String phonePlaceholder = driver.findElement(
                        By.cssSelector("#internet-phone"))
                .getAttribute("placeholder");
        String moneyPlaceholder = driver.findElement(
                        By.cssSelector("#internet-sum"))
                .getAttribute("placeholder");
        String emailPlaceholder = driver.findElement(
                        By.cssSelector("#internet-email"))
                .getAttribute("placeholder");

        // ��������� ���������� � ������������ ������ ����� "������ �����"
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

        // ��������� ���������� ����� �� �������� "������ �����"
        String score = "����� ����� �� 44";

        // ����������� ���������� ����� �� �������� "������ �����"
        String scorePlaceholder = driver.findElement(
                        By.cssSelector("#score-instalment"))
                .getAttribute("placeholder");
        String moneyPlaceholder = driver.findElement(
                        By.cssSelector("#instalment-sum"))
                .getAttribute("placeholder");
        String emailPlaceholder = driver.findElement(
                        By.cssSelector("#instalment-email"))
                .getAttribute("placeholder");

        // ��������� ���������� � ������������ ������ ����� "������ �����"
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

        // ��������� ���������� ����� �� �������� "������ �����"
        String score = "����� ����� �� 2073";

        // ����������� ���������� ����� �� �������� "������ �����"
        String scorePlaceholder = driver.findElement(
                        By.cssSelector("#score-arrears"))
                .getAttribute("placeholder");
        String moneyPlaceholder = driver.findElement(
                        By.cssSelector("#arrears-sum"))
                .getAttribute("placeholder");
        String emailPlaceholder = driver.findElement(
                        By.cssSelector("#arrears-email"))
                .getAttribute("placeholder");

        // ��������� ���������� � ������������ ������ ����� "������ �����"
        assertAll(
                () -> assertEquals(score, scorePlaceholder),
                () -> assertEquals(money, moneyPlaceholder),
                () -> assertEquals(email, emailPlaceholder)
        );
    }
}
