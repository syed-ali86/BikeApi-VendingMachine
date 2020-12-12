package vendingmachine;

import vendingmachine.Bucket;
import vendingmachine.Coin;

import java.util.List;

/**
 * Declare public API for Vending Machine
 */
public interface VendingMachine {

    public long selectItemAndGetPrice(Item item);

    public void insertCoin(Coin coin);

    public List<Coin> refund();

    public Bucket<Item, List<Coin>> collectItemAndChange();

    public void reset();

    public Inventory<Coin> getCashInventory();

    public Inventory<Item> getItemInventory();

    public void initialize();


}
