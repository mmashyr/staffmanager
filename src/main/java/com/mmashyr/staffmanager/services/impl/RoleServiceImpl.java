package com.mmashyr.staffmanager.services.impl;

import com.mmashyr.staffmanager.model.Role;
import com.mmashyr.staffmanager.persistence.RoleDao;
import com.mmashyr.staffmanager.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mark on 12.11.2016.
 */
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao dao;

    @Override
    public List<Role> getAll() {
        return dao.getAll();
    }

    @Override
    public Role getById(long id) {
        return dao.getById(id);
    }

    @Override
    public void add(Role role) {
        dao.add(role);
    }

    @Override
    public void update(Role role) {
        dao.update(role);

    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public Role findByType(String type) {
        return dao.findByType(type);
    }
}
