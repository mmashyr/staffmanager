package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.User;
import com.mmashyr.staffmanager.persistence.UserDao;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
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
        TypedQuery<User> query = createTypedQuery("SELECT u FROM User u WHERE u.login = :login");
        query.setParameter("login", login);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}


