package ua.alex.source.webtester.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.alex.source.webtester.dao.AccountDao;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.exceptions.InvalidUserInputException;
import ua.alex.source.webtester.forms.AccountForm;
import ua.alex.source.webtester.service.AccountService;
import ua.alex.source.webtester.utils.CommonUtils;
import ua.alex.source.webtester.utils.ReflectionUtils;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class})
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAll(int row, int count) {
        return accountDao.findAll(0, 10);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getByLogin(String login) {
        return accountDao.getByLogin(login);
    }

    @Override
    public Account getByUniqueField(String uniqueValue, String uniqueField) {
        return accountDao.getByUniqueField(uniqueValue, uniqueField);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getByEmail(String email) {
        return accountDao.getByEmail(email);
    }

    @Override
    public boolean isExistField(String value, String field) throws NoSuchFieldException {
        if (StringUtils.isBlank(value)) return true;

        ReflectionUtils.getAccessibleField(Account.class, field);
        return accountDao.isExistValueField(value, field);
    }

    @Override
    public int countUsers() {
        return accountDao.countUsers();
    }

    @Override
    public Account getById(Long idAccount) {
        return accountDao.getById(idAccount);
    }

    @Override
    public AccountForm convertToAccountForm(Long idAccount) {
        Account account = accountDao.getById(idAccount);

        AccountForm accountForm = new AccountForm();
        ReflectionUtils.copyByFields(accountForm, account);
        accountForm.setRoles(CommonUtils.convertRoles(account.getAccountRoles()));

        return accountForm;
    }
}
