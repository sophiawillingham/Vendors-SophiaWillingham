import java.util.HashMap;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    private HashMap<String, Item> Stock = new HashMap<String, Item>();
    private double balance;
    private HashMap<String, Integer> purchaseCount = new HashMap<>();

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
            if (item.stock > 0 && balance >= item.price) {
                item.purchase(1);
                purchaseCount.put(name, purchaseCount.getOrDefault(name, 0) + 1);
                this.balance = this.balance - item.price;
            } else
                System.out.println("Gimme more money");
        } else System.out.println("Sorry, don't know that item");
    }

    void emptyInventory() { // empties the vendors inventory
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

    // changes an items name
    void renameItem(String oldName, String newName) {
        if (Stock.containsKey(oldName) && !Stock.containsKey(newName)
                && newName != null && !newName.isEmpty()) {
            Item item = Stock.remove(oldName);
            Stock.put(newName, item);
        }
    }

    // prints items and their stock amounts
    void printInventory() {
        for (String name : Stock.keySet()) {
            Item item = Stock.get(name);
            System.out.println(name + ": " + item.stock);
        }
    }

    // returns the number of purchases
    int getPurchaseCount(String name) {
        return purchaseCount.getOrDefault(name, 0);
    }

    // removes an item from the vendor's inventory
    void removeItem(String name) {
        Stock.remove(name);
    }
    // sets the description
    void setItemDescription(String name, String description) {
        if (Stock.containsKey(name)) {
            Stock.get(name).description = description;
        }
    }

    // returns description
    String getItemDescription(String name) {
        if (Stock.containsKey(name)) {
            return Stock.get(name).description;
        }
        return "Item not found";
    }

    // applies a discount
    void applyDiscount(String name, double percent) {
        if (Stock.containsKey(name) && percent >= 0 && percent <= 100) {
            Item item = Stock.get(name);
            item.price = item.price * (1 - percent / 100);
        }
    }

    // returns current price
    double getItemPrice(String name) {
        if (Stock.containsKey(name)) {
            return Stock.get(name).price;
        }
        return 0;
    }
    // marks an item as bestseller
    void markBestseller(String name) {
        if (Stock.containsKey(name)) {
            Stock.get(name).bestseller = true;
        }
    }

    // checks if an item is a bestseller
    boolean isBestseller(String name) {
        if (Stock.containsKey(name)) {
            return Stock.get(name).bestseller;
        }
        return false;
    }

    // applies a discount to every item in a category
    void applyCategoryDiscount(String category, double percent) {
        if (percent < 0 || percent > 100) {
            return;
        }

        for (String name : Stock.keySet()) {
            Item item = Stock.get(name);

            if (item.category.equals(category)) {
                applyDiscount(name, percent);
            }
        }
    }

    void setItemCategory(String name, String category) {
        if (Stock.containsKey(name)) {
            Stock.get(name).category = category;
        }
    }
}
