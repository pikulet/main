package seedu.address.ui;

import com.google.common.eventbus.Subscribe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.Guest;

import java.util.logging.Logger;

/**
 * Panel containing the list of persons.
 */
public class PersonDetailedPanel extends UiPart<Region> {
    private static final String FXML = "PersonDetailedPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonDetailedPanel.class);

    @FXML
    private ListView<Guest> personDetailedView;

    public PersonDetailedPanel() {
        super(FXML);
        registerAsAnEventHandler(this);
    }


    private void displayGuestDetails(Guest guest) {
        ObservableList<Guest> guestList = FXCollections.observableArrayList();
        guestList.add(guest);
        personDetailedView.setItems(guestList);
        personDetailedView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Guest} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Guest> {
        @Override
        protected void updateItem(Guest guest, boolean empty) {
            super.updateItem(guest, empty);

            if (empty || guest == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonDetailedCard(guest).getRoot());
            }
        }
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        displayGuestDetails(event.getNewSelection());
    }

}
