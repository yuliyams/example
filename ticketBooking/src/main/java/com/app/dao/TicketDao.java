package com.app.dao;

import java.util.List;

import com.app.entity.Ticket;

/**
 * Dao object for the entity Ticket
 */
public interface TicketDao extends Dao<Ticket> {

    /**
     * Get tickets booked by the user id
     *
     * @param userId
     *            - user id
     * @return the list of tickets
     */
    List<Ticket> getTicketsByUserId(long userId);

    /**
     * Get tickets booked by the event id
     *
     * @param eventId
     *            - event id
     * @return the list of tickets
     */

    List<Ticket> getTicketsByEventId(long eventId);

}
