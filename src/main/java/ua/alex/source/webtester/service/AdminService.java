package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.forms.AccountForm;

import java.util.List;

public interface AdminService {

    void saveOrUpdateAccount(AccountForm accountForm);

    void createAdmin();

    List<Account> getUsers(int row,int count);

    void changeUserActivity(Long idAccount);
}
