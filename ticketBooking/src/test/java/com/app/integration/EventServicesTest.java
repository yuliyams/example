package com.app.integration;

import static com.app.util.CommonUtils.convertDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;

import org.junit.Test;

import com.app.entity.Event;
import com.app.entity.impl.EventImpl;

/**
 * Test for EventServiceImpl
 */

public class EventServicesTest extends BaseIntegrationTest {

    private static final String TITLE = "test_event";
    private static final String NEW_TITLE = "new_event";
    private static final String DATE = "2013-02-05";

    @Test
    public void createUpdateDeleteTicket() throws ParseException {
        Event event = new EventImpl(TITLE, convertDate(DATE));
        getFacade().createEvent(event);
        assertNotNull(getFacade().getEventById(event.getId()));
        event.setTitle(NEW_TITLE);
        getFacade().updateEvent(event);
        assertEquals("not equals", event.getTitle(), NEW_TITLE);
        long eventId = event.getId();
        getFacade().deleteEvent(eventId);
        assertNull("event not deleted", getFacade().getEventById(eventId));
    }
}
