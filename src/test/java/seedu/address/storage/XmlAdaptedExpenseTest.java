package seedu.address.storage;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import org.junit.Test;

import seedu.address.model.Menu;
import seedu.address.model.expenses.Expense;
import seedu.address.model.expenses.ExpenseType;
import seedu.address.testutil.Assert;

public class XmlAdaptedExpenseTest {
    private static final String INVALID_ITEM = "1";
    private static final String INVALID_DATETIME = "1/1/2018 12:34:56";

    private static final String VALID_ITEM = "RS01";
    private static final String VALID_DATETIME = "2018-10-14T22:34:40.283201400";

    private static final Menu VALID_MENU = new Menu();
    {
        HashMap<String, ExpenseType> validMenuMap = new HashMap<>();
        ExpenseType validExpenseType = new ExpenseType(VALID_ITEM, "-", 1);
        validMenuMap.put(VALID_ITEM, validExpenseType);
        VALID_MENU.setMenu(validMenuMap);
    }

    @Test
    public void toModelType_validDetails_returnsNormalExpense() {
        XmlAdaptedExpense testExpense = new XmlAdaptedExpense(VALID_ITEM, 1, VALID_DATETIME);
        Expense convertedExpense = testExpense.toModelType(VALID_MENU);
        assertEquals(convertedExpense.getExpenseType(), VALID_MENU.getExpenseType(VALID_ITEM));
        assertEquals(convertedExpense.getDate(), LocalDateTime.parse(VALID_DATETIME));
    }

    @Test
    public void toModelType_invalidItem_returnsUnknownExpense() {
        XmlAdaptedExpense testExpense = new XmlAdaptedExpense(INVALID_ITEM, 1, VALID_DATETIME);
        Expense convertedExpense = testExpense.toModelType(VALID_MENU);
        ExpenseType unknownType = new ExpenseType(INVALID_ITEM,
                XmlAdaptedExpense.EXPENSETYPE_UNKNOWN_NAME, XmlAdaptedExpense.EXPENSETYPE_UNKNOWN_COST);
        assertEquals(convertedExpense.getExpenseType(), unknownType);
        assertEquals(convertedExpense.getDate(), LocalDateTime.parse(VALID_DATETIME));
    }

    @Test
    public void toModelType_emptyItem_throwsIllegalArgumentException() {
        XmlAdaptedExpense testExpense = new XmlAdaptedExpense("", 1, VALID_DATETIME);
        Assert.assertThrows(IllegalArgumentException.class,
                ExpenseType.MESSAGE_NUMBER_EMPTY, () -> testExpense.toModelType(VALID_MENU));
    }

    @Test
    public void toModelType_invalidDatetime_throwsDateTimeParseException() {
        XmlAdaptedExpense testExpense = new XmlAdaptedExpense(VALID_ITEM, 1, INVALID_DATETIME);
        Assert.assertThrows(DateTimeParseException.class, () -> testExpense.toModelType(VALID_MENU));
    }
}
