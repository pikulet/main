package seedu.address.model.login;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import seedu.address.commons.util.JsonUtil;

/**
 * Represents a password hash list to check the validity of a user login.
 */
public class PasswordHashList {

    private JsonNode passwordRef;

    public PasswordHashList(String passwordRefString) throws IOException {
        this.passwordRef = JsonUtil.getNodeObject(passwordRefString);
    }

    private PasswordHashList(JsonNode passwordRef) {
        this.passwordRef = passwordRef;
    }

    public static PasswordHashList getEmptyPasswordHashList() {
        return new PasswordHashList(JsonNodeFactory.instance.objectNode());
    }
}
