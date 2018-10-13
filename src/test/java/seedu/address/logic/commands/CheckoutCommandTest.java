package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRooms.getTypicalUniqueRoomList;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.room.RoomNumber;
import seedu.address.testutil.TypicalBookingPeriods;
import seedu.address.testutil.TypicalRoomNumbers;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class CheckoutCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalUniqueRoomList());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validCheckout_success() {
        RoomNumber roomNumberToCheckout = TypicalRoomNumbers.ROOM_NUMBER_001;
        CheckoutCommand checkoutCommand = new CheckoutCommand(roomNumberToCheckout);

        String expectedMessage = String.format(CheckoutCommand.MESSAGE_CHECKOUT_ROOM_SUCCESS, roomNumberToCheckout);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), 
            model.getUniqueRoomList());
        expectedModel.checkoutRoom(roomNumberToCheckout, 
            model.getUniqueRoomList()
                .asUnmodifiableObservableList()
                .get(roomNumberToCheckout.getRoomNumberAsIndex().getZeroBased())
                .getBookings()
                .getFirstBooking()
                .getBookingPeriod()
                .getEndDate());
        expectedModel.commitAddressBook();

        assertCommandSuccess(checkoutCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCheckout_throwsCommandException() {
        RoomNumber roomNumberToCheckout = new RoomNumber(RoomNumber.MAX_ROOM_NUMBER + 1);
        CheckoutCommand checkoutCommand = new CheckoutCommand(roomNumberToCheckout);

        assertCommandFailure(checkoutCommand, model, commandHistory, Messages.MESSAGE_INVALID_ROOM_CHECKOUT);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() {
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
    }

    @Test
    public void equals() {
    }
}
