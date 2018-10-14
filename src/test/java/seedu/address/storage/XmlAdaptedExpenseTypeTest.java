package seedu.address.storage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import seedu.address.model.expenses.ExpenseType;
import seedu.address.testutil.Assert;

public class XmlAdaptedExpenseTypeTest {

    @Test
    public void toModelType_validArguments_returnsExpenseTypeObject() {
        String number = "1";
        double cost = 0;
        String name = "-";
        XmlAdaptedExpenseType testType = new XmlAdaptedExpenseType(number, name, cost);
        assertEquals(testType.toModelType(), new ExpenseType(number, name, cost));
    }

    @Test
    public void toModelType_emptyNumber_throwsIllegalArgumentException() {
        XmlAdaptedExpenseType testType = new XmlAdaptedExpenseType("", "-", 0);
        Assert.assertThrows(IllegalArgumentException.class, ExpenseType.MESSAGE_NUMBER_EMPTY, testType::toModelType);
    }

    @Test
    public void toModelType_emptyName_throwsIllegalArgumentException() {
        XmlAdaptedExpenseType testType = new XmlAdaptedExpenseType("1", "", 0);
        Assert.assertThrows(IllegalArgumentException.class, ExpenseType.MESSAGE_NAME_EMPTY, testType::toModelType);
    }
}
