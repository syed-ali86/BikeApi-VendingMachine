package vendingmachine;

import exceptions.NotFullPaidException;
import exceptions.NotSufficientChangeException;
import exceptions.SoldOutException;
import vendingmachine.Bucket;
import vendingmachine.Coin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sample implementation of Vending Machine.
 */



public class VendingMachineImpl implements VendingMachine {

    private final Inventory<Coin> cashInventory = new Inventory<>();

    public Inventory<Coin> getCashInventory() {
        return cashInventory;
    }

    public Inventory<Item> getItemInventory() {
        return itemInventory;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public long getCurrentBalance() {
        return currentBalance;
    }

    private final Inventory<Item> itemInventory = new Inventory<>();



    private long totalSales;
    private Item currentItem;
    private long currentBalance;

    public long getTotalSales() {
        return totalSales;
    }
    public VendingMachineImpl() {

        initialize();
    }

    public void initialize() {
        //initialize machine with 5 coins of each denomination
        //and 5 cans of each Item
        for (Coin c : Coin.values()) {
            cashInventory.put(c, 5);
        }

        for (Item i : Item.values()) {
            itemInventory.put(i, 5);
        }

    }

    @Override
    public long selectItemAndGetPrice(Item item) {

        if (itemInventory.hasItem(item)) {
            currentItem = item;
            return currentItem.getPrice();
        }
        throw new SoldOutException("Sold Out, Please buy another item");
    }

    @Override
    public void insertCoin(Coin coin) {

        currentBalance = currentBalance + coin.getDenomination();
        cashInventory.add(coin);
    }

    @Override
    public Bucket<Item, List<Coin>> collectItemAndChange() {

        Item item = collectItem();
        totalSales = totalSales + currentItem.getPrice();

        List<Coin> change = collectChange();

        return new Bucket<>(item, change);
    }

    private Item collectItem() {

        if (isFullPaid()) {
            if (hasSufficientChange()) {
                itemInventory.deduct(currentItem);
                return currentItem;
            }
            throw new NotSufficientChangeException("Not Sufficient change in Inventory");

        }
        long remainingBalance = currentItem.getPrice() - currentBalance;
        throw new NotFullPaidException("Price not full paid, remaining : ", remainingBalance);
    }

    private List<Coin> collectChange() {

        long changeAmount = currentBalance - currentItem.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }

    @Override
    public List<Coin> refund() {

        List<Coin> refund = getChange(currentBalance);
        updateCashInventory(refund);
        currentBalance = 0;
        currentItem = null;
        return refund;
    }


    private boolean isFullPaid() {

        return currentBalance >= currentItem.getPrice();
    }


    private List getChange(long amount) {

        List changes = Collections.EMPTY_LIST;

        if (amount > 0) {
            changes = new ArrayList<Coin>();
            long balance = amount;
            while (balance > 0) {
                if (balance >= Coin.POUND_2.getDenomination()
                        && cashInventory.hasItem(Coin.POUND_2)) {
                    changes.add(Coin.POUND_2);
                    balance = balance - Coin.POUND_2.getDenomination();
                    continue;

                } else if (balance >= Coin.POUND_1.getDenomination()
                        && cashInventory.hasItem(Coin.POUND_1)) {
                    changes.add(Coin.POUND_1);
                    balance = balance - Coin.POUND_1.getDenomination();

                } else if (balance >= Coin.PENCE_50.getDenomination()
                        && cashInventory.hasItem(Coin.PENCE_50)) {
                    changes.add(Coin.PENCE_50);
                    balance = balance - Coin.PENCE_50.getDenomination();

                } else if (balance >= Coin.PENCE_20.getDenomination()
                        && cashInventory.hasItem(Coin.PENCE_20)) {
                    changes.add(Coin.PENCE_20);
                    balance = balance - Coin.PENCE_20.getDenomination();

                }else if (balance >= Coin.PENCE_10.getDenomination()
                        && cashInventory.hasItem(Coin.PENCE_10)) {
                    changes.add(Coin.PENCE_10);
                    balance = balance - Coin.PENCE_10.getDenomination();

                }else if (balance >= Coin.PENCE_5.getDenomination()
                        && cashInventory.hasItem(Coin.PENCE_5)) {
                    changes.add(Coin.PENCE_5);
                    balance = balance - Coin.PENCE_5.getDenomination();

                }else if (balance >= Coin.PENCE_2.getDenomination()
                        && cashInventory.hasItem(Coin.PENCE_2)) {
                    changes.add(Coin.PENCE_2);
                    balance = balance - Coin.PENCE_1.getDenomination();

                }else if (balance >= Coin.PENCE_1.getDenomination()
                        && cashInventory.hasItem(Coin.PENCE_1)) {
                    changes.add(Coin.PENCE_1);
                    balance = balance - Coin.PENCE_1.getDenomination();

                }else {
                    throw new NotSufficientChangeException("NotSufficientChange, Please try another product ");
                }
            }
        }

        return changes;
    }

    @Override
    public void reset() {

        cashInventory.clear();
        itemInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }


    private boolean hasSufficientChange() {

        return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
    }

    private boolean hasSufficientChangeForAmount(long amount) {

        boolean hasChange = true;
        try {
            getChange(amount);
        } catch (NotSufficientChangeException nsce) {
            return false;
        }

        return hasChange;
    }

    private void updateCashInventory(List<Coin> change) {

        for (Coin c : change) {
            cashInventory.deduct(c);
        }
    }

}
