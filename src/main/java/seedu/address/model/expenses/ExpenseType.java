package seedu.address.model.expenses;

/**
 * Represents all the different types of expenses available at the hotel. 
 */
public enum ExpenseType {
    ROOM_SERVICE, 
    RESTAURANT, 
    BAR, 
    SWIMMING_POOL;

    /**
     * Checks if a given string is the name of one of the expense types. 
     * String does not have to be case-sensitive. 
     * 
     * @param typeString The string to be tested. 
     * @return True if the string is the name of a valid expense type, false otherwise. 
     */
    public static boolean isValidExpenseType(String typeString) {
        String upper = typeString.toUpperCase();
        for (ExpenseType t : ExpenseType.values()) {
            if (upper.equals(t.toString())) {
                return true;
            }
        }
        return false;
    }
}
