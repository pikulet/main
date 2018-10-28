package seedu.address.model.expenses;

import java.math.BigDecimal;
import java.math.BigInteger;

import seedu.address.model.expenses.exceptions.MoneyLimitExceededException;

/**
 * Class to represent monetary values.
 * Ensures that prices keyed in for Expense objects are valid.
 * Range of dollars limited to +/- Integer.MAX_VALUE.
 * Range does not include Integer.MIN_VALUE to prevent errors when changing signs.
 */
public class Money {

    private int dollars;
    private int cents;
    //v2.0: implement exchange rates

    public Money(int dollars, int cents) {
        if (cents >= 100 || cents < 0 || dollars == Integer.MIN_VALUE) {
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
        // check 1: if negative value, make a new string without negative sign
        String positiveMoney = new String(money);
        if (money.toCharArray()[0] == '-') {
            positiveMoney = money.substring(1);
        }

        // check 2: if starts with 0, should be 4 chars long, e.g. 0.45
        if (positiveMoney.toCharArray()[0] == '0' && positiveMoney.length() != 4) {
            return false;
        }

        // check 3: regex checking
        String regex = "\\d{1,10}\\.\\d{2}";
        if (!positiveMoney.matches(regex)) {
            return false;
        }

        // check 4: range checking
        // assume BigDecimal and BigInteger can hold arbitrarily large numbers
        BigDecimal d = new BigDecimal(positiveMoney);
        BigInteger i = d.toBigInteger();
        if (i.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) == 1) {
            return false;
        }

        return true;
    }

    public Money add(Money addend) {
        long newDollars;
        int newCents;
        if (dollars >= 0 && addend.dollars >= 0) {
            newDollars = (long) dollars + (long) addend.dollars;
            newCents = cents + addend.cents;
            if (newCents >= 100) {
                newCents = newCents % 100;
                newDollars += 1;
            }
            if (newDollars > Integer.MAX_VALUE) {
                throw new MoneyLimitExceededException();
            }
            return new Money((int) newDollars, newCents);
        } else if (dollars < 0 && addend.dollars < 0) {
            return (this.flipSign().add(addend.flipSign())).flipSign();
        } else if (dollars >= 0 && addend.dollars < 0) {
            newDollars = dollars + addend.dollars;
            newCents = cents - addend.cents;
            if (newCents < 0 && newDollars >= 0) {
                newCents = 100 + newCents;
                newDollars -= 1;
            } else if (newCents >= 0 && newDollars < 0) {
                newCents = 100 - newCents;
                newDollars += 1;
            } else if (newCents < 0 && newDollars < 0) {
                newCents = -1 * newCents;
            }
            // don't need to throw MoneyLimitExceededException since it is
            // impossible to go beyond the range here.
            return new Money((int) newDollars, newCents);
        } else {
            return addend.add(this);
        }
    }

    private Money flipSign() {
        return new Money(dollars * -1, cents);
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
