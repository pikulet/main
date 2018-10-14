package seedu.address.model.room;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAPACITY_DOUBLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_074;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HANDICAP;
import static seedu.address.testutil.TypicalRooms.DOUBLE_002;
import static seedu.address.testutil.TypicalRooms.SINGLE_001;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.room.booking.exceptions.NoActiveBookingException;
import seedu.address.model.room.exceptions.OccupiedRoomCheckinException;
import seedu.address.testutil.RoomBuilder;
import seedu.address.testutil.TypicalBookings;

public class RoomTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public Room testRoomWithTodayBooking;
    public Room testRoomWithTomorrowBooking;
    public Room testRoomWithoutBooking;
    
    @Before
    public void initialize() {
        testRoomWithTodayBooking = new RoomBuilder()
            .withBookings(TypicalBookings.getTypicalBookingsTodayTomorrow()).build();
        testRoomWithTomorrowBooking = new RoomBuilder()
            .withBookings(TypicalBookings.getTypicalBookingsTomorrowNextWeek()).build();
        testRoomWithoutBooking = new RoomBuilder().build();
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Room room = new RoomBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
        room.getTags().remove(0);
    }

    @Test
    public void checkIn_success() {
        testRoomWithTodayBooking.checkIn();
        assertTrue(testRoomWithTodayBooking.isCheckedIn());
    }

    @Test
    public void checkIn_noBooking_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        testRoomWithoutBooking.checkIn();
    }

    @Test
    public void checkIn_noActiveBooking_throwsNoActiveBookingException() {
        thrown.expect(NoActiveBookingException.class);
        testRoomWithTomorrowBooking.checkIn();
    }

    @Test
    public void checkIn_occupiedRoomCheckin_throwsOccupiedRoomCheckinException() {
        testRoomWithTodayBooking.checkIn();
        thrown.expect(OccupiedRoomCheckinException.class);
        testRoomWithTodayBooking.checkIn();
    }

    @Test
    public void isSameRoom() {
        // same object -> returns true
        assertTrue(SINGLE_001.isSameRoom(SINGLE_001));

        // null -> returns false
        assertFalse(SINGLE_001.isSameRoom(null));

        // different room number -> returns false
        Room editedSingle001 = new RoomBuilder(SINGLE_001).withRoomNumber("002").build();
        assertFalse(SINGLE_001.isSameRoom(editedSingle001));

        // same room number, different capacity -> returns true
        editedSingle001 = new RoomBuilder(SINGLE_001).withCapacity(VALID_CAPACITY_DOUBLE).build();
        assertTrue(SINGLE_001.isSameRoom(editedSingle001));

        /* KIV Expenses
        // same room number, different expenses -> returns true
        editedSingle001 = new RoomBuilder(SINGLE_001).withExpenses(new Expenses()).build();
        assertTrue(SINGLE_001.isSameRoom(editedSingle001));
        */

        // same room number, different bookings -> returns true
        editedSingle001 = new RoomBuilder(SINGLE_001).withBookings(TypicalBookings.getTypicalBookingsTodayTomorrow()).build();
        assertTrue(SINGLE_001.isSameRoom(editedSingle001));

        // same room number, different tags -> return true
        editedSingle001 = new RoomBuilder(SINGLE_001).withTags(VALID_TAG_HANDICAP).build();
        assertTrue(SINGLE_001.isSameRoom(editedSingle001));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Room roomCopy = new RoomBuilder(SINGLE_001).build();
        assertTrue(SINGLE_001.equals(roomCopy));

        // same object -> returns true
        assertTrue(SINGLE_001.equals(SINGLE_001));

        // null -> returns false
        assertFalse(SINGLE_001.equals(null));

        // different type -> returns false
        assertFalse(SINGLE_001.equals(5));

        // different guest -> returns false
        assertFalse(SINGLE_001.equals(DOUBLE_002));

        // different room number -> returns false
        Room editedSingle001 = new RoomBuilder(SINGLE_001).withRoomNumber(VALID_ROOM_NUMBER_074).build();
        assertFalse(SINGLE_001.equals(editedSingle001));

        // different capacity -> returns false
        editedSingle001 = new RoomBuilder(SINGLE_001).withCapacity(VALID_CAPACITY_DOUBLE).build();
        assertFalse(SINGLE_001.equals(editedSingle001));

        /* KIV Expenses
        // different capacity -> returns false
        editedSingle001 = new RoomBuilder(SINGLE_001).withExpenses(new Expenses()).build();
        assertFalse(SINGLE_001.equals(editedSingle001));
        */

        // different bookings -> returns false
        editedSingle001 = new RoomBuilder(SINGLE_001).withBookings(TypicalBookings.getTypicalBookingsTodayTomorrow()).build();
        assertFalse(SINGLE_001.equals(editedSingle001));

        // different tags -> returns false
        editedSingle001 = new RoomBuilder(SINGLE_001).withTags(VALID_TAG_HANDICAP).build();
        assertFalse(SINGLE_001.equals(editedSingle001));
    }
}
