package seedu.address.model.login;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.Test;

public class LogInManagerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LogInManager(null));
    }

    @Test
    public void isSignedIn_success() throws Exception {

        // new empty LogInManager -> not signed in
        LogInManager logInManager = new LogInManager();
        assertFalse(logInManager.isSignedIn());

        // new non-empty LogInManager -> not signed in
        PasswordHashList passwordRef = PasswordHashListTest.getSamplePasswordHashList();
        logInManager = new LogInManager(passwordRef);
        assertFalse(logInManager.isSignedIn());

        // after failed sign-in attempt -> not signed in
        assertThrows(InvalidLogInException.class, () -> new LogInManager(passwordRef).signIn(
                "user1", "wrongpassword"));
        assertFalse(logInManager.isSignedIn());

        // after correct sign-in attempt -> signed in
        logInManager.signIn("user1", "passw0rd");
        assertTrue(logInManager.isSignedIn());
    }
}
