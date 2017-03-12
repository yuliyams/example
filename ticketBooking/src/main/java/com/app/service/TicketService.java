package com.app.service;

import java.util.List;

import com.app.entity.Event;
import com.app.entity.Ticket;
import com.app.entity.User;

public interface TicketService {

    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    boolean cancelTicket(long ticketId);
}
