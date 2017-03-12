package com.app.service.impl;

import static com.app.util.CommonUtils.getListUsingPagination;

import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.TicketDao;
import com.app.entity.Event;
import com.app.entity.Ticket;
import com.app.entity.User;
import com.app.entity.impl.TicketImpl;
import com.app.service.TicketService;

public class TicketServiceImpl implements TicketService {

    private static final Logger LOGGER = Logger.getLogger(TicketServiceImpl.class);
    private TicketDao ticketDao;

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket ticket = new TicketImpl(userId, eventId, category, place);
        ticketDao.create(ticket);
        LOGGER.info("Book ticket:" + ticket.getUserId() + ticket.getId());
        return ticket;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        List<Ticket> tickets = ticketDao.getTicketsByUserId(user.getId());
        LOGGER.info("Getting tickets by user: " + user.getName());
        return getListUsingPagination(tickets, pageSize, pageNum);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        List<Ticket> tickets = ticketDao.getTicketsByEventId(event.getId());
        LOGGER.info("Getting tickets by event: " + event.getTitle());
        return getListUsingPagination(tickets, pageSize, pageNum);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        boolean result = false;
        Ticket ticket = ticketDao.getById(ticketId);
        if (ticket != null) {
            ticketDao.delete(ticketId);
            result = true;
            LOGGER.info("Deleting ticketId:" + ticketId);
        }
        return result;
    }
}
