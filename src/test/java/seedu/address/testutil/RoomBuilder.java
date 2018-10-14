package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.expenses.Expenses;
import seedu.address.model.room.Capacity;
import seedu.address.model.room.DoubleRoom;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.SingleRoom;
import seedu.address.model.room.SuiteRoom;
import seedu.address.model.room.booking.Bookings;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Room objects.
 */
public class RoomBuilder {

    public static final String DEFAULT_ROOM_NUMBER = "001";
    public static final int DEFAULT_CAPACITY = 1;

    private RoomNumber roomNumber;
    private Capacity capacity;
    private Expenses expenses;
    private Bookings bookings;
    private Set<Tag> tags;

    public RoomBuilder() {
        roomNumber = new RoomNumber(DEFAULT_ROOM_NUMBER);
        capacity = new Capacity(DEFAULT_CAPACITY);
        expenses = new Expenses();
        bookings = new Bookings();
        tags = new HashSet<>();
    }

    /**
     * Initializes the RoomBuilder with the data of {@code roomToCopy}.
     */
    public RoomBuilder(Room roomToCopy) {
        roomNumber = roomToCopy.getRoomNumber();
        capacity = roomToCopy.getCapacity();
        expenses = roomToCopy.getExpenses();
        bookings = roomToCopy.getBookings();
        tags = new HashSet<>(roomToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Room} that we are building.
     */
    public RoomBuilder withRoomNumber(String roomNumber) {
        this.roomNumber = new RoomNumber(roomNumber);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Room} that we are building.
     */
    public RoomBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Room} that we are building.
     */
    public RoomBuilder withCapacity(int capacity) {
        this.capacity = new Capacity(capacity);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Room} that we are building.
     */
    public RoomBuilder withExpenses(Expenses expenses) {
        this.expenses = new Expenses(expenses);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Room} that we are building.
     */
    public RoomBuilder withBookings(Bookings bookings) {
        this.bookings = new Bookings(bookings);
        return this;
    }

    /**
     * Builds the room type depending on the capacity
     */
    public Room build() {
        switch(capacity.getValue()) {
        case 1:
            return new SingleRoom(this.roomNumber, this.expenses, this.bookings, this.tags);
        case 2:
            return new DoubleRoom(this.roomNumber, this.expenses, this.bookings, this.tags);
        case 5:
            return new SuiteRoom(this.roomNumber, this.expenses, this.bookings, this.tags);
        default:
            throw new IllegalArgumentException(Capacity.MESSAGE_CAPACITY_CONSTRAINTS);
        }
    }
}
