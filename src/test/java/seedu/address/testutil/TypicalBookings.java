package seedu.address.testutil;

import seedu.address.model.room.booking.Booking;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {
    public static final Booking JAN09_JAN10 = new Booking(TypicalPersons.ALICE,
        TypicalBookingPeriods.JAN09_JAN10);

    public static final Booking JAN09_JAN11 = new Booking(TypicalPersons.BENSON,
        TypicalBookingPeriods.JAN09_JAN11);

    public static final Booking JAN10_JAN11 = new Booking(TypicalPersons.CARL,
        TypicalBookingPeriods.JAN10_JAN11);

    public static final Booking FEB09_FEB10 = new Booking(TypicalPersons.DANIEL,
        TypicalBookingPeriods.FEB09_FEB10);

    public static final Booking FEB27_FEB28 = new Booking(TypicalPersons.FIONA,
        TypicalBookingPeriods.FEB27_FEB28);

    public static final Booking MAR30_MAR31 = new Booking(TypicalPersons.ELLE,
        TypicalBookingPeriods.MAR30_MAR31);
    
    public static final Booking TODAY_TOMORROW = new Booking(TypicalPersons.GEORGE,
        TypicalBookingPeriods.TODAY_TOMORROW);

    public static final Booking TODAY_NEXTWEEK = new Booking(TypicalPersons.GEORGE,
        TypicalBookingPeriods.TODAY_NEXTWEEK);

    public static final Booking TOMORROW_NEXTWEEK = new Booking(TypicalPersons.GEORGE,
        TypicalBookingPeriods.TOMORROW_NEXTWEEK);
}
