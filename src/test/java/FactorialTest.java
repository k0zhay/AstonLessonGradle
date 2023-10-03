import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FactorialTest {
    @ParameterizedTest
    @DisplayName("Факториал числа")
    @ValueSource(ints = { 1, 5 })
    public void factorial(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        System.out.printf("%d! = %d\n", num, result);
    }

    @Test
    @DisplayName("Пустышка")
    @Disabled("Тест-пустышка")
    public void empty() {
        System.out.println(777);
    }
}
