package seedu.address.model.expenses;


import java.util.Date;

import seedu.address.model.expenses.exceptions.ItemNotFoundException;

/**
 * Represents a single spending on a single type of service.
 */
public class Expense {

    private final double cost;
    private final ExpenseType type;
    private final Date date;

    /**
     * Constructs an {@code Expense} object.
     *
     * @param type The menu number of the product or service exchanged for with this expense.
     * @param cost The monetary value of the expense.
     * @throws ItemNotFoundException if the item's menu number does not exist in the menu.
     */
    public Expense(String type, double cost) throws ItemNotFoundException {
        if (!ExpenseType.isValidMenuNumber(type)) {
            throw new ItemNotFoundException();
        }
        this.type = new ExpenseType(type);
        this.cost = cost;
        this.date = new Date();
    }

    /**
     * Constructs an {@code Expense} object.
     * Used if the user is charging the base price and hence does not need to manually enter a price.
     *
     * @param type The product or service exchanged for with this expense.
     * @throws ItemNotFoundException if the item's menu number does not exist in the menu.
     */
    public Expense(String type) throws ItemNotFoundException {
        if (!ExpenseType.isValidMenuNumber(type)) {
            throw new ItemNotFoundException();
        }
        this.type = new ExpenseType(type);
        this.cost = this.type.getItemPrice();
        this.date = new Date();
    }

    /**
     * Provides the cost of this expense.
     *
     * @return The monetary value of the expense.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Provides the expense type of the item purchased in this expense.
     *
     * @return The ExpenseType object of the product or service exchanged for with this expense.
     */
    public ExpenseType getExpenseType() {
        return type;
    }

    /**
     * Provides the name of the item purchased in this expense.
     *
     * @return The name of the product or service exchanged for with this expense.
     */
    public String getItemName() {
        return type.getItemName();
    }

    /**
     * Provides the date of the transaction.
     *
     * @return The date and time of this transaction.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Provides the date of the transaction in a string.
     *
     * @return The date and time of this transaction in string.
     */
    public String getDateString() {
        return date.toString();
    }

    @Override
    public String toString() {
        return cost + " spent on " + type.getItemName() + " on " + getDateString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Expenses // instanceof handles nulls
                && cost == ((Expense) other).cost
                && type.equals(((Expense) other).type)
                && date.equals(((Expense) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
