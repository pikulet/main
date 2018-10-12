package seedu.address.model.room;

import java.util.List;

import seedu.address.model.room.booking.Bookings;
import seedu.address.model.person.Guest;

/**
 * Represents a Single Room in the hotel.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class SingleRoom extends Room {
    public static final Capacity CAPACITY_SINGLE_ROOM = new Capacity(1);

    /**
     * All parameters must be non-null.
     * Note: {@code expenses}, or {@code bookings} may be empty, but not null.
     */
    public SingleRoom(RoomNumber roomNumber, Expenses expenses, Bookings bookings) {
        super(roomNumber, CAPACITY_SINGLE_ROOM, expenses, bookings);
    }
}
