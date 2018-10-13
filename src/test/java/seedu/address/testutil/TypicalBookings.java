package seedu.address.testutil;

import seedu.address.model.room.booking.Booking;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {
    public static final Booking JAN09JAN10 = new Booking(TypicalPersons.ALICE,
        TypicalBookingPeriods.JAN09JAN10);

    public static final Booking JAN09JAN11 = new Booking(TypicalPersons.BENSON,
        TypicalBookingPeriods.JAN09JAN11);

    public static final Booking JAN10JAN11 = new Booking(TypicalPersons.CARL,
        TypicalBookingPeriods.JAN10JAN11);

    public static final Booking FEB09FEB10 = new Booking(TypicalPersons.DANIEL,
        TypicalBookingPeriods.FEB09FEB10);

    public static final Booking FEB27FEB28 = new Booking(TypicalPersons.FIONA,
        TypicalBookingPeriods.FEB27FEB28);

    public static final Booking MAR30MAR31 = new Booking(TypicalPersons.ELLE,
        TypicalBookingPeriods.MAR30MAR31);
}
