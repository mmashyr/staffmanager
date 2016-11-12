package com.mmashyr.staffmanager.persistence;

import com.mmashyr.staffmanager.model.Role;

/**
 * Created by Mark on 12.11.2016.
 */
public interface RoleDao extends EntityDao<Role> {

    Role findByType(String type);
}
