package ua.alex.source.webtester.dao.impl;

import ua.alex.source.webtester.dao.IEntityDao;
import ua.alex.source.webtester.entities.IEntity;

public abstract class AbstractEntityDao<T> implements IEntityDao<T> {

    @Override
    public void save(T entity) {
        System.out.println("SAVE");
    }
}
