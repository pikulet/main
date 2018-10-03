package seedu.address.model.expenses;

import java.util.Date;

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
     * @param cost The monetary value of the expense.
     * @param type The product or service exchanged for with this expense.
     */
    public Expense(double cost, ExpenseType type) {
        this.cost = cost;
        this.type = type;
        this.date = new Date();
    }

    /**
     * Accessor method for the cost.
     *
     * @return The monetary value of the expense.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Accessor method for the type.
     *
     * @return The product or service exchanged for with this expense.
     */
    public ExpenseType getType() {
        return type;
    }

    /**
     * Accessor method for the date.
     *
     * @return The date and time of this transaction.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Accessor method for the date in a string.
     *
     * @return The date and time of this transaction in string.
     */
    public String getDateString() {
        return date.toString();
    }

    @Override
    public String toString() {
        return cost + " spent on " + type + " on " + getDateString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Expenses // instanceof handles nulls
                && cost == ((Expense) other).cost
                && type == ((Expense) other).type
                && date.equals(((Expense) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
