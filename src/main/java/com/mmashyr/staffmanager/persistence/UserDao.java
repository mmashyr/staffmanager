package com.mmashyr.staffmanager.persistence;

import com.mmashyr.staffmanager.model.User;

/**
 * Created by Mark on 11.11.2016.
 */
public interface UserDao extends EntityDao<User> {

    User findByLogin(String login);
}
