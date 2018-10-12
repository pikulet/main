package seedu.address.model.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.room.exceptions.BookingNotFoundException;
import seedu.address.model.room.exceptions.OverlappingBookingException;

/**
 * A list of Bookings that maintains non-overlapping property between its elements and does not allow nulls.
 * A Booking is considered non-overlapping by comparing using
 * {@code Booking#canAcceptBooking(Booking)}. As such, adding and updating of
 * Bookings uses Booking#canAcceptBooking(Booking) to ensure that the
 * Booking being added or updated does not overlap any existing ones in Bookings.
 * However, the removal of a Booking uses Booking#equals(Object) so
 * as to ensure that the Booking with exactly the same fields will be removed.
 * 
 * A sorted list of bookings is also maintained to support viewing of bookings in chronological order.
 * Note: SortedList only provides a sorted view of the list. Any changes to the list must be made through the underlying
 * ObservableList.
 *
 * Supports a minimal set of list operations.
 *
 * @see Booking#isOverlapping(Booking)
 */
public class Bookings implements Iterable<Booking> {

    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final SortedList<Booking> sortedList = new SortedList<>(internalList);

    /**
     * Returns true if the list canAcceptBooking an equivalent Booking as the given argument.
     */
    public boolean canAcceptBooking(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isOverlapping);
    }

    /**
     * Returns true if {@code Bookings} contains no overlapping Bookings.
     */
    private boolean bookingsAreNotOverlapping(List<Booking> bookings) {
        for (int i = 0; i < bookings.size() - 1; i++) {
            for (int j = i + 1; j < bookings.size(); j++) {
                if (bookings.get(i).isOverlapping(bookings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if there is an active booking now
     */
    public boolean hasCurrentBooking() {
        return getFirstUpcomingBooking().isActive();
    }

    /**
     * Gets the first upcoming booking
     */
    public Booking getFirstUpcomingBooking() {
        return sortedList.get(0);
    }
    
    public void updateBookings() {
        LocalDate today = LocalDate.now();
        internalList.stream()
            .filter(booking -> booking.isUpcoming());
    }

    /**
     * Adds a Booking to the list.
     * The Booking must not already exist in the list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        if (canAcceptBooking(toAdd)) {
            throw new OverlappingBookingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent Booking from the list.
     * The Booking must exist in the list.
     */
    public void remove(Booking toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BookingNotFoundException();
        }
    }

    /**
     * Replaces the Booking {@code target} in the list with {@code editedBooking}.
     * {@code target} must exist in the list.
     * The Booking identity of {@code editedBooking} must not be the same as another existing
     * Booking in the list.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookingNotFoundException();
        }

        if (canAcceptBooking(editedBooking)) {
            throw new OverlappingBookingException();
        }

        internalList.set(index, editedBooking);
    }


    public void setBookings(Bookings replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Bookings}.
     * {@code Bookings} must not contain duplicate Bookings.
     */
    public void setBookings(List<Booking> bookings) {
        requireAllNonNull(bookings);
        if (!bookingsAreNotOverlapping(bookings)) {
            throw new OverlappingBookingException();
        }

        internalList.setAll(bookings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Booking> asUnmodifiableSortedList() {
        return FXCollections.unmodifiableObservableList(sortedList);
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Bookings // instanceof handles nulls
                        && internalList.equals(((Bookings) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        sortedList.forEach(builder::append);
        return builder.toString();
    }
}
