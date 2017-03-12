package com.app.entity;

import java.util.Date;

public interface Event {

    long getId();

    void setId(long id);

    String getTitle();

    void setTitle(String title);

    Date getDate();

    void setDate(Date date);
}
