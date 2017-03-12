package com.app.unit;

import static com.app.util.CommonUtils.convertDate;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.app.dao.EventDao;
import com.app.dao.TicketDao;
import com.app.entity.Event;
import com.app.entity.impl.EventImpl;
import com.app.service.impl.EventServiceImpl;

/**
 * Test for EventServiceImpl
 */

public class EventServicesTest extends BaseUnitTest {

    private static final long EVENT_ID = 1400;
    private static final String EVENT_UPDATE_TITLE = "newTitle";
    private static final String EVENT_DATE = "2013-11-25";
    private static final String EVENT_TITLES = "event_list";
    @InjectMocks
    private EventServiceImpl eventService;
    @Mock
    private EventDao eventDao;
    @Mock
    private TicketDao ticketDao;
    private Event eventUpdate;
    private Event first_event;
    private Event second_event;
    private Date date;

    @Before
    public void setUp() throws ParseException {
        Event third_event = new EventImpl();
        second_event = new EventImpl();
        first_event = getMockEvent();
        date = convertDate(EVENT_DATE);
        eventUpdate = new EventImpl(EVENT_UPDATE_TITLE, date);
        when(eventDao.getEventsByTitle(EVENT_TITLES)).thenReturn(Arrays.asList(second_event, third_event));
        when(eventDao.getById(EVENT_ID)).thenReturn(first_event);
        when(eventDao.getEventsForDay(date)).thenReturn(Arrays.asList(second_event, third_event));
        when(eventDao.getAll()).thenReturn(Arrays.asList(second_event, third_event));
        when(eventDao.create(first_event)).thenReturn(first_event);
        when(eventDao.update(first_event)).thenReturn(eventUpdate);
        when(eventDao.delete(EVENT_ID)).thenReturn(first_event);
    }

    @Test
    public void getEventByIdTest() {
        assertThat("not equals", eventService.getEventById(EVENT_ID), equalTo(first_event));
        assertEquals("not equals", eventService.getEventById(EVENT_ID).getTitle(), EVENT_TITLE);
    }

    @Test
    public void getEventsByTitleTest() {
        assertThat("not contain", eventService.getEventsByTitle(EVENT_TITLES, 8, 1), hasItem(second_event));
        assertThat("not equals", eventService.getEventsByTitle(EVENT_TITLES, 8, 1).size(), is(2));
        verify(eventDao, times(2)).getEventsByTitle(EVENT_TITLES);
    }

    @Test
    public void getEventsByDateTest() throws ParseException {
        assertThat("not contain", eventService.getEventsForDay(date, 8, 1), hasItem(second_event));
        assertThat("not equals", eventService.getEventsForDay(date, 8, 1).size(), is(2));
        verify(eventDao, times(2)).getEventsForDay(date);
    }

    @Test
    public void createEventTest() {
        assertNotNull("first_event not created", eventService.createEvent(first_event));
        verify(eventDao, times(1)).create(first_event);
    }

    @Test
    public void updateEventTest() {
        eventUpdate.setTitle(EVENT_UPDATE_TITLE);
        eventService.updateEvent(eventUpdate);
        Assert.assertThat("not updated", eventUpdate.getTitle(), is(EVENT_UPDATE_TITLE));
    }

    @Test
    public void deleteEventTest() {
        assertTrue("first_event not deleted", eventService.deleteEvent(EVENT_ID));
        verify(eventDao, times(1)).delete(EVENT_ID);
    }
}
