package seedu.address.model.login;

import static seedu.address.model.login.InvalidLogInException.MESSAGE_INVALID_PASSWORD;
import static seedu.address.model.login.InvalidLogInException.MESSAGE_INVALID_USERNAME;

import java.util.Optional;

/**
 * A helper class to manage the login process.
 */
public class LogInHelper {

    // The username currently associated with the model
    private Optional<String> username = Optional.empty();
    private final PasswordHashList passwordRef;

    /**
     * Creates an {@code LogInHelper} with an empty password reference list.
     */
    public LogInHelper() {
        this.passwordRef = PasswordHashList.getEmptyPasswordHashList();
    }

    public LogInHelper(PasswordHashList passwordRef) {
        this.passwordRef = passwordRef;
    }

    /**
     * Checks if the current {@code LogInHelper} is signed in.
     */
    public boolean isSignedIn() {
        return this.username.isPresent();
    }

    /**
     * Attempts to sign in with the given {@code username} and the
     * {@code hashed password}.
     *
     * @throws InvalidLogInException Thrown when the username is not present,
     * or the password does not match the username.
     */
    public void signIn(String username, String hashedPassword) throws InvalidLogInException {
        Optional<String> expectedPassword = passwordRef.getExpectedPassword(username);

        if (!expectedPassword.isPresent()) {
            throw new InvalidLogInException(MESSAGE_INVALID_USERNAME);
        } else if (!hashedPassword.equals(expectedPassword)) {
            throw new InvalidLogInException(MESSAGE_INVALID_PASSWORD);
        }

        this.username = Optional.of(username);
    }
}
