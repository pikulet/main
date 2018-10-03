package seedu.address.model.room;

/**
 * Empty javadoc comment.
 */
public class RoomNumber {

    public static final String MESSAGE_ROOM_NUMBER_CONSTRAINTS = "TEST_MESSAGE";
    private static final String ROOM_NUMBER_VALIDATION_REGEX =
            "^(0\\d[1-9]|0[1-9]\\d|100)$";

    public final String value;

    public RoomNumber(String trimmedRoomNumber) {
        this.value = trimmedRoomNumber;
    }

    public static boolean isValidRoomNumber(String test) {
        return test.matches(ROOM_NUMBER_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomNumber // instanceof handles nulls
                && value.equals(((RoomNumber) other).value)); // state check
    }
}
