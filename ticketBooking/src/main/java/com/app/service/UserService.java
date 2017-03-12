package com.app.service;

import java.util.List;

import com.app.entity.User;

public interface UserService {

    User getUserById(long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name, int pageSize, int pageNum);

    User createUser(User user);

    User updateUser(User user);

    boolean deleteUser(long userId);
}
