package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.Role;
import com.mmashyr.staffmanager.model.User;
import com.mmashyr.staffmanager.persistence.RoleDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Created by Mark on 12.11.2016.
 */
@Repository(value = "jpaRoleDao")
public class JpaRoleDao extends  JPAAbstractDao<Role> implements RoleDao {
    @Override
    public Role findByType(String type) {
        TypedQuery<Role> query = createTypedQuery("SELECT r FROM Role r WHERE r.type = :type)");
        query.setParameter("type", type);

        return query.getSingleResult();
    }
}
