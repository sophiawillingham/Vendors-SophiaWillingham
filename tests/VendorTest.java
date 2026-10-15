import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {

    @Test
    void testAddMoney() {
        Vending vendor = new Vending(5, 5);

        vendor.addMoney(10.00);

        assertEquals(10.00, vendor.getBalance(), 0.001);
    }

}