package seedu.address.testutil;

import seedu.address.model.room.booking.BookingPeriod;

/**
 * A utility class containing a list of {@code BookingPeriod} objects to be used in tests.
 */
public class TypicalBookingPeriods {
    public static final BookingPeriod JAN09JAN10 = new BookingPeriod("09/01/19", "10/01/19");
    public static final BookingPeriod JAN09JAN11 = new BookingPeriod("09/01/19", "11/01/19");
    public static final BookingPeriod JAN10JAN11 = new BookingPeriod("10/01/19", "11/01/19");
    public static final BookingPeriod FEB09FEB10 = new BookingPeriod("09/02/19", "10/02/19");
    public static final BookingPeriod FEB27FEB28 = new BookingPeriod("27/02/19", "28/02/19");
    public static final BookingPeriod MAR30MAR31 = new BookingPeriod("30/03/19", "31/03/19");
}
