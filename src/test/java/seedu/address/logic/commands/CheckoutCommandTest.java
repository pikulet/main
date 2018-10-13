package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.RoomNumber;
import seedu.address.testutil.TypicalRoomNumbers;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code CheckoutCommand}.
 */
public class CheckoutCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validCheckout_success() {
        RoomNumber roomNumberToCheckout = TypicalRoomNumbers.ROOM_NUMBER_072;
        CheckoutCommand checkoutCommand = new CheckoutCommand(roomNumberToCheckout);

        String expectedMessage = String.format(CheckoutCommand.MESSAGE_CHECKOUT_ROOM_SUCCESS, roomNumberToCheckout);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), 
            model.getUniqueRoomList());
        expectedModel.checkinRoom(roomNumberToCheckout);
        expectedModel.checkoutRoom(roomNumberToCheckout);
        expectedModel.commitAddressBook();

        assertCommandSuccess(checkoutCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCheckoutNoActiveBooking_throwsCommandException() {
        RoomNumber roomNumberToCheckout = TypicalRoomNumbers.ROOM_NUMBER_099;
        CheckoutCommand checkoutCommand = new CheckoutCommand(roomNumberToCheckout);
        
        String expectedMessage = String.format(CheckoutCommand.MESSAGE_NO_ACTIVE_BOOKING_ROOM_CHECKOUT, 
            roomNumberToCheckout);

        assertCommandFailure(checkoutCommand, model, commandHistory, expectedMessage);
    }

    @Test
    public void execute_invalidCheckoutUnoccupiedRoom_throwsCommandException() {
        RoomNumber roomNumberToCheckout = TypicalRoomNumbers.ROOM_NUMBER_088;
        CheckoutCommand checkoutCommand = new CheckoutCommand(roomNumberToCheckout);

        String expectedMessage = String.format(CheckoutCommand.MESSAGE_UNOCCUPIED_ROOM_CHECKOUT, roomNumberToCheckout);

        assertCommandFailure(checkoutCommand, model, commandHistory, expectedMessage);
    }
    
    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() {
        // to be completed after AddressBook is refactored
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        // to be completed after AddressBook is refactored
    }

    @Test
    public void equals() {
        CheckoutCommand checkoutFirstCommand = new CheckoutCommand(TypicalRoomNumbers.ROOM_NUMBER_001);
        CheckoutCommand checkoutSecondCommand = new CheckoutCommand(TypicalRoomNumbers.ROOM_NUMBER_002);

        // same object -> returns true
        assertTrue(checkoutFirstCommand.equals(checkoutFirstCommand));

        // same values -> returns true
        CheckoutCommand deleteFirstCommandCopy = new CheckoutCommand(TypicalRoomNumbers.ROOM_NUMBER_001);
        assertTrue(checkoutFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(checkoutFirstCommand.equals(1));

        // null -> returns false
        assertFalse(checkoutFirstCommand.equals(null));

        // different room number -> returns false
        assertFalse(checkoutFirstCommand.equals(checkoutSecondCommand));
    }
}
