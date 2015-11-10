package ua.alex.source.webtester.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.alex.source.webtester.dao.AccountDao;
import ua.alex.source.webtester.entities.Account;

import java.util.List;

@Repository
public class AccountDaoImpl extends AbstractEntityDao<Account> implements AccountDao {

    @Override
    public List<Account> getAccounts(int row, int count) {
        return getSession().createCriteria(getEntityClass()).setMaxResults(count).setFirstResult(row).list();
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
    protected Class<Account> getEntityClass() {
        return Account.class;
    }
}
