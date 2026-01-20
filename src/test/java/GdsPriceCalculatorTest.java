import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.*;

class GdsPriceCalculatorTest {
    @Test
    void testAirlinePrice() {
        GdsPriceCalculator calculator = new GdsPriceCalculator();
        int result = calculator.add(2, 3);
        // If result is 5, the test passes. If not, it fails.
        assertEquals(5, result, "2 + 3 should equal 5");


    }


}