package seedu.address.testutil;

import seedu.address.model.room.DoubleRoom;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.SingleRoom;
import seedu.address.model.room.SuiteRoom;
import seedu.address.model.room.UniqueRoomList;

/**
 * A utility class containing a list of {@code Room} objects to be used in tests.
 */
public class TypicalRooms {

    private static final int CAPACITY_SINGLE_ROOM = SingleRoom.CAPACITY_SINGLE_ROOM.getValue();
    private static final int CAPACITY_DOUBLE_ROOM = DoubleRoom.CAPACITY_DOUBLE_ROOM.getValue();
    private static final int CAPACITY_SUITE_ROOM = SuiteRoom.CAPACITY_SUITE_ROOM.getValue();

    public static final Room SINGLE_001 = new RoomBuilder()
        .withRoomNumber("001").withCapacity(CAPACITY_SINGLE_ROOM)
        .build();

    public static final Room DOUBLE_002 = new RoomBuilder()
        .withRoomNumber("002").withCapacity(CAPACITY_DOUBLE_ROOM)
        .build();

    public static final Room SUITE_010 = new RoomBuilder()
        .withRoomNumber("010").withCapacity(CAPACITY_SUITE_ROOM)
        .build();

    public static final Room SINGLE_011 = new RoomBuilder()
        .withRoomNumber("011").withCapacity(CAPACITY_SINGLE_ROOM)
        .build();

    public static final Room DOUBLE_012 = new RoomBuilder()
        .withRoomNumber("012").withCapacity(CAPACITY_DOUBLE_ROOM)
        .build();

    public static final Room SUITE_020 = new RoomBuilder()
        .withRoomNumber("020").withCapacity(CAPACITY_SUITE_ROOM)
        .build();

    /**
     * Returns an {@code UniqueRoomList} with all the typical rooms.
     */
    public static UniqueRoomList getTypicalUniqueRoomList() {
        UniqueRoomList rooms = new UniqueRoomList(RoomNumber.MAX_ROOM_NUMBER);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_001_TODAY_TOMORROW, TypicalBookings.TODAY_TOMORROW);
        rooms.checkinRoom(TypicalRoomNumbers.ROOM_NUMBER_001_TODAY_TOMORROW);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_002_TODAY_NEXT_WEEK, TypicalBookings.TODAY_NEXTWEEK);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_099_TOMORROW_NEXT_WEEK, TypicalBookings.TOMORROW_NEXTWEEK);
        return rooms;
    }
}
