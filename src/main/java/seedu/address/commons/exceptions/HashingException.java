package seedu.address.commons.exceptions;

/**
 * Represents an error when hashing a password
 */
public class HashingException extends Exception {

    public HashingException() {
        super("Error occurred during the hashing of the supplied password.");
    }

}
