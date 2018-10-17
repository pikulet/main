package seedu.address.model.expenses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ExpenseTest {
    
    private final LocalDateTime validDateTime = LocalDateTime.parse("01/01/2018 23:59:59", Expense.FORMAT);

    private final Expense validExpense = new Expense(new ExpenseType("1", "-", 1), 0, validDateTime);

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Expense(null));
        Assert.assertThrows(NullPointerException.class, () -> new Expense(null, 0));
        Assert.assertThrows(NullPointerException.class, () -> new Expense(null, 0, LocalDateTime.now()));
        Assert.assertThrows(NullPointerException.class, () ->
                new Expense(new ExpenseType("1", "-", 0), 0, null));
    }

    @Test
    public void getCost() {
        assert validExpense.getCost() == 0;
    }

    @Test
    public void getExpenseType() {
        assert validExpense.getExpenseType().equals(new ExpenseType("1", "-", 1));
    }

    @Test
    public void getItemName() {
        assert validExpense.getItemName().equals("-");
    }

    @Test
    public void getDate() {
        assert validExpense.getDateTime().equals(validDateTime);
    }
}
