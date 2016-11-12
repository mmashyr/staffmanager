package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.User;
import com.mmashyr.staffmanager.persistence.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created by Mark
 */
@Repository(value = "userDao")
public class JpaUserDao extends JPAAbstractDao<User> implements UserDao {


    public JpaUserDao() {

    }

    @Override
    public User findByLogin(String login) {
        Query query = createQuery("SELECT User u FROM User WHERE u.login = :login)");
        query.setParameter("login", login).setMaxResults(1);

        return (User) query.getSingleResult();
    }
}