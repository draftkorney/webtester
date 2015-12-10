package ua.alex.source.webtester.dao;


import java.io.Serializable;
import java.util.List;

public interface IEntityDao<T> {
    void save(T entity);

    void update(T entity);

    void deleteById(Serializable id);

    T getById(Serializable id);

    List<T> findAll();
}
