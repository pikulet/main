package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Guest;
import seedu.address.model.room.Capacity;
import seedu.address.model.room.DoubleRoom;
import seedu.address.model.room.Expenses;
import seedu.address.model.room.Reservations;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.SingleRoom;
import seedu.address.model.room.SuiteRoom;
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
    private String expenses;
    @XmlElement
    private String reservations;
    @XmlElement
    private List<Guest> guests = new ArrayList();
    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs an XmlAdaptedPerson.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedRoom() {}

    /**
     * Constructs an {@code XmlAdaptedPerson} with the given guest details.
     */
    public XmlAdaptedRoom(String roomNumber, String capacity, String expenses,
                          String reservations, List<Guest> guests, List<XmlAdaptedTag> tagged) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.expenses = expenses;
        this.reservations = reservations;

        if (guests != null) {
            this.guests = new ArrayList<>(guests);
        }
        if (tagged != null) {
            this.tagged = new ArrayList<>(tagged);
        }
    }

    /**
     * Converts a given Guest into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedPerson
     */
    public XmlAdaptedRoom(Room source) {
        roomNumber = source.getRoomNumber().toString();
        capacity = source.getCapacity().toString();
        expenses = source.getExpenses().toString();
        reservations = source.getReservations().toString();
        guests = source.getOccupant();
        tagged = source.getTags().stream()
                .map(XmlAdaptedTag::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this jaxb-friendly adapted guest object into the model's Guest object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted guest
     */
    public Room toModelType() throws IllegalValueException {
        final List<Tag> roomTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            roomTags.add(tag.toModelType());
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

        final Expenses modelExpenses = new Expenses();

        final Reservations modelReservations = new Reservations();

        final Set<Tag> modelTags = new HashSet<>(roomTags);

        switch(Integer.parseInt(capacity)) {
        case 1:
            return new SingleRoom(modelRoomNumber, guests, modelExpenses, modelReservations);

        case 2:
            return new DoubleRoom(modelRoomNumber, guests, modelExpenses, modelReservations);

        case 5:
            return new SuiteRoom(modelRoomNumber, guests, modelExpenses, modelReservations);

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
