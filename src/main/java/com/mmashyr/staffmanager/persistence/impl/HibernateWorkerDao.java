package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.Worker;
import com.mmashyr.staffmanager.persistence.WorkerDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Mark
 */

@Repository(value = "hibernateWorkerDao")
public class HibernateWorkerDao extends HibernateAbstractDao<Worker> implements WorkerDao {

    public HibernateWorkerDao() {
    }

}


