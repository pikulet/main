package seedu.address.model.room;

import java.util.List;

import seedu.address.model.room.booking.Bookings;
import seedu.address.model.person.Guest;

/**
 * Represents a Suite Room in the hotel.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class SuiteRoom extends Room {
    public static final Capacity CAPACITY_SUITE_ROOM = new Capacity(5);

    /**
     * All parameters must be non-null.
     * Note: {@code expenses}, or {@code bookings} may be empty, but not null.
     */
    public SuiteRoom(RoomNumber roomNumber, Expenses expenses, Bookings bookings) {
        super(roomNumber, CAPACITY_SUITE_ROOM, expenses, bookings);
    }
}
