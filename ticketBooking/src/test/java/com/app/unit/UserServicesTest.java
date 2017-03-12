package com.app.unit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.app.dao.TicketDao;
import com.app.dao.UserDao;
import com.app.entity.User;
import com.app.entity.impl.UserImpl;
import com.app.service.impl.UserServiceImpl;

/**
 * Test for UserServiceImpl
 */

public class UserServicesTest extends BaseUnitTest {

    private static final long USER_DELETE_ID = 2000;
    private static final long USER_ID = 47;
    private static final String USER_EMAIL = "user_email@test.com";
    private static final String USER_UPDATE_EMAIL = "email@email.com";
    private static final String USER_NAMES = "user_names";
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserDao userDao;
    @Mock
    private TicketDao ticketDao;
    private User userUpdate;
    private User mockedUser;

    @Before
    public void setUp() {
        User userDelete = new UserImpl("test5", "test5@test.com");
        mockedUser = getMockedUser();
        userUpdate = new UserImpl("test6", "tesst6@test.com");

        when(userDao.getById(USER_ID)).thenReturn(mockedUser);
        when(userDao.getById(USER_DELETE_ID)).thenReturn(userDelete);

        when(userDao.getUserByEmail(USER_EMAIL)).thenReturn(mockedUser);
        when(userDao.getUsersByName(USER_NAMES)).thenReturn(getMockUsers());

        when(userDao.create(mockedUser)).thenReturn(mockedUser);
        when(userDao.update(userUpdate)).thenReturn(userUpdate);
        when(userDao.delete(USER_DELETE_ID)).thenReturn(userDelete);
    }

    @Test
    public void getUserIdTest() {
        assertEquals("not equals", userService.getUserById(USER_ID).getEmail(), mockedUser.getEmail());
    }

    @Test
    public void getUserByEmailTest() {
        assertThat("not equals", userService.getUserByEmail(USER_EMAIL), equalTo(mockedUser));
    }

    @Test
    public void getUsersByName() {
        assertThat("not equals", userService.getUsersByName(USER_NAMES, 2, 2).size(), equalTo(2));
        assertThat("not equals", userService.getUsersByName(USER_NAMES, 2, 3).size(), equalTo(1));
    }

    @Test
    public void createUserTest() {
        assertNotNull("mockedUser not created", userService.createUser(mockedUser));
        verify(userDao, times(1)).create(mockedUser);
    }

    @Test
    public void updateUserTest() {
        userUpdate.setEmail(USER_UPDATE_EMAIL);
        userService.updateUser(userUpdate);
        assertThat("not updated", userUpdate.getEmail(), is(USER_UPDATE_EMAIL));
    }

    @Test
    public void deleteUserTest() {
        assertTrue("mockedUser not deleted", userService.deleteUser(USER_DELETE_ID));
        verify(userDao, times(1)).delete(USER_DELETE_ID);
    }
}
