package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.Role;
import com.mmashyr.staffmanager.model.UserRoleType;
import com.mmashyr.staffmanager.persistence.RoleDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Created by Mark on 12.11.2016.
 */
@Repository(value = "jpaRoleDao")
public class JpaRoleDao extends JPAAbstractDao<Role> implements RoleDao {
    @Override
    public Role findByType(String type) {
        TypedQuery<Role> query = createTypedQuery("SELECT r FROM Role r WHERE r.type = :type)");
        query.setParameter("type", UserRoleType.valueOf(type));

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
