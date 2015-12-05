package ua.alex.source.webtester.dao.impl;


import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.TestDao;
import ua.alex.source.webtester.dao.TestResultDao;
import ua.alex.source.webtester.entities.Test;
import ua.alex.source.webtester.entities.TestResult;

@Repository
public class TestResultDaoImpl extends AbstractEntityDao<TestResult> implements TestResultDao {
    @Override
    protected Class<TestResult> getEntityClass() {
        return TestResult.class;
    }
}
