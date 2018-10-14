package seedu.address.model.room;

/**
 * Represents a Double Room in the hotel.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class DoubleRoom extends Room {
    public static final Capacity CAPACITY_DOUBLE_ROOM = new Capacity(2);

    /**
     * All parameters must be non-null.
     */
    public DoubleRoom(RoomNumber roomNumber) {
        super(roomNumber, CAPACITY_DOUBLE_ROOM);
    }

    public DoubleRoom(DoubleRoom doubleRoom) {
        super(doubleRoom);
    }

    @Override
    public DoubleRoom cloneRoom() {
        return new DoubleRoom(this);
    }
}
