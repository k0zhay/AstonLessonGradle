import org.KozhaevSA.MyMath;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FactorialTest {
    @BeforeAll
    public static void startClass() {
        System.out.println("\nStart testing\n");
    }

    @AfterAll
    public static void endClass() {
        System.out.println("End testing\n");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("Try this number");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Great, It works (perhaps)\n");
    }

    // В JUnit попробуем со списком параметров
    @ParameterizedTest
    @DisplayName("Факториал числа")
    @CsvSource({ "1, 1", "5, 120" })
    public void factorialTest(int num, int expected) {
        System.out.printf("%d! = %d\n", num, MyMath.factorial(num));
        Assertions.assertEquals(expected, MyMath.factorial(num));
    }

    @Test
    @DisplayName("Пустышка")
    @Disabled("Проверка, что этот тест будет пропущен")
    public void empty() {
        System.out.println(777 + MyMath.factorial(0));
    }
}
