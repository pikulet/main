package seedu.address.testutil;

import seedu.address.model.room.booking.Booking;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {
    public static final Booking TODAY_TOMORROW = new Booking(TypicalPersons.GEORGE,
        TypicalBookingPeriods.TODAY_TOMORROW);

    public static final Booking TODAY_NEXTWEEK = new Booking(TypicalPersons.GEORGE,
        TypicalBookingPeriods.TODAY_NEXTWEEK);

    public static final Booking TOMORROW_NEXTWEEK = new Booking(TypicalPersons.GEORGE,
        TypicalBookingPeriods.TOMORROW_NEXTWEEK);
}
