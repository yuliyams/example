package com.app.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.facade.BookingFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/resources/application.xml" })
public abstract class BaseIntegrationTest {

    @Autowired
    private AbstractApplicationContext context;
    private BookingFacade facade;

    public BookingFacade getFacade() {
        return facade;
    }

    @Before
    public void setUp() {
        facade = (BookingFacade) context.getBean("bookingFacade");
    }

    @After
    public final void tearDown() {
        context.registerShutdownHook();
    }
}
