package seedu.address.ui;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
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

    public PersonDetailedPanel(ObservableList<Guest> guestList) {
        super(FXML);
        setConnections(guestList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Guest> guestList) {
        personDetailedView.setItems(guestList);
        personDetailedView.setCellFactory(listView -> new PersonListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        personDetailedView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in guest list panel changed to : '" + newValue + "'");
                        raise(new PersonPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code PersonCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            personDetailedView.scrollTo(index);
            personDetailedView.getSelectionModel().clearAndSelect(index);
        });
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        scrollTo(event.targetIndex);
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
                setGraphic(new PersonCard(guest, getIndex() + 1).getRoot());
            }
        }
    }

}
