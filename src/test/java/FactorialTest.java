import static org.testng.Assert.assertEquals;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.KozhaevSA.MyMath;


public class FactorialTest {
    @BeforeClass
    private void startClass() {
        System.out.println("\nStart testing\n");
    }

    @AfterClass
    private void endClass() {
        System.out.println("End testing\n");
    }

    @BeforeMethod
    private void setUp() {
        System.out.println("Try this number");
    }

    @AfterMethod
    private void tearDown() {
        System.out.println("Great, It works\n");
    }

    @Test (priority = 1) // этот тест запустим вторым
    public void factorialTestOne() {
        System.out.printf("%d! = %d\n", 1, MyMath.factorial(1));
        assertEquals(1, MyMath.factorial(1));
    }


    @Test (priority = 0) // а этот - первым
    public void factorialTestFive() {
        System.out.printf("%d! = %d\n", 5, MyMath.factorial(5));
        assertEquals(120, MyMath.factorial(5));
    }
}
