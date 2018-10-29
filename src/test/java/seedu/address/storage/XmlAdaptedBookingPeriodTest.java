package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.XmlAdaptedBookingPeriod.MISSING_FIELD_MESSAGE_FORMAT;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.room.booking.BookingPeriod;
import seedu.address.testutil.Assert;
import seedu.address.testutil.TypicalBookingPeriods;

public class XmlAdaptedBookingPeriodTest {
    
    private static final String MESSAGE_START_DATE = "startDate";
    private static final String MESSAGE_END_DATE = "endDate";

    private static final String INVALID_START_DATE = "2018/10/21";
    private static final String INVALID_END_DATE = "102118";

    private static final String VALID_START_DATE = 
            TypicalBookingPeriods.TODAY_TOMORROW.getStartDateAsFormattedString();
    private static final String VALID_END_DATE = 
            TypicalBookingPeriods.TODAY_TOMORROW.getEndDateAsFormattedString();

    @Test
    public void toModelType_validBookingPeriodDetails_returnsBookingPeriod() throws Exception {
        XmlAdaptedBookingPeriod bookingPeriod = new XmlAdaptedBookingPeriod(VALID_START_DATE, VALID_END_DATE);
        assertEquals(TypicalBookingPeriods.TODAY_TOMORROW, bookingPeriod.toModelType());
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        XmlAdaptedBookingPeriod bookingPeriod =
                new XmlAdaptedBookingPeriod(INVALID_START_DATE, VALID_END_DATE);
        String expectedMessage = BookingPeriod.MESSAGE_BOOKING_PERIOD_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, bookingPeriod::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        XmlAdaptedBookingPeriod bookingPeriod = new XmlAdaptedBookingPeriod(null, VALID_END_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MESSAGE_START_DATE);
        Assert.assertThrows(IllegalValueException.class, expectedMessage, bookingPeriod::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        XmlAdaptedBookingPeriod bookingPeriod =
                new XmlAdaptedBookingPeriod(VALID_START_DATE, INVALID_END_DATE);
        String expectedMessage = BookingPeriod.MESSAGE_BOOKING_PERIOD_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, bookingPeriod::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        XmlAdaptedBookingPeriod bookingPeriod = new XmlAdaptedBookingPeriod(VALID_START_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MESSAGE_END_DATE);
        Assert.assertThrows(IllegalValueException.class, expectedMessage, bookingPeriod::toModelType);
    }

}
