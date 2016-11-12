package com.mmashyr.staffmanager.services.impl;

import com.mmashyr.staffmanager.model.User;
import com.mmashyr.staffmanager.persistence.UserDao;
import com.mmashyr.staffmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mark on 12.11.2016.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public User getById(long id) {
        return dao.getById(id);
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.add(user);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public User findByLogin(String login) {
        return dao.findByLogin(login);
    }
}
