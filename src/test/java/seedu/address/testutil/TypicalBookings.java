package seedu.address.testutil;

import seedu.address.model.room.booking.Booking;
import seedu.address.model.room.booking.Bookings;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {
    public static final Booking TODAY_TOMORROW = new BookingBuilder()
        .withBookingPeriod(TypicalBookingPeriods.TODAY_TOMORROW).build();

    public static final Booking TODAY_NEXTWEEK = new BookingBuilder()
        .withBookingPeriod(TypicalBookingPeriods.TODAY_NEXTWEEK).build();

    public static final Booking TOMORROW_NEXTWEEK = new BookingBuilder()
        .withBookingPeriod(TypicalBookingPeriods.TOMORROW_NEXTWEEK).build();

    public static Bookings getTypicalBookings() {
        Bookings bookings = new Bookings();
        bookings.add(TODAY_TOMORROW);
        bookings.add(TOMORROW_NEXTWEEK);
        return bookings;
    }
}
