package seedu.address.model.room;

import seedu.address.model.person.Guest;
import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Reservation of a room in the hotel.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reservation {

    // Identity fields
    private final Guest registeredGuest;
    private final ReservationPeriod reservationPeriod;

    /**
     * Every field must be present and not null.
     */
    public Reservation(Guest registeredGuest, ReservationPeriod phone) {
        requireAllNonNull(registeredGuest, phone);
        this.registeredGuest = registeredGuest;
        this.reservationPeriod = phone;
    }

    public Guest getRegisteredGuest() {
        return registeredGuest;
    }

    public ReservationPeriod getReservationPeriod() {
        return reservationPeriod;
    }

    /**
     * Checks if the {@code Reservation} overlaps with the other.
     */
    public boolean isOverlapping(Reservation other) {
        return reservationPeriod.isOverlapping(other.getReservationPeriod());
    }

    /**
     * Returns true if both reservations have the same identity and data fields.
     * This defines a stronger notion of equality between two reservations.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Reservation)) {
            return false;
        }

        Reservation otherReservation = (Reservation) other;
        return otherReservation.getRegisteredGuest().equals(getRegisteredGuest())
                && otherReservation.getReservationPeriod().equals(getReservationPeriod());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(registeredGuest, reservationPeriod);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Guest: ")
                .append(getRegisteredGuest())
                .append(" ReservationPeriod: ")
                .append(getReservationPeriod());
        return builder.toString();
    }

}
