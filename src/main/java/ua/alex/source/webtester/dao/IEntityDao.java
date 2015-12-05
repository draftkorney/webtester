package ua.alex.source.webtester.dao;


import java.util.List;

public interface IEntityDao<T> {
    void save(T entity);
    void update(T entity);

    List<T> findAll();
}
