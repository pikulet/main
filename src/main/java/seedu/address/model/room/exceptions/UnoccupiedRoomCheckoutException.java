package seedu.address.model.room.exceptions;

/**
 * Signals that the operation is unable to find an active booking.
 */
public class UnoccupiedRoomCheckoutException extends RuntimeException {
    public UnoccupiedRoomCheckoutException() {
        super("Operation is attempting to checkout an unoccupied room.");
    }
}
