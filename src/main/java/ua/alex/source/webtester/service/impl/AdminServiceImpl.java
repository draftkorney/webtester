package ua.alex.source.webtester.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.alex.source.webtester.components.EntityBuilder;
import ua.alex.source.webtester.dao.AccountDao;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.forms.NewAccount;
import ua.alex.source.webtester.service.AdminService;
import ua.alex.source.webtester.service.EmailService;
import ua.alex.source.webtester.utils.ReflectionUtils;

import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private EntityBuilder entityBuilder;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional(readOnly = false, rollbackFor = {InvalidUserInputException.class, RuntimeException.class})
    public void addNewAccount(NewAccount newAccount) {
        Account account = entityBuilder.buildAccount();
        ReflectionUtils.copyByFields(account, newAccount);
        account.setPassword(UUID.randomUUID().toString().substring(0, 7));
        accountDao.save(account);
        emailService.confirmNewUser(account);
    }
}

