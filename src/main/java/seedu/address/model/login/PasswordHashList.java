package seedu.address.model.login;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Represents a password hash list, and is used in the {@code LogInHelper}.
 */
public class PasswordHashList {

    private static final String PASSWORD_REF_NAME = "passwordRef";
    private JsonNode passwordRef; // The JSON password reference list
    // The json key that wraps the other key-value pairs

    /**
     * Returns an empty password hash list with no username or passwords
     */
    public PasswordHashList() {
        this.passwordRef = JsonNodeFactory.instance.objectNode();
    }

    /**
     * This method creates a new PasswordHashList using a JsonNode with a
     * {@code PASSWORD_REF_NAME} wrapper object. This facilitates the
     * serialising and storage component handled by {@code JsonUtil}.
     * @param passwordRef
     */
    public PasswordHashList(JsonNode passwordRef) {
        requireNonNull(passwordRef);
        this.passwordRef = passwordRef.get(PASSWORD_REF_NAME);
    }

    /**
     * A helper method to wrap the JsonNode in with {@code PASSWORD_REF_NAME}
     * Note: a deprecated method put(String, JsonNode) is used
     */
    private static PasswordHashList of(JsonNode passwordRef) {
        requireNonNull(passwordRef);
        ObjectNode wrappedNode = JsonNodeFactory.instance.objectNode();
        wrappedNode.putPOJO(PASSWORD_REF_NAME, passwordRef);
        return new PasswordHashList(wrappedNode);
    }

    /**
     * Returns a new {@code PasswordHashList} with the username/ hashed
     * password entry added.
     */
    public PasswordHashList addEntry(String username, String hashedPassword) {
        requireNonNull(username, hashedPassword);

        ObjectNode newPasswordRef = (ObjectNode) passwordRef;
        newPasswordRef.put(username, hashedPassword);
        return PasswordHashList.of(newPasswordRef);
    }

    /**
     * Retrieves the password associated with the given username.
     * @param username The username which is the key
     * @return An Optional value which is empty if the username is not
     * present. Else, the optional will wrap the associated password.
     */
    protected Optional<String> getExpectedPassword(String username) {
        requireNonNull(username);

        JsonNode expectedPasswordNode = passwordRef.get(username);

        if (expectedPasswordNode == null) {
            return Optional.empty();
        }

        return Optional.of(expectedPasswordNode.asText());
    }

    @Override
    public String toString() {
        return "Number of users: " + passwordRef.size();
    }

}
