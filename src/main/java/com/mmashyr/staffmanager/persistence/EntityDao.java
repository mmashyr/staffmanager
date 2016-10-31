package com.mmashyr.staffmanager.persistence;

import com.mmashyr.staffmanager.model.BaseModel;

import java.util.List;

/**
 * Created by Mark
 */
public interface EntityDao<T extends BaseModel> {

    public List<T> getAll();

    public T getById(long id);

    public void add(T entity);

    public void update(T entity);

    public void delete(long id);


}
