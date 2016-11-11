package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.Worker;
import com.mmashyr.staffmanager.persistence.WorkerDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Mark
 */

@Repository(value = "jpaWorkerDao")
public class JPAWorkerDao extends JPAAbstractDao<Worker> implements WorkerDao {

    public JPAWorkerDao() {
    }

}


