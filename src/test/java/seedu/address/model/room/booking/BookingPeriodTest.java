package seedu.address.model.room.booking;

import org.junit.Test;
import seedu.address.testutil.Assert;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookingPeriodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new BookingPeriod(null, null));
    }

    @Test
    public void constructor_invalidBookingPeriod_throwsIllegalArgumentException() {
        String invalidStartDate = "32/01/18";
        String invalidEndDate = "31/02/18";
        Assert.assertThrows(IllegalArgumentException.class, () -> new BookingPeriod(invalidStartDate, invalidEndDate));
    }

    @Test
    public void isValidBookingPeriod() {
        // null booking period
        Assert.assertThrows(NullPointerException.class, () -> BookingPeriod.isValidBookingPeriod(null, null));

        // invalid booking periods
        assertFalse(BookingPeriod.isValidBookingPeriod("", "")); // empty string
        assertFalse(BookingPeriod.isValidBookingPeriod(" ", " ")); // spaces only
        assertFalse(BookingPeriod.isValidBookingPeriod("32/01/18", "01/02/18")); // day not between 1 and 31
        assertFalse(BookingPeriod.isValidBookingPeriod("31/01/18", "31/02/18")); // not possible date
        assertFalse(BookingPeriod.isValidBookingPeriod("310118", "010218")); // wrong date format
        assertFalse(BookingPeriod.isValidBookingPeriod("31Jan2018", "01Feb2018")); // alphabets in date
        assertFalse(BookingPeriod.isValidBookingPeriod("31 /01/18", "01/02/18")); // spaces in date

        // valid booking periods
        assertTrue(BookingPeriod.isValidBookingPeriod("01/01/18", "02/01/18")); // valid format and correct dates
        assertTrue(BookingPeriod.isValidBookingPeriod("27/02/18", "28/02/18"));
        assertTrue(BookingPeriod.isValidBookingPeriod("30/12/18", "31/12/18"));
    }
}
