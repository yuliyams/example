package com.app.integration;

import static com.app.util.CommonUtils.convertDate;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import com.app.entity.Event;
import com.app.entity.Ticket;
import com.app.entity.User;
import com.app.entity.impl.EventImpl;
import com.app.entity.impl.UserImpl;

/**
 * Test for TicketServiceImpl
 */

public class BookServicesTest extends BaseIntegrationTest {

    private static final String EMAIL = "test_user@test.com";
    private static final String NAME = "Test user";
    private static final String TITLE = "test_event";
    private static final String DATE = "2013-02-05";
    private static final int PLACE = 10;

    @Test
    public void bookTest() throws ParseException {
        User user = new UserImpl(EMAIL, NAME);
        getFacade().createUser(user);
        Event event = new EventImpl(TITLE, convertDate(DATE));
        getFacade().createEvent(event);
        Ticket ticket = getFacade().bookTicket(user.getId(), event.getId(), PLACE, Ticket.Category.PREMIUM);
        assertThat("not equal", getFacade().getBookedTickets(event, 10, 1).size(), is(1));
        assertThat("not equal", getFacade().getBookedTickets(user, 10, 1).size(), is(1));
        assertTrue("ticket not canceled", getFacade().cancelTicket(ticket.getId()));
        assertThat("not equal", getFacade().getBookedTickets(event, 10, 1).size(), is(0));
    }
}
