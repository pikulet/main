package seedu.address.testutil;

import seedu.address.model.room.Capacity;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;

/**
 * A utility class containing a list of {@code Room} objects to be used in tests.
 */
public class TypicalRooms {

    public static final Room SINGLE_001 = new RoomBuilder()
        .withRoomNumber(TypicalRoomNumbers.ROOM_NUMBER_001.toString()).withCapacity(Capacity.SINGLE)
        .build();

    public static final Room DOUBLE_002 = new RoomBuilder()
        .withRoomNumber(TypicalRoomNumbers.ROOM_NUMBER_002.toString()).withCapacity(Capacity.DOUBLE)
        .build();

    public static final Room SUITE_010 = new RoomBuilder()
        .withRoomNumber(TypicalRoomNumbers.ROOM_NUMBER_010.toString()).withCapacity(Capacity.SUITE)
        .build();

    public static final Room SINGLE_011 = new RoomBuilder()
        .withRoomNumber(TypicalRoomNumbers.ROOM_NUMBER_011.toString()).withCapacity(Capacity.SINGLE)
        .build();

    public static final Room DOUBLE_012 = new RoomBuilder()
        .withRoomNumber(TypicalRoomNumbers.ROOM_NUMBER_012.toString()).withCapacity(Capacity.DOUBLE)
        .build();

    public static final Room SUITE_020 = new RoomBuilder()
        .withRoomNumber(TypicalRoomNumbers.ROOM_NUMBER_020.toString()).withCapacity(Capacity.SUITE)
        .build();

    public static final Room SINGLE_023 = new RoomBuilder()
        .withRoomNumber(TypicalRoomNumbers.ROOM_NUMBER_023.toString()).withCapacity(Capacity.SINGLE)
        .build();

    public static final Room DOUBLE_024 = new RoomBuilder()
        .withRoomNumber(TypicalRoomNumbers.ROOM_NUMBER_024.toString()).withCapacity(Capacity.DOUBLE)
        .build();

    public static final Room SUITE_099 = new RoomBuilder()
        .withRoomNumber(TypicalRoomNumbers.ROOM_NUMBER_099.toString()).withCapacity(Capacity.SUITE)
        .build();

    /**
     * Returns a {@code UniqueRoomList} with all rooms.
     * Note: All rooms do not have bookings or expenses. Use @Before in tests to initialize bookings and expenses.
     */
    public static UniqueRoomList getTypicalUniqueRoomList() {
        return new UniqueRoomList();
    }
}
