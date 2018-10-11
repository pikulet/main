package seedu.address.model.room;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Guest;


/**
 * Represents a Booking of a room in the hotel.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {

    // Identity fields
    private final Guest guest;
    private final BookingPeriod bookingPeriod;

    /**
     * Every field must be present and not null.
     */
    public Booking(Guest guest, BookingPeriod bookingPeriod) {
        requireAllNonNull(guest, bookingPeriod);
        this.guest = guest;
        this.bookingPeriod = bookingPeriod;
    }

    public Guest getGuest() {
        return guest;
    }

    public BookingPeriod getBookingPeriod() {
        return bookingPeriod;
    }

    /**
     * Checks if the {@code Booking} overlaps with the other.
     */
    public boolean isOverlapping(Booking other) {
        return bookingPeriod.isOverlapping(other.getBookingPeriod());
    }

    /**
     * Returns true if both bookings have the same identity and data fields.
     * This defines a stronger notion of equality between two bookings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Booking)) {
            return false;
        }

        Booking otherBooking = (Booking) other;
        return otherBooking.getGuest().equals(getGuest())
                && otherBooking.getBookingPeriod().equals(getBookingPeriod());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(guest, bookingPeriod);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Guest: ")
                .append(getGuest())
                .append(" BookingPeriod: ")
                .append(getBookingPeriod());
        return builder.toString();
    }

}
