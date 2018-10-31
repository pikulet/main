package seedu.address.logic.commands.exceptions;

import seedu.address.logic.commands.LogInCommand;

public class UnauthorisedCommandException extends CommandException {

    public UnauthorisedCommandException() {
        super(LogInCommand.MESSAGE_NOT_SIGNED_IN);
    }
}
