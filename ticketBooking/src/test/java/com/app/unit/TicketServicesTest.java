package com.app.unit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

import java.text.ParseException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.app.dao.TicketDao;
import com.app.entity.Event;
import com.app.entity.Ticket;
import com.app.entity.User;
import com.app.entity.impl.TicketImpl;
import com.app.service.impl.TicketServiceImpl;

/**
 * Test for TicketServiceImpl
 */

public class TicketServicesTest extends BaseUnitTest {

    private static final long USER_ID = 2300;
    private static final long EVENT_ID = 7800;
    private static final long TICKET_ID = 3000;
    private static final int PLACE = 20;
    @InjectMocks
    private TicketServiceImpl ticketService;
    @Mock
    private TicketDao ticketDao;
    private Event event;
    private User user;
    private Ticket mockedTicket;
    private Ticket ticket1;
    private Ticket ticket2;

    @Before
    public void setUp() throws ParseException {
        ticket1 = new TicketImpl();
        ticket2 = new TicketImpl();
        mockedTicket = getMockedTicket();
        mockedTicket.setId(TICKET_ID);
        event = getMockEvent();
        event.setId(EVENT_ID);
        user = getMockedUser();
        user.setId(USER_ID);
        when(ticketDao.getTicketsByUserId(USER_ID)).thenReturn(Arrays.asList(ticket1, ticket2));
        when(ticketDao.getTicketsByEventId(EVENT_ID)).thenReturn(Arrays.asList(ticket1, ticket2));
        when(ticketDao.getById(TICKET_ID)).thenReturn(mockedTicket);
        when(ticketDao.create(mockedTicket)).thenReturn(mockedTicket);
        when(ticketDao.delete(TICKET_ID)).thenReturn(mockedTicket);
    }

    @Test
    public void getBookedTicketsTest() throws ParseException {
        assertThat("not contain", ticketService.getBookedTickets(event, 8, 1), hasItem(ticket1));
        assertThat("not equals", ticketService.getBookedTickets(event, 8, 1).size(), is(2));
        verify(ticketDao, times(2)).getTicketsByEventId(EVENT_ID);
    }

    @Test
    public void getBookedTicketsTest1() throws ParseException {
        assertThat("not contain", ticketService.getBookedTickets(user, 8, 1), hasItem(ticket2));
        assertThat("not equals", ticketService.getBookedTickets(user, 8, 1).size(), is(2));
        verify(ticketDao, times(2)).getTicketsByUserId(USER_ID);
    }

    @Test
    public void bookTicketTest() {
        ticketService.bookTicket(MOCKED_USER_ID, MOCKED_EVENT_ID, PLACE, Ticket.Category.PREMIUM);
        assertNotNull(mockedTicket);
        assertThat("not equals", mockedTicket.getId(), equalTo(TICKET_ID));
        assertThat("not equals", mockedTicket.getUserId(), equalTo(MOCKED_USER_ID));
        assertThat("not equals", mockedTicket.getEventId(), equalTo(MOCKED_EVENT_ID));
        assertThat("not equals", mockedTicket.getPlace(), equalTo(PLACE));
        assertThat("not equals", mockedTicket.getCategory(), equalTo(Ticket.Category.PREMIUM));
        verify(ticketDao, times(1)).create(any(Ticket.class));
    }

    @Test
    public void cancelTicketTest() {
        assertTrue(ticketService.cancelTicket(TICKET_ID));
        verify(ticketDao, times(1)).delete(TICKET_ID);
    }
}
