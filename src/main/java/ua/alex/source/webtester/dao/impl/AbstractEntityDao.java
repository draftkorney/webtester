package ua.alex.source.webtester.dao.impl;

import ua.alex.source.webtester.dao.IEntityDao;
import ua.alex.source.webtester.entities.IEntity;

public abstract class AbstractEntityDao<T> implements IEntityDao {
    @Override
    public void save(Object entity) {
        System.out.println("SAVE");
    }
}
