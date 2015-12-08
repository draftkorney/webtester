package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Account;

import java.util.List;

public interface AccountService {

    void save(Account account);
    void update(Account account);

    int countUsers();

    List<Account> getAll(int row, int count);

    Account getByLogin(String login);
    Account getByEmail(String email);

    boolean isExistField(String value, String field) throws NoSuchFieldException;

}
