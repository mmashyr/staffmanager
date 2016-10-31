package com.mmashyr.staffmanager.services.impl;

import com.mmashyr.staffmanager.model.Worker;
import com.mmashyr.staffmanager.persistence.WorkerDao;
import com.mmashyr.staffmanager.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mark
 */

@Service(value = "workerService")
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    @Qualifier(value = "hibernateWorkerDao")
    private WorkerDao dao;

    public WorkerServiceImpl() {
    }

    @Transactional(readOnly = true)
    public List<Worker> getAll() {
        return dao.getAll();
    }

    @Transactional(readOnly = true)
    public Worker getById(long id) {
        return dao.getById(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(Worker entity) {
        dao.add(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Worker entity) {
        dao.update(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(long id) {
        dao.delete(id);
    }

}
