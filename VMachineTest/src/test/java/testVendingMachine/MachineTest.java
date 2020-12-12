package testVendingMachine;


import org.junit.AfterClass;
import org.junit.jupiter.api.*;
import vendingmachine.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MachineTest {

    private static VendingMachine vm;

    @BeforeAll
    public static void setUp() {

        vm = VendingMachineFactory.createVendingMachine();
    }

    @AfterAll
    public static void tearDown() {

        vm = null;
    }

    @Test
    @DisplayName("Accepts coins of 1,2,5,10,20,50,100,200 Cents i.e. penny, nickel, dime, and quarter")
    public void acceptingCoins(){
        vm.insertCoin(Coin.PENCE_1);
        vm.insertCoin(Coin.PENCE_2);
        vm.insertCoin(Coin.PENCE_5);
        vm.insertCoin(Coin.PENCE_10);
        vm.insertCoin(Coin.PENCE_20);
        vm.insertCoin(Coin.PENCE_50);
        vm.insertCoin(Coin.POUND_1);
        vm.insertCoin(Coin.POUND_2);
        Inventory<Coin> coin2 = vm.getCashInventory();
        Assertions.assertAll(()->assertEquals(6,coin2.getQuantity(Coin.POUND_2)),
                ()->assertEquals(6,coin2.getQuantity(Coin.POUND_1)),
                ()->assertEquals(6,coin2.getQuantity(Coin.PENCE_50)),
                ()->assertEquals(6,coin2.getQuantity(Coin.PENCE_20)),
                ()->assertEquals(6,coin2.getQuantity(Coin.PENCE_10)),
                ()->assertEquals(6,coin2.getQuantity(Coin.PENCE_5)),
                ()->assertEquals(6,coin2.getQuantity(Coin.PENCE_2)),
                ()->assertEquals(6,coin2.getQuantity(Coin.PENCE_1))
        );
        vm.reset();
        vm.initialize();
    }


    @Test
    @DisplayName("Allow user to select products Coke(25), Pepsi(35), Soda(45)")
    public void selectProduct(){
        long coke = vm.selectItemAndGetPrice(Item.COKE);
        long pepsi = vm.selectItemAndGetPrice(Item.PEPSI);
        long soda = vm.selectItemAndGetPrice(Item.SODA);

        Assertions.assertAll(()->assertEquals(25,coke),
                ()-> assertEquals(35,pepsi),
                ()->assertEquals(45,soda)
                );
    }

    @Test
    @DisplayName("Allow user to take refund by cancelling the request")
    public void refundByCancel(){
        long price = vm.selectItemAndGetPrice(Item.PEPSI);
        vm.insertCoin(Coin.POUND_1);
        Assertions.assertAll(()-> assertEquals(Item.PEPSI.getPrice(), price),
                ()->assertEquals(100, getTotal(vm.refund())
                ));


    }

    @Test
    @DisplayName("Return selected product and remaining change if any")
    public void productAndChange(){
        long price = vm.selectItemAndGetPrice(Item.COKE);
        //assertEquals(Item.PEPSI.getPrice(), price);
        vm.insertCoin(Coin.PENCE_5);
        vm.insertCoin(Coin.PENCE_20);
        vm.insertCoin(Coin.PENCE_5);        Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
        Item item = bucket.getFirst();
        List<Coin> change = bucket.getSecond();
        Assertions.assertAll(()->assertEquals(Item.COKE,item),
                ()->assertEquals(5,change.get(0).getDenomination())
                );



    }

    @Test
    @DisplayName("Allow reset operation for vending machine supplier")
    public void resetMachine(){

        Inventory<Coin> coin = vm.getCashInventory();
        Inventory<Item>  items = vm.getItemInventory();
        productAndChange();
        vm.reset();
        Assertions.assertAll(
                ()->assertEquals(0,coin.getQuantity(Coin.POUND_1)),
                ()->assertEquals(0,coin.getQuantity(Coin.POUND_2)),
                ()->assertEquals(0,coin.getQuantity(Coin.PENCE_50)),
                ()->assertEquals(0,coin.getQuantity(Coin.PENCE_20)),
                ()->assertEquals(0,coin.getQuantity(Coin.PENCE_10)),
                ()->assertEquals(0,coin.getQuantity(Coin.PENCE_5)),
                ()->assertEquals(0,coin.getQuantity(Coin.PENCE_2)),
                ()->assertEquals(0,coin.getQuantity(Coin.PENCE_1)),
                ()->assertEquals(0,items.getQuantity(Item.COKE)),
                ()->assertEquals(0,items.getQuantity(Item.PEPSI)),
                ()->assertEquals(0,items.getQuantity(Item.SODA))
        );
        vm.initialize();
                Assertions.assertAll(
                ()->assertEquals(5,coin.getQuantity(Coin.POUND_1)),
                ()->assertEquals(5,coin.getQuantity(Coin.POUND_2)),
                ()->assertEquals(5,coin.getQuantity(Coin.PENCE_50)),
                ()->assertEquals(5,coin.getQuantity(Coin.PENCE_20)),
                ()->assertEquals(5,coin.getQuantity(Coin.PENCE_10)),
                ()->assertEquals(5,coin.getQuantity(Coin.PENCE_5)),
                ()->assertEquals(5,coin.getQuantity(Coin.PENCE_2)),
                ()->assertEquals(5,coin.getQuantity(Coin.PENCE_1)),
                ()->assertEquals(5,items.getQuantity(Item.COKE)),
                ()->assertEquals(5,items.getQuantity(Item.PEPSI)),
                ()->assertEquals(5,items.getQuantity(Item.SODA))
                );
    }



    private long getTotal(List<Coin> change) {

        long total = 0;
        for (Coin c : change) {
            total = total + c.getDenomination();
        }
        return total;
    }
}