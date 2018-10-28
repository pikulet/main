package seedu.address.model.expenses;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import seedu.address.model.expenses.exceptions.MoneyLimitExceededException;
import seedu.address.testutil.Assert;

public class MoneyTest {

    @Test
    public void constructor_invalidCents_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Money(123,456));
    }

    @Test
    public void constructor_validDollarsAndCents_success() {
        try {
            Money m = new Money(123, 45);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void constructor_invalidString_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Money("abc"));
    }

    @Test
    public void constructor_validString_success() {
        try {
            Money m = new Money("123.45");
            assertTrue(m.equals(new Money(123, 45)));
            Money n = new Money("987654321.01");
            assertTrue(n.equals(new Money(987654321, 1)));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void isValidMoneyFormat() {
        // valid
        assertTrue(Money.isValidMoneyFormat("123.45"));
        assertTrue(Money.isValidMoneyFormat("999999999.99"));
        assertTrue(Money.isValidMoneyFormat("1.01"));
        assertTrue(Money.isValidMoneyFormat("0.45"));

        // invalid
        assertFalse(Money.isValidMoneyFormat("abc"));
        assertFalse(Money.isValidMoneyFormat("2234567890.12"));
        assertFalse(Money.isValidMoneyFormat("123.456"));
        assertFalse(Money.isValidMoneyFormat("0123.45"));
        assertFalse(Money.isValidMoneyFormat("123a45"));
    }

    @Test
    public void add_sumTooLarge_throwsMoneyLimitExceededException() {
        Money a = new Money(Integer.MAX_VALUE, 99);
        Money b = new Money(0, 1);
        Money c = new Money(2000000000, 0);
        Money d = new Money(1000000000, 0);
        Money e = new Money(1000000000, 0);
        Money f = new Money(1000000000, 0);
        Assert.assertThrows(MoneyLimitExceededException.class, () -> a.add(b));
        Assert.assertThrows(MoneyLimitExceededException.class, () -> c.add(d));
        Assert.assertThrows(MoneyLimitExceededException.class, () -> d.add(e).add(f));
    }

    @Test
    public void add_validInput_success() {
        Money a = new Money(475, 9);
        Money b = new Money(123, 89);
        Money c = new Money(684, 34);
        assertTrue(a.add(b).equals(new Money(598, 98)));
        assertTrue(b.add(c).equals(new Money(808, 23)));
    }
}
