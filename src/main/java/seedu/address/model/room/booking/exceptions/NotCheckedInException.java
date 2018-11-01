package seedu.address.model.room.booking.exceptions;

/**
 * Exception to indicate that the room is not checked in.
 */
public class NotCheckedInException extends RuntimeException {
    public NotCheckedInException() {
        super("There are no guests checked in to the room.");
    }
}
