package com.app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.TicketDao;
import com.app.dao.UserDao;
import com.app.entity.Ticket;
import com.app.entity.User;
import com.app.service.UserService;
import com.app.util.CommonUtils;

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao;
    private TicketDao ticketDao;

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(long id) {
        logger.info("Getting user by id: " + id);
        return userDao.getById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        logger.info("Getting user by email: " + email);
        return userDao.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        List<User> usersList = userDao.getUsersByName(name);
        logger.info("Getting users by name: " + name);
        return CommonUtils.getListUsingPagination(usersList, pageSize, pageNum);
    }

    @Override
    public User createUser(User user) {
        logger.info("Create user: " + user.getName());
        return userDao.create(user);
    }

    @Override
    public User updateUser(User user) {
        logger.info("Update user: " + user.getName());
        return userDao.update(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        boolean result = false;
        User user = userDao.getById(userId);
        if (user != null) {
            for (Ticket currentTicket : ticketDao.getTicketsByUserId(userId)) {
                ticketDao.delete(currentTicket.getId());
            }
            userDao.delete(userId);
            result = true;
            logger.info("Delete user:" + userId);
        }
        return result;
    }

}
