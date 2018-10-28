package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import seedu.address.logic.commands.CheckinCommand;
import seedu.address.logic.commands.CheckoutCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.room.RoomNumber;

/**
 * Parses input arguments and creates a new CheckinCommand object
 */
public class CheckinCommandParser implements Parser<CheckinCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckinCommand
     * and returns an CheckinCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckinCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROOM);
        if (!argMultimap.getValue(PREFIX_ROOM).isPresent() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckoutCommand.MESSAGE_USAGE));
        }
        try {
            RoomNumber roomNumber = ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM).get());
            return new CheckinCommand(roomNumber);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckinCommand.MESSAGE_USAGE), pe);
        }
    }

}
