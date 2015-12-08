package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.forms.NewAccount;

import java.util.List;

public interface AdminService {

    void addNewAccount(NewAccount newAccount);

    void createAdmin();

    List<Account> getUsers(int row,int count);
}
