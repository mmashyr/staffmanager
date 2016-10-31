package com.mmashyr.staffmanager.services;

import com.mmashyr.staffmanager.model.Task;

import java.util.List;

/**
 * Created by Mark
 */
public interface TaskService {

    public List<Task> getAll();

    public Task getById(long id);

    public void add(Task entity);

    public void update(Task entity);

    public void delete(long id);
}
