package ua.alex.source.webtester.dao.impl;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.TestDao;
import ua.alex.source.webtester.entities.Test;

import java.util.List;

@Repository
public class TestDaoImpl extends AbstractEntityDao<Test> implements TestDao {

    @Override
    protected Class<Test> getEntityClass() {
        return Test.class;
    }

    @Override
    public int getCountTestsByAccountId(Long idAccount) {
        return getSession().createCriteria(getEntityClass()).createAlias("account", "a").add(Restrictions.eq("a.id", idAccount)).list().size();
    }

    @Override
    public List<Test> getTestByAccountId(int row, int count, Long idAccount) {
        int first = (row - 1) * count;

        String q = "FROM Test t WHERE  t.account.id = :idAccount";
        Query query = getSession().createQuery(q);
        query.setParameter("idAccount", idAccount);
        query.setFirstResult(first);
        query.setMaxResults(count);

        return query.list();
    }

    @Override
    public List<Test> findTestForPass(int row, int count) {
        String q = "FROM Test t WHERE t.idTest = " +
                "(SELECT DISTINCT q.test.idTest " +
                "FROM Question q WHERE q.test.idTest = t.idTest AND  q.answers.size >0)";
        Query query = getSession().createQuery(q);
        return query.list();
    }
}
