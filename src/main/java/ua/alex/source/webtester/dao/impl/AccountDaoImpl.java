package ua.alex.source.webtester.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.alex.source.webtester.dao.AccountDao;
import ua.alex.source.webtester.entities.Account;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class AccountDaoImpl extends AbstractEntityDao<Account> implements AccountDao {

    @Override
    public List<Account> getAccounts(int row, int count) {
        int first = (row - 1) * count;
        return getSession().createCriteria(getEntityClass()).setMaxResults(count).setFirstResult(first).list();
    }

    @Override
    public Account getByEmail(String email) {
        return (Account) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public Account getByLogin(String login) {
        return (Account) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("login", login)).uniqueResult();
    }

    @Override
    public boolean isExistValueField(String value, String field) {
        return getSession().createCriteria(getEntityClass()).add(Restrictions.eq(field, value)).list().size() > 0;
    }

    @Override
    public Account getByUniqueField(String uniqueValue, String uniqueField) {
        return (Account) getSession().createCriteria(getEntityClass()).add(Restrictions.eq(uniqueField, uniqueValue)).uniqueResult();
    }

    @Override
    public int countUsers() {
        return getSession().createCriteria(getEntityClass()).list().size();
    }

    @Override
    public void changeUserActivity(Long idAccount) {
        getSession().createQuery("UPDATE Account a SET a.active = " +
                "CASE a.active " +
                "WHEN TRUE THEN FALSE " +
                "ELSE TRUE END, a.updated = :updated  " +
                "WHERE a.idAccount = :idAccount")
                .setParameter("idAccount", idAccount)
                .setParameter("updated", new Timestamp(System.currentTimeMillis())).executeUpdate();
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }
}
