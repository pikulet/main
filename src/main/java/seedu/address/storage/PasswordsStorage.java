package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.util.InvalidPasswordException;

/**
 * Represents a storage for user passwords
 */
public interface PasswordsStorage {

    /**
     * Returns the file path of the passwords data file.
     */
    Path getPasswordsFilePath();

    /**
     * Checks the validity of a given {@code userName} and {@code
     * hashedPassword}.
     *
     * Return true if the password matches the username and false otherwise.
     */
    boolean checkPassword(String userName, String hashedPassword)
            throws IOException, InvalidPasswordException, DataConversionException;

}
