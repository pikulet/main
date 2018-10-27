package seedu.address.model.expenses.exceptions;

public class MoneyLimitExceededException extends RuntimeException {
    public MoneyLimitExceededException() {
        super("The resulting sum of money is too large.");
    }
}
