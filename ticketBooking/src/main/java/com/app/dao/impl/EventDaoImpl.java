package com.app.dao.impl;

import static com.app.util.CommonUtils.composeId;
import static com.app.util.Constant.EVENT_SPACE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.dao.EventDao;
import com.app.entity.Event;

public class EventDaoImpl extends AbstractDao implements EventDao {

    @Override
    public Event create(Event event) {
        event.setId(getUniqueId());
        return (Event) getStorage().put(composeId(EVENT_SPACE, event.getId()), event);
    }

    @Override
    public Event update(Event event) {
        return (Event) getStorage().put(composeId(EVENT_SPACE, event.getId()), event);
    }

    @Override
    public Event delete(long id) {
        return (Event) getStorage().remove(composeId(EVENT_SPACE, id));
    }

    @Override
    public Event getById(long id) {
        return (Event) getStorage().get(composeId(EVENT_SPACE, id));
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<Event>();
        for (String key : getStorage().getAllKeys()) {
            if (key.contains(EVENT_SPACE)) {
                events.add((Event) getStorage().get(key));
            }
        }
        return events;
    }

    @Override
    public List<Event> getEventsByTitle(String title) {
        List<Event> eventsWithCorrespondedTitle = new ArrayList<Event>();
        for (Event currentEvent : getAll()) {
            if (currentEvent.getTitle().equals(title)) {
                eventsWithCorrespondedTitle.add(currentEvent);
            }
        }
        return eventsWithCorrespondedTitle;
    }

    @Override
    public List<Event> getEventsForDay(Date day) {
        List<Event> eventsWithCorrespondedDate = new ArrayList<Event>();
        for (Event currentEvent : getAll()) {
            if (currentEvent.getDate().compareTo(day) == 0) {
                eventsWithCorrespondedDate.add(currentEvent);
            }
        }
        return eventsWithCorrespondedDate;
    }
}
