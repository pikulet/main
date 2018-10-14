package seedu.address.testutil;

import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.UniqueRoomList;

/**
 * A utility class containing a list of {@code Room} objects to be used in tests.
 */
public class TypicalRooms {
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
