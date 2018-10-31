package seedu.address.model.login;

import static seedu.address.model.login.InvalidLogInException.MESSAGE_INVALID_PASSWORD;
import static seedu.address.model.login.InvalidLogInException.MESSAGE_INVALID_USERNAME;

import java.util.Optional;

public class LogInHelper {

    private Optional<String> username = Optional.empty();
    private final PasswordHashList passwordRef;

    public LogInHelper() {
        this.passwordRef = PasswordHashList.getEmptyPasswordHashList();
    }

    public LogInHelper(PasswordHashList passwordRef) {
        this.passwordRef = passwordRef;
    }

    public boolean isSignedIn() {
        return this.username.isPresent();
    }

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
