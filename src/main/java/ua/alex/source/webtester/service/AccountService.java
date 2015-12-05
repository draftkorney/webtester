package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Account;

import java.util.List;

public interface AccountService {

    void save(Account account);
    void update(Account account);

    List<Account> getAll(int row, int count);

}
