package seedu.address.model.room;

/**
 * Represents a Suite Room in the hotel.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class SuiteRoom extends Room {
    public static final Capacity CAPACITY_SUITE_ROOM = new Capacity(5);

    /**
     * All parameters must be non-null.
     */
    public SuiteRoom(RoomNumber roomNumber) {
        super(roomNumber, CAPACITY_SUITE_ROOM);
    }

    public SuiteRoom(SuiteRoom suiteRoom) {
        super(suiteRoom);
    }

    @Override
    public SuiteRoom cloneRoom() {
        return new SuiteRoom(this);
    }
}
