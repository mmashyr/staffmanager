package com.mmashyr.staffmanager.persistence.impl;

import com.mmashyr.staffmanager.model.BaseModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Mark
 */

@Repository
public abstract class JPAAbstractDao<T extends BaseModel> {


    private EntityManager em;
    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public JPAAbstractDao() {
        this.clazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<T> getAll() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> entityRoot = criteriaQuery.from(clazz);
        criteriaQuery.select(entityRoot);
        TypedQuery<T> tQuery = em.createQuery(criteriaQuery);
        return tQuery.getResultList();

    }

    public T getById(long id) {
        return em.find(clazz, id);
    }

    public void add(T entity) {
        em.persist(entity);

    }

    public void update(T entity) {
        em.merge(entity);
    }

    public void delete(long id) {
        em.remove(em.find(clazz, id));
    }

    protected Query createQuery(String query){
        return em.createQuery(query, clazz);
    }
}
