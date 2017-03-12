package com.app.entity;

public interface Ticket {

    enum Category {
        STANDARD, PREMIUM, BAR
    }

    long getId();

    void setId(long id);

    long getEventId();

    void setEventId(long eventId);

    long getUserId();

    void setUserId(long userId);

    Category getCategory();

    void setCategory(Category category);

    int getPlace();

    void setPlace(int place);
}
