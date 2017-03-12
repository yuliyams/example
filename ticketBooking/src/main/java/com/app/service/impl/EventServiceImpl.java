package com.app.service.impl;

import static com.app.util.CommonUtils.getListUsingPagination;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.EventDao;
import com.app.dao.TicketDao;
import com.app.entity.Event;
import com.app.entity.Ticket;
import com.app.service.EventService;

public class EventServiceImpl implements EventService {

    private static final Logger LOGGER = Logger.getLogger(EventServiceImpl.class);
    private EventDao eventDao;
    private TicketDao ticketDao;

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public Event getEventById(long id) {
        LOGGER.info("Getting event by id: " + id);
        return eventDao.getById(id);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        List<Event> events = eventDao.getEventsByTitle(title);
        LOGGER.info("Getting events by title: " + title);
        return getListUsingPagination(events, pageSize, pageNum);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        List<Event> events = eventDao.getEventsForDay(day);
        LOGGER.info("Getting events by day: " + day);
        return getListUsingPagination(events, pageSize, pageNum);
    }

    @Override
    public Event createEvent(Event event) {
        LOGGER.info("Create event: " + event.getTitle());
        return eventDao.create(event);
    }

    @Override
    public Event updateEvent(Event event) {
        LOGGER.info("Update event: " + event.getTitle());
        return eventDao.update(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        boolean result = false;
        Event event = eventDao.getById(eventId);
        if (event != null) {
            for (Ticket currentTicket : ticketDao.getTicketsByEventId(eventId)) {
                ticketDao.delete(currentTicket.getId());
            }
            eventDao.delete(eventId);
            result = true;
            LOGGER.info("Delete event: " + eventId);
        }
        return result;
    }
}
