package com.app;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.entity.User;
import com.app.entity.impl.UserImpl;
import com.app.facade.BookingFacade;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        BookingFacade facade = context.getBean("bookingFacade", BookingFacade.class);

        User firstUser = new UserImpl();
        firstUser.setName("Test user");
        firstUser.setEmail("test_user@test.com");
        facade.createUser(firstUser);
        LOGGER.info("Created user: " + firstUser);

        User secondUser = new UserImpl();
        secondUser.setName("Ivanov");
        secondUser.setEmail("Ivanov_user@test.com");
        facade.createUser(secondUser);
        LOGGER.info("Created user: " + secondUser);

        boolean result = facade.deleteUser(secondUser.getId());
        LOGGER.info("Created user: " + result);
        LOGGER.info("Get user: " + facade.getUserByEmail("max007@mail.ru"));
        LOGGER.info("Get event: " + facade.getEventById(2));

    }
}
