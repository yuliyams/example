package com.app.unit;

import static com.app.util.CommonUtils.convertDate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

import com.app.entity.Event;
import com.app.entity.Ticket;
import com.app.entity.User;
import com.app.entity.impl.EventImpl;
import com.app.entity.impl.TicketImpl;
import com.app.entity.impl.UserImpl;

/**
 * Abstract Test Class
 */

public abstract class BaseUnitTest {

    private static final String USER_NAME = "test_name";
    private static final String EVENT_DATE = "2013-02-05";
    protected static final String EVENT_TITLE = "test_event";
    protected static final long MOCKED_USER_ID = 1000;
    protected static final long MOCKED_EVENT_ID = 1000;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    public List<User> getMockUsers() {
        List<User> users = new ArrayList<User>();
        users.add(getMockedUser());
        users.add(new UserImpl(USER_NAME, "test1@test.com"));
        users.add(new UserImpl(USER_NAME, "test2@test.com"));
        users.add(new UserImpl(USER_NAME, "test3@test.com"));
        users.add(new UserImpl(USER_NAME, "test4@test.com"));
        return users;
    }

    public User getMockedUser() {
        return new UserImpl(USER_NAME, "test@test.com");
    }

    public Event getMockEvent() throws ParseException {
        return new EventImpl(EVENT_TITLE, convertDate(EVENT_DATE));
    }

    public Ticket getMockedTicket() {
        return new TicketImpl(MOCKED_USER_ID, MOCKED_EVENT_ID, Ticket.Category.PREMIUM, 20);
    }
}
