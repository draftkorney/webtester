package ua.alex.source.webtester.dao;


import ua.alex.source.webtester.entities.Account;

import java.util.List;

public interface AccountDao extends IEntityDao<Account> {
    List getAccounts(int row, int count);

    Account getByEmail(String email);

    Account getByLogin(String login);

}
