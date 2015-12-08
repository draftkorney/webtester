package ua.alex.source.webtester.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.alex.source.webtester.dao.AccountDao;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.service.AccountService;
import ua.alex.source.webtester.utils.ReflectionUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    @Transactional
    public List<Account> getAll(int row, int count) {
        return accountDao.getAccounts(0, 10);
    }

    @Override
    public boolean isExistField(String value, String field) throws NoSuchFieldException {
        ReflectionUtils.getAccessibleField(Account.class, field);
        return accountDao.isExistValueField(value, field);
    }
}
