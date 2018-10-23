package guitests.guihandles;

import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import seedu.address.model.person.Guest;

/**
 * Provides a handle for {@code GuestListPanel} containing the list of {@code GuestCard}.
 */
public class GuestDetailedPanelHandle extends NodeHandle<ListView<Guest>> {
    public static final String PERSON_LIST_VIEW_ID = "#guestDetailedView";

    private static final String CARD_PANE_ID = "#cardPane";

    public GuestDetailedPanelHandle(ListView<Guest> guestListPanelNode) {
        super(guestListPanelNode);
    }

    /**
     * Navigates the listview to display {@code guest}.
     */
    public void navigateToCard(Guest guest) {
        if (!getRootNode().getItems().contains(guest)) {
            throw new IllegalArgumentException("Guest does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(guest);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Returns all card nodes in the scene graph.
     * Card nodes that are visible in the listview are definitely in the scene graph, while some nodes that are not
     * visible in the listview may also be in the scene graph.
     */
    private Set<Node> getAllCardNodes() {
        return guiRobot.lookup(CARD_PANE_ID).queryAll();
    }

    /**
     * Returns the guest card handle of a guest associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public GuestDetailedCardHandle getGuestDetailedCardHandle(int index) {
        Set<Node> allNodes = getAllCardNodes();

        return getAllCardNodes().stream()
                .map(GuestDetailedCardHandle::new)
                .filter(handle -> handle.equals(getPerson(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private Guest getPerson(int index) {
        return getRootNode().getItems().get(index);
    }
}
