package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.Task;
import com.mmashyr.staffmanager.persistence.TaskDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Mark
 */
@Repository(value = "jpaTaskDao")
public class JPATaskDao extends JPAAbstractDao<Task> implements TaskDao {

    public JPATaskDao() {
    }

}
