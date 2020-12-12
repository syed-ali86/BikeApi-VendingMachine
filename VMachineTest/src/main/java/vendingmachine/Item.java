package vendingmachine;

/**
 * Items or products supported by Vending Machine.
 */
public enum Item {

    COKE("COKE", 25), PEPSI("PEPSI", 35), SODA("SODA", 45);

    private final String name;
    private final int price;

    Item(String name, int price) {

        this.name = name;
        this.price = price;
    }

    public String getName() {

        return name;
    }

    public int getPrice() {

        return price;
    }
}
