package seedu.address.testutil;

import seedu.address.model.room.UniqueRoomList;

/**
 * A utility class containing a list of {@code Room} objects to be used in tests.
 */
public class TypicalRooms {
    /**
     * Returns an {@code UniqueRoomList} with all the typical rooms.
     */
    public static UniqueRoomList getTypicalUniqueRoomList() {
        UniqueRoomList rooms = new UniqueRoomList();
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_001, TypicalBookings.JAN09_JAN10);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_002, TypicalBookings.JAN09_JAN11);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_010, TypicalBookings.JAN10_JAN11);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_020, TypicalBookings.FEB09_FEB10);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_025, TypicalBookings.FEB27_FEB28);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_035, TypicalBookings.MAR30_MAR31);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_072, TypicalBookings.TODAY_TOMORROW);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_088, TypicalBookings.TODAY_NEXTWEEK);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_099, TypicalBookings.TOMORROW_NEXTWEEK);
        return rooms;
    }
}
