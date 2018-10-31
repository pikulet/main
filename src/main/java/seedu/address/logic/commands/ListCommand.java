package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_ARCHIVED_GUEST;
import static seedu.address.logic.parser.CliSyntax.FLAG_GUEST;
import static seedu.address.logic.parser.CliSyntax.FLAG_ROOM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GUESTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROOMS;
import static seedu.address.model.Model.PREDICATE_SHOW_NO_GUESTS;
import static seedu.address.model.Model.PREDICATE_SHOW_NO_ROOMS;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ListingChangedEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Lists all guests in Concierge to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "List successful!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of guest or rooms. "
            + "Parameters: "
            + FLAG_GUEST + " for guests, "
            + FLAG_ROOM + " for rooms. \n"
            + "Example: " + COMMAND_WORD + " "
            + FLAG_ROOM;

    private final String flag;

    /**
     * Creates a ListCommand to handle listing of guests/rooms and other flags
     */
    public ListCommand(String flag) {
        requireNonNull(flag);
        this.flag = flag;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);

        // flag is guaranteed to be valid by the parser, so no need to throw exception after these if-else blocks
        if (flag.equals(FLAG_GUEST.toString())) {
            EventsCenter.getInstance().post(new ListingChangedEvent(FLAG_GUEST.toString()));
        } else if (flag.equals(FLAG_ROOM.toString())) {
            EventsCenter.getInstance().post(new ListingChangedEvent(FLAG_ROOM.toString()));
        } else if (flag.equals(FLAG_ARCHIVED_GUEST.toString())) {
            EventsCenter.getInstance().post(new ListingChangedEvent(FLAG_ARCHIVED_GUEST.toString()));
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && flag.equals(((ListCommand) other).flag));
    }

}
