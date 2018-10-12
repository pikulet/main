package seedu.address.model.room;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.room.booking.Booking;
import seedu.address.model.room.booking.Bookings;
import seedu.address.model.person.Guest;
import seedu.address.model.room.exceptions.UnoccupiedRoomCheckoutException;
import seedu.address.model.tag.Tag;

/**
 * Represents a room in the hotel.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Room {

    // Identity fields
    protected final RoomNumber roomNumber;

    // Data fields
    protected final Capacity capacity;
    protected final Expenses expenses;
    protected final Bookings bookings;
    protected final Set<Tag> tags = new HashSet<>();

    /**
     * NEVER USE THIS DEFAULT CONSTRUCTOR.
     * I had to write this default constructor because Java necessitates that `final` fields must be
     * instantiated. Between choosing this or removing `final` access modifier, I decided to go with this because
     * the latter opens up the possibility for once-correct fields to be changed unintentionally.
     * If you can find a better alternative, please let me know and I will replace this stain immediately.
     */
    public Room() {
        this.roomNumber = new RoomNumber("001");
        this.capacity = new Capacity(1);
        this.expenses = new Expenses();
        this.bookings = new Bookings();
    }

    /**
     * All parameters must be non-null.
     * Note: {@code expenses}, or {@code bookings} may be empty, but not null.
     */
    protected Room(RoomNumber roomNumber, Capacity capacity, Expenses expenses, Bookings bookings) {
        requireAllNonNull(roomNumber, capacity, expenses, bookings);
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.expenses = expenses;
        this.bookings = bookings;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public Bookings getBookings() {
        return bookings;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if room has an active booking.
     */
    public boolean isOccupied() {
        return getBookings().hasActiveBooking();
    }

    /**
     * Returns true if both rooms of the same name have the same room number.
     * This defines a weaker notion of equality between two rooms.
     */
    public boolean isSameRoom(Room otherRoom) {
        if (otherRoom == this) {
            return true;
        }

        return otherRoom != null
            && otherRoom.getRoomNumber().equals(getRoomNumber());
    }

    /**
     * Returns true if the room number identifies this room.
     */
    public boolean isRoom(RoomNumber roomNumber) {
        return getRoomNumber().equals(roomNumber);
    }

    /**
     * Checks out this room and its occupant.
     * Future features to include exporting of receipt, setting room to housekeeping status for __x__ hours.
     */
    public void checkout() {
        if (!bookings.hasActiveBooking()) {
            throw new UnoccupiedRoomCheckoutException();
        }
        Booking activeBooking = bookings.getActiveBooking();
        Guest guest = activeBooking.getGuest();
        // guest.checkout(); // joyce implement this later

        bookings.remove(activeBooking);
        // expenses.report(); // weizheng implement this later
    }

    /**
     * Returns true if both rooms have the same identity and data fields.
     * This defines a stronger notion of equality between two rooms.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Room)) {
            return false;
        }

        Room otherRoom = (Room) other;
        return otherRoom.getRoomNumber().equals(getRoomNumber())
                && otherRoom.getCapacity().equals(getCapacity())
                && otherRoom.getBookings().equals(getBookings())
                && otherRoom.getExpenses().equals(getExpenses())
                && otherRoom.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(roomNumber, capacity, expenses, bookings, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Room: ")
                .append(getRoomNumber())
                .append(" Capacity: ")
                .append(getCapacity())
                .append(" Registered Guest: ");
        builder.append(" Bookings: ")
                .append(getBookings())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
