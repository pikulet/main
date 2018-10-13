package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expenses.Expense;
import seedu.address.model.person.Guest;
import seedu.address.model.room.Capacity;
import seedu.address.model.room.DoubleRoom;
import seedu.address.model.room.Reservations;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.SingleRoom;
import seedu.address.model.room.SuiteRoom;
import seedu.address.model.expenses.Expenses;
import seedu.address.model.tag.Tag;

/**
 * JAXB-friendly version of the Room.
 */
public class XmlAdaptedRoom {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Room's %s field is missing!";

    @XmlElement(required = true)
    private String roomNumber;
    @XmlElement(required = true)
    private String capacity;

    @XmlElement
    private List<XmlAdaptedExpense> expenses;
    @XmlElement
    private String reservations;
    @XmlElement
    private List<XmlAdaptedPerson> guests = new ArrayList<>();
    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedRoom.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedRoom() {}

    /**
     * Constructs an {@code XmlAdaptedRoom} with the given room details.
     */
    public XmlAdaptedRoom(String roomNumber, String capacity, List<XmlAdaptedExpense> expenses,
                          String reservations, List<XmlAdaptedPerson> guests, List<XmlAdaptedTag> tagged) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.reservations = reservations;

        if (guests != null) {
            this.guests = new ArrayList<>(guests);
        }
        if (tagged != null) {
            this.tagged = new ArrayList<>(tagged);
        }
        if (expenses != null) {
            this.expenses = new ArrayList<>(expenses);
        }
    }

    /**
     * Converts a given room into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedRoom
     */
    public XmlAdaptedRoom(Room source) {
        roomNumber = source.getRoomNumber().toString();
        capacity = source.getCapacity().toString();
        expenses = source.getExpensesList().stream()
                .map(XmlAdaptedExpense::new)
                .collect(Collectors.toList());
        reservations = source.getReservations().toString();
        guests = source.getOccupant().stream()
                .map(XmlAdaptedPerson::new)
                .collect(Collectors.toList());
        tagged = source.getTags().stream()
                .map(XmlAdaptedTag::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this jaxb-friendly adapted room object into the model's room object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted room
     */
    public Room toModelType() throws IllegalValueException {
        final List<Tag> roomTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            roomTags.add(tag.toModelType());
        }

        final List<Expense> expenseList = new ArrayList<>();
        for (XmlAdaptedExpense expense : expenses) {
            expenseList.add(expense.toModelType());
        }

        final List<Guest> modelGuests = new ArrayList<>();
        for (XmlAdaptedPerson guest : guests) {
            modelGuests.add(guest.toModelType());
        }

        if (roomNumber == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName()));
        }
        final RoomNumber modelRoomNumber = new RoomNumber(roomNumber);

        if (capacity == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Capacity.class.getSimpleName()));
        }
        final Capacity modelCapacity = new Capacity(Integer.parseInt(capacity));

        final Expenses modelExpenses = new Expenses(expenseList);

        final Reservations modelReservations = new Reservations();

        final Set<Tag> modelTags = new HashSet<>(roomTags);

        switch(Integer.parseInt(capacity)) {
        case 1:
            return new SingleRoom(modelRoomNumber, modelGuests, modelExpenses, modelReservations);

        case 2:
            return new DoubleRoom(modelRoomNumber, modelGuests, modelExpenses, modelReservations);

        case 5:
            return new SuiteRoom(modelRoomNumber, modelGuests, modelExpenses, modelReservations);

        default:
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Capacity.class.getSimpleName()));
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedRoom)) {
            return false;
        }

        XmlAdaptedRoom otherRoom = (XmlAdaptedRoom) other;
        return Objects.equals(roomNumber, otherRoom.roomNumber)
                && Objects.equals(capacity, otherRoom.capacity)
                && Objects.equals(expenses, otherRoom.expenses)
                && Objects.equals(reservations, otherRoom.reservations)
                && tagged.equals(otherRoom.tagged);
    }
}
