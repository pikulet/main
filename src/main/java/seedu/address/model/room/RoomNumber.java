package seedu.address.model.room;

/**
 * Empty javadoc comment.
 */
public class RoomNumber {

    public static final String MESSAGE_ROOM_NUMBER_CONSTRAINTS = "TEST_MESSAGE";

    public RoomNumber(String trimmedRoomNumber) {
    }

    public static boolean isValidRoomNumber(String trimmedRoomNumber) {
        return true;
    }
}
