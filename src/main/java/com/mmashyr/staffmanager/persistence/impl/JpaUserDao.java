package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.User;
import com.mmashyr.staffmanager.persistence.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

/**
 * Created by Mark
 */
@Repository(value = "jpaUserDao")
public class JpaUserDao extends JPAAbstractDao<User> implements UserDao {


    public JpaUserDao() {

    }

    @Override
    public User findByLogin(String login) {
        TypedQuery<User> query = createTypedQuery("SELECT u FROM User u");
       // query.setParameter("login", login);

        return query.getSingleResult();
    }
}

