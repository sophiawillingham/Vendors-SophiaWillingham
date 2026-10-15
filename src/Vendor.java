import java.util.HashMap;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    private HashMap<String, Item> Stock = new HashMap<String, Item>();
    private double balance;

    Vending(int numCandy, int numGum) {
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
        this.balance = 0;
    }

    /**
     * resets the Balance to 0
     */
    void resetBalance() {
        this.balance = 0;
    }

    /**
     * returns the current balance
     */
    double getBalance() {
        return this.balance;
    }

    /**
     * adds money to the machine's balance
     *
     * @param amt how much money to add
     *
     */
    void addMoney(double amt) {
        this.balance = this.balance + amt;
    }

    /**
     * attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select(String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if (balance >= item.price) {
                item.purchase(1);
                this.balance = this.balance - item.price;
            } else
                System.out.println("Gimme more money");
        } else System.out.println("Sorry, don't know that item");
    }

    void emptyInventory() { // empties the vendor's inventory
        for (Item item : Stock.values()) {
            item.stock = 0;
        }
    }

    int getStock(String name) { // returns the amount of an item in stock
        if (Stock.containsKey(name)) {
            return Stock.get(name).stock;
        }
        return 0;
    }

    void restockItem(String name, int amount) { // adds more stock to an existing item
        if (Stock.containsKey(name) && amount > 0) {
            Stock.get(name).restock(amount);
        }
    }

    // restocks
    void restockItem(String name, int amount, double price) {
        if (amount <= 0 || price < 0) {
            return;
        }

        if (Stock.containsKey(name)) {
            Stock.get(name).restock(amount);
        } else {
            Stock.put(name, new Item(price, amount));
        }
    }
}