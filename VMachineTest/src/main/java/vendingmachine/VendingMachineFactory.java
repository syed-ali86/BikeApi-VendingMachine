package vendingmachine;
import vendingmachine.VendingMachineImpl;

/**
 * Factory class to create instance of Vending Machine, this can be extended to create instance of * different types of vending machines.
 */
public class VendingMachineFactory {

    private VendingMachineFactory() {

    }

    public static VendingMachine createVendingMachine() {

        return new VendingMachineImpl();
    }
}
