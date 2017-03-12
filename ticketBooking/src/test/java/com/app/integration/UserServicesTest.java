package com.app.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.app.entity.User;
import com.app.entity.impl.UserImpl;

/**
 * Test for UserServiceImpl
 */

public class UserServicesTest extends BaseIntegrationTest {

    private static final String EMAIL = "test_user@test.com";
    private static final String NAME = "Test user";
    private static final String NEW_EMAIL = "user_newuser@test.com";

    @Test
    public void createUpdateDeleteUser() {
        User user = new UserImpl(EMAIL, NAME);
        getFacade().createUser(user);
        assertNotNull(user.getEmail());
        user.setEmail(NEW_EMAIL);
        getFacade().updateUser(user);
        assertEquals("not equals", user.getEmail(), NEW_EMAIL);
        long userId = user.getId();
        getFacade().deleteUser(userId);
        assertNull("user not deleted", getFacade().getUserById(userId));
    }
}
