package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.Role;
import com.mmashyr.staffmanager.model.User;
import com.mmashyr.staffmanager.persistence.RoleDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created by Mark on 12.11.2016.
 */
@Repository(value = "jpaRoleDao")
public class JpaRoleDao extends  JPAAbstractDao<Role> implements RoleDao {
    @Override
    public Role findByType(String type) {
        Query query = createQuery("SELECT r FROM Role r WHERE u.type = :type)");
        query.setParameter("type", type).setMaxResults(1);

        return (Role) query.getSingleResult();
    }
}
