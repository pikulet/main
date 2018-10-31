package seedu.address.model.util;

/**
 * Represents an error when hashing a password
 */
public class HashingException extends Exception {

    public HashingException() {
        super("Error occurred during the hashing of the supplied password.");
    }

}
