package seedu.address.model.util;

/**
 * Signals that the password supplied does not match the user name.
 */
public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("The username or password is incorrect.");
    }
}
