import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testMultipleVendors() {
        Vending[] vendors = new Vending[5];

        for (int i = 0; i < 5; i++) {
            vendors[i] = new Vending(i + 1, i + 2);
        }

        assertEquals(5, vendors.length);
        assertEquals(1, vendors[0].getStock("Candy"));
        assertEquals(5, vendors[4].getStock("Candy"));
    }

    @Test
    void testRemoveItem() {
        Vending vendor = new Vending(5, 5);

        vendor.removeItem("Candy");

        assertEquals(0, vendor.getStock("Candy"));
        assertEquals(5, vendor.getStock("Gum"));
    }

    @Test
    void testPurchaseTracking() {
        Vending vendor = new Vending(5, 5);

        vendor.addMoney(10.00);
        vendor.select("Candy");
        vendor.select("Candy");
        vendor.select("Gum");

        assertEquals(2, vendor.getPurchaseCount("Candy"));
        assertEquals(1, vendor.getPurchaseCount("Gum"));
    }

    @Test
    void testItemDescription() {
        Vending vendor = new Vending(5, 5);

        vendor.setItemDescription("Candy", "Sweet chocolate candy");

        assertEquals("Sweet chocolate candy",
                vendor.getItemDescription("Candy"));
        assertEquals("Item not found",
                vendor.getItemDescription("Chips"));
    }

    @Test
    void testApplyDiscount() {
        Vending vendor = new Vending(5, 5);

        vendor.applyDiscount("Candy", 20);

        assertEquals(1.00, vendor.getItemPrice("Candy"), 0.001);

        vendor.addMoney(2.00);
        vendor.select("Candy");

        assertEquals(1.00, vendor.getBalance(), 0.001);
    }

    @Test
    void testBestseller() {
        Vending vendor = new Vending(5, 5);

        vendor.markBestseller("Candy");

        assertTrue(vendor.isBestseller("Candy"));
        assertFalse(vendor.isBestseller("Gum"));
    }

    @Test
    void testCategoryDiscount() {
        Vending vendor = new Vending(5, 5);

        vendor.setItemCategory("Candy", "Snacks");
        vendor.setItemCategory("Gum", "Snacks");

        vendor.applyCategoryDiscount("Snacks", 20);

        assertEquals(1.00, vendor.getItemPrice("Candy"), 0.001);
        assertEquals(0.40, vendor.getItemPrice("Gum"), 0.001);
    }

    @Test
    void testInsufficientMoney() {
        Vending vendor = new Vending(5, 5);

        vendor.addMoney(0.50);
        vendor.select("Candy");

        assertEquals(5, vendor.getStock("Candy"));
        assertEquals(0.50, vendor.getBalance(), 0.001);
    }

    @Test
    void testOutOfStock() {
        Vending vendor = new Vending(0, 5);

        vendor.addMoney(10.00);
        vendor.select("Candy");

        assertEquals(0, vendor.getStock("Candy"));
        assertEquals(10.00, vendor.getBalance(), 0.001);
        assertEquals(0, vendor.getPurchaseCount("Candy"));
    }

    @Test
    void testPurchaseUpdatesInventory() {
        Vending vendor = new Vending(5, 5);

        vendor.addMoney(5.00);
        vendor.select("Candy");

        // Checks inventory and balance after a purchase
        assertEquals(4, vendor.getStock("Candy"));
        assertEquals(3.75, vendor.getBalance(), 0.001);
        assertEquals(1, vendor.getPurchaseCount("Candy"));
    }

}