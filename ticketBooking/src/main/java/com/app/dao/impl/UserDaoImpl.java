package com.app.dao.impl;

import static com.app.util.CommonUtils.composeId;
import static com.app.util.Constant.USER_SPACE;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.UserDao;
import com.app.entity.User;

public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public User create(User user) {
        user.setId(getUniqueId());
        return (User) getStorage().put(composeId(USER_SPACE, user.getId()), user);
    }

    @Override
    public User update(User user) {
        return (User) getStorage().put(composeId(USER_SPACE, user.getId()), user);
    }

    @Override
    public User delete(long id) {
        return (User) getStorage().remove(composeId(USER_SPACE, id));
    }

    @Override
    public User getById(long id) {
        return (User) getStorage().get(composeId(USER_SPACE, id));
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        for (String key : getStorage().getAllKeys()) {
            if (key.contains(USER_SPACE)) {
                users.add((User) getStorage().get(key));
            }
        }
        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User currentUser : getAll()) {
            if (currentUser.getEmail().equals(email)) {
                return currentUser;
            }
        }
        return null;
    }

    @Override
    public List<User> getUsersByName(String name) {
        List<User> users = new ArrayList<User>();
        for (User currentUser : getAll()) {
            if (currentUser.getName().contains(name)) {
                users.add(currentUser);
                return users;
            }
        }
        return users;
    }
}
