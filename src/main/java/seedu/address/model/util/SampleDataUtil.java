package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.expenses.ExpenseType;
import seedu.address.model.expenses.Expenses;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.UniqueRoomList;
import seedu.address.model.room.booking.Booking;
import seedu.address.model.room.booking.BookingPeriod;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Guest[] getSamplePersons() {
        return new Guest[] {
            new Guest(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Guest(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Guest(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Guest(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Guest(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Guest(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    /**
     * Returns a room list initialized with the maximum number of rooms as set in RoomNumber class
     */
    public static UniqueRoomList getSampleRooms() {
        return new UniqueRoomList(RoomNumber.MAX_ROOM_NUMBER);
    }

    /**
     * Returns a room list initialized with the maximum number of rooms, and 1 sample booking for testing
     * DELETE WHEN TESTED IN UNIT TESTS
     */
    public static UniqueRoomList getSampleRoomsWithSampleBooking() {
        UniqueRoomList roomList = new UniqueRoomList(RoomNumber.MAX_ROOM_NUMBER);
        roomList.addBooking(new RoomNumber("001"),
            new Booking(getSamplePersons()[0],
            new BookingPeriod("01/01/2019", "02/01/2019")));
        return roomList;
    }

    public static Map<String, ExpenseType> getSampleMenuMap() {
        HashMap<String, ExpenseType> sampleMenuMap = new HashMap<>();
        sampleMenuMap.put("RS01", new ExpenseType("RS01", "Room service: Red wine", 50));
        sampleMenuMap.put("RS02", new ExpenseType("RS02", "Room service: Beef steak", 70));
        sampleMenuMap.put("RS03", new ExpenseType("RS03", "Room service: Thai massage", 100));
        sampleMenuMap.put("SP01", new ExpenseType("SP01", "Swimming pool: Entry", 5));
        sampleMenuMap.put("MB01", new ExpenseType("MB01", "Minibar: Coca cola", 3));
        sampleMenuMap.put("MB02", new ExpenseType("MB02", "Minibar: Sprite", 3));
        sampleMenuMap.put("MB03", new ExpenseType("MB03", "Minibar: Tiger beer", 6));
        sampleMenuMap.put("MB04", new ExpenseType("MB04", "Minibar: Mineral water", 3));
        return sampleMenuMap;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Guest sampleGuest : getSamplePersons()) {
            sampleAb.addPerson(sampleGuest);
        }
        sampleAb.setRooms(getSampleRooms().asUnmodifiableObservableList());
        for (Room sampleRoom : getSampleRooms()) {
            sampleAb.addRoom(sampleRoom);
        }
        sampleAb.setMenu(getSampleMenuMap());
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
