package seedu.address.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import seedu.address.model.expenses.Expense;
import seedu.address.model.expenses.ExpenseType;
import seedu.address.model.expenses.exceptions.ItemNotFoundException;

/**
 * A representation of the menu of the items and services for sale.
 * This class should be read-only.
 */
public class Menu {

    private HashMap<String, ExpenseType> numberToType;

    public Menu() {
        numberToType = new HashMap<>();
    }

    public Menu(Menu other) {
        numberToType = new HashMap<>(other.numberToType);
    }

    public void setMenu(Map<String, ExpenseType> other) {
        numberToType.putAll(other);
    }

    public Map<String, ExpenseType> asUnmodifiableMap() {
        return Collections.unmodifiableMap(numberToType);
    }

    public boolean isValidMenuNumber(String item) {
        return numberToType.containsKey(item);
    }

    public ExpenseType getExpenseType(String item) {
        if (!isValidMenuNumber(item)) {
            throw new ItemNotFoundException();
        }
        return numberToType.get(item);
    }
}
