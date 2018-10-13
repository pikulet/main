package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Guest;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;
import seedu.address.model.room.booking.Booking;
import seedu.address.model.room.booking.BookingPeriod;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Room} objects to be used in tests.
 */
public class TypicalRooms {
    /**
     * Returns an {@code UniqueRoomList} with all the typical rooms.
     */
    public static UniqueRoomList getTypicalUniqueRoomList() {
        UniqueRoomList rooms = new UniqueRoomList();
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_001, TypicalBookings.JAN09JAN10);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_002, TypicalBookings.JAN09JAN11);
        rooms.addBooking(TypicalRoomNumbers.ROOM_NUMBER_010, TypicalBookings.FEB27FEB28);
        return rooms;
    }
}
