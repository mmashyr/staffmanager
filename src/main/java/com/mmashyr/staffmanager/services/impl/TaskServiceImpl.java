package com.mmashyr.staffmanager.services.impl;

import com.mmashyr.staffmanager.model.Task;
import com.mmashyr.staffmanager.persistence.TaskDao;
import com.mmashyr.staffmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mark
 */
@Service(value = "taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    @Qualifier(value = "hibernateTaskDao")
    private TaskDao dao;

    public TaskServiceImpl() {
    }

    @Transactional(readOnly = true)
    public List<Task> getAll() {
        return dao.getAll();
    }

    @Transactional(readOnly = true)
    public Task getById(long id) {
        return dao.getById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void add(Task entity) {
        dao.add(entity);
    }

    @Transactional
    public void update(Task entity) {
        dao.update(entity);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }


}

