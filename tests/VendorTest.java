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

    @Test
    void testAddNewItem() {
        Vending vendor = new Vending(5, 5);

        vendor.restockItem("Chips", 10, 2.00);

        assertEquals(10, vendor.getStock("Chips"));
    }

    @Test
    void testRenameItem() {
        Vending vendor = new Vending(5, 5);

        vendor.renameItem("Candy", "Chocolate");

        assertEquals(5, vendor.getStock("Chocolate"));
        assertEquals(0, vendor.getStock("Candy"));
    }

}