package ua.alex.source.webtester.dao;


import ua.alex.source.webtester.entities.Account;

import java.util.List;

public interface AccountDao extends IEntityDao<Account> {
    List<Account> getAccounts(int row, int count);
    int countUsers();

    Account getByEmail(String email);

    Account getByLogin(String login);

    boolean isExistValueField(String value, String field);

    Account getByUniqueField(String uniqueValue, String uniqueField);

    void changeUserActivity(Long idAccount);
}
