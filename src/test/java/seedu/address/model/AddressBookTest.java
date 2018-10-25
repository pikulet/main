package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalConcierge.getTypicalConcierge;
import static seedu.address.testutil.TypicalGuests.ALICE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.expenses.ExpenseType;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.exceptions.DuplicateGuestException;
import seedu.address.model.room.Room;
import seedu.address.testutil.GuestBuilder;

public class ConciergeTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final Concierge addressBook = new Concierge();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getGuestList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        addressBook.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyConcierge_replacesData() {
        Concierge newData = getTypicalConcierge();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateGuests_throwsDuplicateGuestException() {
        // Two guests with the same identity fields
        Guest editedAlice = new GuestBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Guest> newGuests = Arrays.asList(ALICE, editedAlice);

        ConciergeStub newData = new ConciergeStub(newGuests, null);

        thrown.expect(DuplicateGuestException.class);
        addressBook.resetData(newData);
    }

    @Test
    public void hasGuest_nullGuest_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        addressBook.hasGuest(null);
    }

    @Test
    public void hasGuest_guestNotInConcierge_returnsFalse() {
        assertFalse(addressBook.hasGuest(ALICE));
    }

    @Test
    public void hasGuest_guestInConcierge_returnsTrue() {
        addressBook.addGuest(ALICE);
        assertTrue(addressBook.hasGuest(ALICE));
    }

    @Test
    public void hasGuest_guestWithSameIdentityFieldsInConcierge_returnsTrue() {
        addressBook.addGuest(ALICE);
        Guest editedAlice = new GuestBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasGuest(editedAlice));
    }

    @Test
    public void getGuestList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        addressBook.getGuestList().remove(0);
    }

    @Test
    public void getMenuMap_modifyMap_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        addressBook.getMenuMap().put("1", new ExpenseType("1", "-", 0));
    }

    /**
     * A stub ReadOnlyConcierge whose guests list can violate interface constraints.
     */
    private static class ConciergeStub implements ReadOnlyConcierge {
        private final ObservableList<Guest> guests = FXCollections.observableArrayList();
        private final ObservableList<Room> rooms = FXCollections.observableArrayList();

        ConciergeStub(Collection<Guest> guests, Collection<Room> rooms) {
            this.guests.setAll(guests);
        }

        @Override
        public ObservableList<Guest> getGuestList() {
            return guests;
        }

        @Override
        public ObservableList<Room> getRoomList() {
            return rooms;
        }

        @Override
        public Menu getMenu() {
            return new Menu();
        }

        @Override
        public Map<String, ExpenseType> getMenuMap() {
            return new HashMap<>();
        }
    }

}
