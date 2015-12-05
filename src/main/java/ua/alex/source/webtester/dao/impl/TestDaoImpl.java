package ua.alex.source.webtester.dao.impl;

import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.RoleDao;
import ua.alex.source.webtester.dao.TestDao;
import ua.alex.source.webtester.entities.Role;
import ua.alex.source.webtester.entities.Test;

@Repository
public class TestDaoImpl extends AbstractEntityDao<Test> implements TestDao {
    @Override
    protected Class<Test> getEntityClass() {
        return Test.class;
    }
}
