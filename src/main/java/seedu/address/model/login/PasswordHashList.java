package seedu.address.model.login;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import seedu.address.commons.util.JsonUtil;

/**
 * Represents a password hash list, and is used in the {@code LogInHelper}.
 */
public class PasswordHashList {

    private JsonNode passwordRef; // The JSON password reference list

    public PasswordHashList(String passwordRefString) throws IOException {
        this.passwordRef = JsonUtil.getNodeObject(passwordRefString);
    }

    private PasswordHashList(JsonNode passwordRef) {
        this.passwordRef = passwordRef;
    }

    /**
     * @return An empty password hash list with no username or passwords
     */
    public static PasswordHashList getEmptyPasswordHashList() {
        return new PasswordHashList(JsonNodeFactory.instance.objectNode());
    }

    /**
     * Retrieves the password associated with the given username.
     * @param username The username which is the key
     * @return An Optional value which is empty if the username is not
     * present. Else, the optional will wrap the associated password.
     */
    public Optional<String> getExpectedPassword(String username) {
        return Optional.of(passwordRef.get(username).asText());
    }

}
