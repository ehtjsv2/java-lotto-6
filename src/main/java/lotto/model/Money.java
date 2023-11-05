package lotto.model;

import static lotto.ErrorMessage.INDIVISIBLE_NUMBER;
import static lotto.ErrorMessage.NOT_NUMBER_FORMAT;
import static lotto.ErrorMessage.ZERO_NUMBER;

public class Money {
    private final static int LOTTO_PRICE = 1000;
    private int money;

    public Money(String money) {
        checkValidMoneyFormat(money);
        this.money = Integer.parseInt(money);
    }

    public int moneyToLottoCount(){
        return money/LOTTO_PRICE;
    }

    private void checkValidMoneyFormat(String money) {
        checkNotNumber(money);
        checkIndivisibleMoney(Integer.parseInt(money));
        checkZeroMoney(Integer.parseInt(money));
    }

    private void checkIndivisibleMoney(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(INDIVISIBLE_NUMBER.getMessage());
        }
    }

    private void checkZeroMoney(int money) {
        if (money == 0) {
            throw new IllegalArgumentException(ZERO_NUMBER.getMessage());
        }
    }

    private void checkNotNumber(String money) {
        if (!money.matches("\\d+")) {
            throw new IllegalArgumentException(NOT_NUMBER_FORMAT.getMessage());
        }
    }
}
