package com.app.entity.impl;

import com.app.entity.User;

public class UserImpl implements User {

    private long id;
    private String name;
    private String email;

    public UserImpl(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserImpl() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserImpl{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}
