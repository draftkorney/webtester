package ua.alex.source.webtester.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.alex.source.webtester.dao.IEntityDao;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractEntityDao<T> implements IEntityDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected abstract Class<T> getEntityClass();

    @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    @Override
    public T getById(Serializable id) {
        return (T) getSession().get(getEntityClass(), id);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public List<T> findAll() {
        return getSession().createCriteria(getEntityClass()).list();
    }

    @Override
    public void deleteById(Serializable id) {
        String q = "DELETE  FROM " + getEntityClass().getSimpleName() + " clazz WHERE clazz.id = :id ";
        getSession().createQuery(q).setParameter("id", id).executeUpdate();
    }
}
