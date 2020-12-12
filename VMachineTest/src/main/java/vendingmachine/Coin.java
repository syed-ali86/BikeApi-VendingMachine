package vendingmachine;

/**
 * Coins supported by Vending Machine.
 */
public enum Coin {

    PENCE_50(50), PENCE_20(20), PENCE_10(10), PENCE_5(5),
    PENCE_2(2),PENCE_1(1),POUND_1(100),POUND_2(200);

    private final int denomination;

    Coin(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}