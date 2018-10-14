package seedu.address.model.room;

/**
 * Represents a Single Room in the hotel.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class SingleRoom extends Room {
    public static final Capacity CAPACITY_SINGLE_ROOM = new Capacity(1);

    /**
     * All parameters must be non-null.
     */
    public SingleRoom(RoomNumber roomNumber) {
        super(roomNumber, CAPACITY_SINGLE_ROOM);
    }

    public SingleRoom(SingleRoom singleRoom) {
        super(singleRoom);
    }

    @Override
    public SingleRoom cloneRoom() {
        return new SingleRoom(this);
    }
}
