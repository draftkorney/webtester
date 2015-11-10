package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Account;

public interface AccountService {

    void save(Account account);
    void update(Account account);

}
