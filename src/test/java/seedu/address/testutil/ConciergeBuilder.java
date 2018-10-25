package seedu.address.testutil;

import seedu.address.model.Concierge;
import seedu.address.model.guest.Guest;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Concierge ab = new ConciergeBuilder().withGuest("John", "Doe").build();}
 */
public class ConciergeBuilder {

    private Concierge addressBook;

    public ConciergeBuilder() {
        addressBook = new Concierge();
    }

    public ConciergeBuilder(Concierge addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Guest} to the {@code Concierge} that we are building.
     */
    public ConciergeBuilder withGuest(Guest guest) {
        addressBook.addGuest(guest);
        return this;
    }

    public Concierge build() {
        return addressBook;
    }
}
