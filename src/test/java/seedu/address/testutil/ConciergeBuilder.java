package seedu.address.testutil;

import seedu.address.model.Concierge;
import seedu.address.model.guest.Guest;

/**
 * A utility class to help with building Concierge objects.
 * Example usage: <br>
 *     {@code Concierge ab = new ConciergeBuilder().withGuest("John", "Doe").build();}
 */
public class ConciergeBuilder {

    private Concierge concierge;

    public ConciergeBuilder() {
        concierge = new Concierge();
    }

    public ConciergeBuilder(Concierge concierge) {
        this.concierge = concierge;
    }

    /**
     * Adds a new {@code Guest} to the {@code Concierge} that we are building.
     */
    public ConciergeBuilder withGuest(Guest guest) {
        concierge.addGuest(guest);
        return this;
    }

    public Concierge build() {
        return concierge;
    }
}
