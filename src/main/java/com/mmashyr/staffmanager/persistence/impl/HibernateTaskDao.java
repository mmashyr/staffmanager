package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.Task;
import com.mmashyr.staffmanager.persistence.TaskDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Mark
 */
@Repository(value = "hibernateTaskDao")
public class HibernateTaskDao extends HibernateAbstractDao<Task> implements TaskDao {

    public HibernateTaskDao() {
    }

}
