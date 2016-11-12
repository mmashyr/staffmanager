package com.mmashyr.staffmanager.services;

import com.mmashyr.staffmanager.model.User;

import java.util.List;

/**
 * Created by Mark on 12.11.2016.
 */
public interface UserService {

    public List<User> getAll();

    public User getById(long id);

    public void add(User user);

    public void update(User user);

    public void delete(long id);

    public User findByLogin(String login);
}
