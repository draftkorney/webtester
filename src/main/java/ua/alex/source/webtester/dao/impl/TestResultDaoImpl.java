package ua.alex.source.webtester.dao.impl;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.TestResultDao;
import ua.alex.source.webtester.entities.TestResult;

import java.util.List;

@Repository
public class TestResultDaoImpl extends AbstractEntityDao<TestResult> implements TestResultDao {
    @Override
    protected Class<TestResult> getEntityClass() {
        return TestResult.class;
    }

    @Override
    public List<TestResult> getResult(long currentIdAccount) {
        String q = "FROM TestResult t WHERE  t.account.id = :idAccount ORDER BY t.updated DESC";
        Query query = getSession().createQuery(q);
        query.setParameter("idAccount", currentIdAccount);
        return query.list();
    }

    @Override
    public int getResultCount(long currentIdAccount) {
        String q = "FROM TestResult t WHERE  t.account.id = :idAccount ORDER BY t.updated DESC";
        Query query = getSession().createQuery(q);
        query.setParameter("idAccount", currentIdAccount);
        return query.list().size();
    }

    @Override
    public List<TestResult> getResult(long currentIdAccount, Integer page, Integer count) {
        int first = (page - 1) * count;

        String q = "FROM TestResult t WHERE  t.account.id = :idAccount ORDER BY t.updated DESC";
        Query query = getSession().createQuery(q);
        query.setParameter("idAccount", currentIdAccount);
        query.setFirstResult(first);
        query.setMaxResults(count);

        return query.list();
    }
}
