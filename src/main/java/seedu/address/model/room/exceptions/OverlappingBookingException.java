package seedu.address.model.room.exceptions;

/**
 * Signals that the operation will result in overlapping Bookings
 */
public class OverlappingBookingException extends RuntimeException {
    public OverlappingBookingException() {
        super("Operation would result in overlapping booking dates.");
    }
}
