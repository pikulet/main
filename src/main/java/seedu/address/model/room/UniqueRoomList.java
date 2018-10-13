package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.room.booking.Booking;
import seedu.address.model.room.booking.Bookings;
import seedu.address.model.room.exceptions.DuplicateRoomException;
import seedu.address.model.room.exceptions.RoomNotFoundException;

/**
 * A list of rooms that enforces uniqueness between its elements and does not allow nulls.
 * A room is considered unique by comparing using {@code Room#isSameRoom(Room)}. As such, adding and updating of
 * rooms uses Room#isSameRoom(Room) for equality so as to ensure that the room being added or updated is
 * unique in terms of identity in the UniqueRoomList. A room can be removed by passing either its reference or
 * room number to the remove() method.
 *
 * Supports a minimal set of list operations.
 *
 * @see Room#isSameRoom(Room)
 */
public class UniqueRoomList implements Iterable<Room> {

    private final ObservableList<Room> internalList = FXCollections.observableArrayList();

    /**
     * Initializes UniqueRoomList with list of rooms ranging from 001 up to the maximum room number.
     */
    public UniqueRoomList() {
        this.internalList.setAll(Stream.iterate(1, i -> i <= Integer.parseInt(RoomNumber.MAX_ROOM_NUMBER), i -> i + 1)
            .map(i -> {
                RoomNumber roomNumber = new RoomNumber(String.format("%03d", i));;
                Expenses expenses = new Expenses();
                Bookings bookings = new Bookings();
                if (i % 10 == 0) { // All rooms with room number that is multiple of 10 is a SuiteRoom.
                    return new SuiteRoom(roomNumber, expenses, bookings);
                }
                if (i % 2 == 0) { // All rooms with even room number is a DoubleRoom.
                    return new DoubleRoom(roomNumber, expenses, bookings);
                }
                // ALl rooms with odd room number is a SingleRoom.
                return new SingleRoom(roomNumber, expenses, bookings);
            })
            .collect(Collectors.toList()));
    }

    /**
     * Returns true if the list contains an equivalent room as the given argument.
     */
    public boolean contains(Room toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRoom);
    }

    /**
     * Adds a room to the list.
     * The room must not already exist in the list.
     */
    public void add(Room toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRoomException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent room from the list.
     * The room must exist in the list.
     */
    public void remove(Room toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RoomNotFoundException();
        }
    }

    /**
     * Removes the room corresponding to the room number
     * The room must exist in the list.
     */
    public void remove(RoomNumber toRemove) {
        requireNonNull(toRemove);
        for (Room room : internalList) {
            if (room.getRoomNumber().equals(toRemove)) {
                internalList.remove(room);
                return;
            }
        }
        throw new RoomNotFoundException();
    }

    /**
     * Replaces the room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in the list.
     * The room identity of {@code editedRoom} must not be the same as another existing room in the list.
     */
    public void setRoom(Room target, Room editedRoom) {
        requireAllNonNull(target, editedRoom);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RoomNotFoundException();
        }

        if (!target.isSameRoom(editedRoom) && contains(editedRoom)) {
            throw new DuplicateRoomException();
        }

        internalList.set(index, editedRoom);
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     */
    public void setRooms(UniqueRoomList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms.
     */
    public void setRooms(List<Room> rooms) {
        requireAllNonNull(rooms);
        if (!roomsAreUnique(rooms)) {
            throw new DuplicateRoomException();
        }

        internalList.setAll(rooms);
    }

    /**
     * Add a booking to a room identified by its room number.
     */
    public void addBooking(RoomNumber roomNumber, Booking booking) {
        internalList.get(roomNumber.getRoomNumberAsIndex().getZeroBased())
            .addBooking(booking);
    }

    /**
     * Returns true if the room identified by its room number is checked in.
     */
    public boolean isRoomCheckedIn(RoomNumber roomNumber) {
        return internalList.get(roomNumber.getRoomNumberAsIndex().getZeroBased()).isCheckedIn();
    }

    /**
     * Returns true if the room's first booking is active.
     */
    public boolean isRoomBookingActive(RoomNumber roomNumber) {
        return internalList.get(roomNumber.getRoomNumberAsIndex().getZeroBased()).hasActiveBooking();
    }

    /**
     * Checks in the room using its room number
     */
    public void checkinRoom(RoomNumber roomNumber) {
        internalList.get(roomNumber.getRoomNumberAsIndex().getZeroBased()).checkIn();
    }

    /**
     * Checks out a room using its room number
     */
    public void checkoutRoom(RoomNumber roomNumber) {
        internalList.get(roomNumber.getRoomNumberAsIndex().getZeroBased()).checkout();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Room> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public Iterator<Room> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRoomList // instanceof handles nulls
                        && internalList.equals(((UniqueRoomList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code rooms} contains only unique rooms.
     */
    private boolean roomsAreUnique(List<Room> rooms) {
        for (int i = 0; i < rooms.size() - 1; i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                if (rooms.get(i).isSameRoom(rooms.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
