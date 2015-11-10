package ua.alex.source.webtester.dao;


public interface IEntityDao<T> {
    void save(T entity);
    void update(T entity);
}
