package seedu.address.model.expenses;

import java.math.BigDecimal;
import java.math.BigInteger;

import seedu.address.model.expenses.exceptions.MoneyLimitExceededException;

/**
 * Class to represent monetary values.
 * Ensures that prices keyed in for Expense objects are valid.
 */
public class Money {

    private int dollars;
    private int cents;
    //v2.0: implement exchange rates

    public Money(int dollars, int cents) {
        if (cents >= 100) {
            throw new IllegalArgumentException();
        }
        this.dollars = dollars;
        this.cents = cents;
    }

    /**
     * Converts a string with valid money format into a Money object.
     * @param toConvert The string to convert to a Money object.
     */
    public Money(String toConvert) {
        if (!isValidMoneyFormat(toConvert)) {
            throw new IllegalArgumentException();
        }
        String[] parts = toConvert.split("\\.");
        dollars = Integer.parseInt(parts[0]);
        cents = Integer.parseInt(parts[1]);
    }

    /**
     * Checks that a string is a valid representation of money.
     * @param money The string to be checked.
     * @return True if the string is in money format, false otherwise.
     */
    public static boolean isValidMoneyFormat(String money) {
        // check 1: if starts with 0, should be 4 chars long, e.g. 0.45
        if (money.toCharArray()[0] == '0' && money.length() != 4) {
            return false;
        }

        // check 2: regex checking
        String regex = "\\d{1,9}\\.\\d{2}";
        if (!money.matches(regex)) {
            return false;
        }

        // check 3: range checking - dollar part should be within Integer.MAX_VALUE
        // assume BigDecimal and BigInteger can hold arbitrarily large numbers
        BigDecimal d = new BigDecimal(money);
        BigInteger i = d.toBigInteger();
        if (i.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) == 1) {
            return false;
        }

        return true;
    }

    public Money add(Money addend) {
        long newDollars = (long) dollars + (long) addend.dollars;
        int newCents = cents + addend.cents;
        if (newCents >= 100) {
            newCents = newCents % 100;
            newDollars += 1;
        }
        if (newDollars > Integer.MAX_VALUE) {
            throw new MoneyLimitExceededException();
        }
        return new Money((int) newDollars, newCents);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Money // instanceof handles nulls
                && dollars == ((Money) other).dollars
                && cents == ((Money) other).cents); // state check
    }

    @Override
    public String toString() {
        return dollars + "." + String.format("%02d", cents);
    }

    @Override
    public int hashCode() {
        return (dollars * 100) + cents;
    }
}
