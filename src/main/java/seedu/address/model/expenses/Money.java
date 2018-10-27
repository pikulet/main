package seedu.address.model.expenses;

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
        // limit dollar part to be under 1 billion, for ease of checking
        // and also because exceeding 1 billion in expenditure is
        // very unlikely.
        String regex = "\\d{1,9}\\.\\d{2}";
        return money.matches(regex);
    }

    public Money add(Money addend) {
        long newDollars = dollars + addend.dollars;
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
