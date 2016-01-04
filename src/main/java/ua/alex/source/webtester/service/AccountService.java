package ua.alex.source.webtester.service;


import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.forms.AccountForm;
import ua.alex.source.webtester.forms.EditAccountForm;

import java.sql.Timestamp;
import java.util.List;

public interface AccountService {

    void save(Account account);
    void update(Account account);

    int countUsers();

    List<Account> getAll(int row, int count);

    Account getByLogin(String login);
    Account getByEmail(String email);

    boolean isExistField(String value, String field) throws NoSuchFieldException;

    Account getById(Long idAccount);

    AccountForm convertToAccountForm(Long idAccount);
    EditAccountForm convertToAccountEditForm(Long idAccount);

    Account getByUniqueField(String uniqueValue, String uniqueField);

    void update(EditAccountForm profile);

    void deleteExpiredAccount(Timestamp dayOff);
}
