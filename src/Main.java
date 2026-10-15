public class Main {
    public static void main(String[] args) {

        // creates five vendors with different inventory amounts
        Vending[] vendors = new Vending[5];

        vendors[0] = new Vending(5, 10);
        vendors[1] = new Vending(10, 5);
        vendors[2] = new Vending(15, 10);
        vendors[3] = new Vending(20, 15);
        vendors[4] = new Vending(25, 20);

        // prints each vendors inventory
        for (int i = 0; i < vendors.length; i++) {
            System.out.println("Vendor " + (i + 1));
            vendors[i].printInventory();
            System.out.println();
        }
    }
}