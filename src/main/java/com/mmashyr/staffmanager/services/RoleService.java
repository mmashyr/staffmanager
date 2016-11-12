package com.mmashyr.staffmanager.services;

import com.mmashyr.staffmanager.model.Role;

import java.util.List;

/**
 * Created by Mark on 12.11.2016.
 */
public interface RoleService {

    public List<Role> getAll();

    public Role getById(long id);

    public void add(Role role);

    public void update(Role role);

    public void delete(long id);

    public Role findByType(String type);
}
