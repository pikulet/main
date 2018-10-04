package seedu.address.model.expenses;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import seedu.address.model.expenses.exceptions.ItemNotFoundException;

/**
 * Contains all the different types of expenses available at the hotel.
 * All products and services have a unique menu number.
 */
public class ExpenseType {
    private static final Map<String, String> numberToName;
    private static final Map<String, Double> numberToPrice;
    static {
        Map<String, String> numberToNameDummy = new HashMap<>();
        Map<String, Double> numberToPriceDummy = new HashMap<>();
        numberToNameDummy.put("RS01", "RS: Red wine");
        numberToNameDummy.put("RS02", "RS: Beef Steak");
        numberToNameDummy.put("RS03", "RS: Massage");
        numberToNameDummy.put("SP99", "SP: Pool entrance fee");
        numberToNameDummy.put("GY99", "GY: Gym entrance fee");
        numberToNameDummy.put("MB01", "Minibar: Coke");
        numberToNameDummy.put("MB02", "Minibar: Mineral water");
        numberToNameDummy.put("MB03", "Minibar: Beer");
        numberToPriceDummy.put("RS01", 50.00);
        numberToPriceDummy.put("RS02", 70.00);
        numberToPriceDummy.put("RS03", 100.00);
        numberToPriceDummy.put("SP99", 4.00);
        numberToPriceDummy.put("GY99", 10.00);
        numberToPriceDummy.put("MB01", 3.00);
        numberToPriceDummy.put("MB02", 3.00);
        numberToPriceDummy.put("MB03", 7.00);
        numberToName = Collections.unmodifiableMap(numberToNameDummy);
        numberToPrice = Collections.unmodifiableMap(numberToPriceDummy);
    }
    private final String itemNumber;
    private final String itemName;
    private final double itemPrice;

    /**
     * Constructor for an ExpenseType object. Only called via the {@code createExpenseType()} method.
     *
     * @param number The menu number of the item.
     * @throws ItemNotFoundException if the given menu number does not exist.
     */
    ExpenseType(String number) throws ItemNotFoundException {
        if (!isValidMenuNumber(number)) {
            throw new ItemNotFoundException();
        }
        itemNumber = number;
        itemName = numberToName.get(number);
        itemPrice = numberToPrice.get(number);
    }

    /**
     * Checks if a given menu number exists in the menu.
     * String does not have to be case-sensitive.
     *
     * @param menuNumber The menu number to be tested.
     * @return True if the menuNumber exists in the menu, false otherwise.
     */
    public static boolean isValidMenuNumber(String menuNumber) {
        return numberToName.containsKey(menuNumber);
    }

    /**
     * Returns the name of the item associated with the given menu number.
     *
     * @param menuNumber The menu number of the item.
     * @return The name of the item.
     * @throws ItemNotFoundException if the user provides a non-existent menu number.
     */
    public static String getItemName(String menuNumber) throws ItemNotFoundException {
        if (!isValidMenuNumber(menuNumber)) {
            throw new ItemNotFoundException();
        }
        return numberToName.get(menuNumber);
    }

    /**
     * Returns the name of the item in the ExpenseType object.
     *
     * @return The name of the item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Returns the price of the item associated with the given menu number.
     *
     * @param menuNumber The menu number of the item.
     * @return The price of the item.
     * @throws ItemNotFoundException if the user provides a non-existent menu number.
     */
    public static double getItemPrice(String menuNumber) throws ItemNotFoundException {
        if (!isValidMenuNumber(menuNumber)) {
            throw new ItemNotFoundException();
        }
        return numberToPrice.get(menuNumber);
    }

    /**
     * Returns the price of the item in the ExpenseType object.
     *
     * @return The price of the item.
     */
    public double getItemPrice() {
        return itemPrice;
    }

    @Override
    public String toString() {
        return itemNumber + " " + itemName + " $" + itemPrice;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseType // instanceof handles nulls
                && itemNumber.equals(((ExpenseType) other).itemNumber)
                && itemName.equals(((ExpenseType) other).itemName)
                && itemPrice == ((ExpenseType) other).itemPrice); // state check
    }

    @Override
    public int hashCode() {
        return itemNumber.hashCode();
    }
}
