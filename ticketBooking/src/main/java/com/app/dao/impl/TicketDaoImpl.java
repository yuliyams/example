package com.app.dao.impl;

import static com.app.util.CommonUtils.composeId;
import static com.app.util.Constant.TICKET_SPACE;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.TicketDao;
import com.app.entity.Ticket;

public class TicketDaoImpl extends AbstractDao implements TicketDao {

    @Override
    public Ticket create(Ticket ticket) {
        ticket.setId(getUniqueId());
        return (Ticket) getStorage().put(composeId(TICKET_SPACE, ticket.getId()), ticket);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return (Ticket) getStorage().put(composeId(TICKET_SPACE, ticket.getId()), ticket);
    }

    @Override
    public Ticket delete(long id) {
        return (Ticket) getStorage().remove(composeId(TICKET_SPACE, id));
    }

    @Override
    public Ticket getById(long id) {
        return (Ticket) getStorage().get(composeId(TICKET_SPACE, id));
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<Ticket>();
        for (String key : getStorage().getAllKeys()) {
            if (key.contains(TICKET_SPACE)) {
                tickets.add((Ticket) getStorage().get(key));
            }
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByUserId(long userId) {
        List<Ticket> ticketsCorrespondedUserId = new ArrayList<Ticket>();
        for (Ticket currentTicket : getAll()) {
            if (currentTicket.getUserId() == userId) {
                ticketsCorrespondedUserId.add(currentTicket);
            }
        }
        return ticketsCorrespondedUserId;
    }

    @Override
    public List<Ticket> getTicketsByEventId(long eventId) {
        List<Ticket> ticketsCorrespondedEventId = new ArrayList<Ticket>();
        for (Ticket currentTicket : getAll()) {
            if (currentTicket.getEventId() == eventId) {
                ticketsCorrespondedEventId.add(currentTicket);
            }
        }
        return ticketsCorrespondedEventId;
    }
}
