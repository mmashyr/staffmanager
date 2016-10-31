package com.mmashyr.staffmanager.services;

import com.mmashyr.staffmanager.model.Worker;

import java.util.List;

/**
 * Created by Mark
 */
public interface WorkerService {

    public List<Worker> getAll();

    public Worker getById(long id);

    public void add(Worker entity);

    public void update(Worker entity);

    public void delete(long id);
}
