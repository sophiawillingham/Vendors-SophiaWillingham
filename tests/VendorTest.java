import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {

    @Test
    void testAddMoney() {
        Vending vendor = new Vending(5, 5);

        vendor.addMoney(10.00);

        assertEquals(10.00, vendor.getBalance(), 0.001);
    }

    @Test
    void testBuyItem() {
        Vending vendor = new Vending(5, 5);

        vendor.addMoney(5.00);
        vendor.select("Candy");

        assertEquals(3.75, vendor.getBalance(), 0.001);
    }

    @Test
    void testEmptyInventory() {
        Vending vendor = new Vending(5, 5);

        vendor.emptyInventory();

        assertEquals(0, vendor.getStock("Candy"));
        assertEquals(0, vendor.getStock("Gum"));
    }

    @Test
    void testRestockItem() {
        Vending vendor = new Vending(5, 5);

        vendor.restockItem("Candy", 10);

        assertEquals(15, vendor.getStock("Candy"));
    }

}