package ua.alex.source.webtester.dao.impl;

import ua.alex.source.webtester.dao.AccountDao;
import ua.alex.source.webtester.entities.Account;

import java.util.List;

public class AccountDaoImpl extends AbstractEntityDao<Account> implements AccountDao {
    @Override
    public List<Account> getAccounts(int row, int count) {
        return null;
    }

    @Override
    public Account getByEmail(String email) {
        return null;
    }

    @Override
    public Account getByLogin(String login) {
        return null;
    }
}
