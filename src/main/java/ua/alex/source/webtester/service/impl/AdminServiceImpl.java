package ua.alex.source.webtester.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.alex.source.webtester.components.EntityBuilder;
import ua.alex.source.webtester.dao.AccountDao;
import ua.alex.source.webtester.dao.AccountRoleDao;
import ua.alex.source.webtester.dao.RoleDao;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRole;
import ua.alex.source.webtester.entities.Role;
import ua.alex.source.webtester.exceptions.InvalidUserInputException;
import ua.alex.source.webtester.forms.AccountForm;
import ua.alex.source.webtester.service.AdminService;
import ua.alex.source.webtester.service.EmailService;
import ua.alex.source.webtester.utils.ReflectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);

    @Autowired
    private EntityBuilder entityBuilder;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AccountRoleDao accountRoleDao;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional(readOnly = false, rollbackFor = {InvalidUserInputException.class, RuntimeException.class})
    public void saveOrUpdateAccount(AccountForm accountForm) {
        boolean isEmailChange = false;
        boolean isLoginChange = false;

        Long idAccount = accountForm.getIdAccount();
        Account account;


        if (idAccount == null) {
            account = entityBuilder.buildAccount();
            account.setPassword(UUID.randomUUID().toString().substring(0, 7));

        } else {
            account = accountDao.getById(idAccount);
            account.setUpdated(new Timestamp(System.currentTimeMillis()));
            accountRoleDao.deleteRolesByAccountId(account.getIdAccount());
            isEmailChange = StringUtils.equalsIgnoreCase(accountForm.getEmail(), account.getEmail());
            isLoginChange = StringUtils.equals(accountForm.getLogin(), account.getLogin());
        }

        ReflectionUtils.copyByFields(account, accountForm);
        accountDao.save(account);

        List<Role> roles = roleDao.getListByIds(accountForm.getRoles());

        for (Role role : roles) {
            AccountRole ar = entityBuilder.buildAccountRole(account, role);
            accountRoleDao.save(ar);
        }

        if (idAccount == null) {
            emailService.confirmNewUser(account);
        } else if (isEmailChange || isLoginChange) {
            emailService.sendNewEmailOrLogin(account, isLoginChange, isEmailChange);
        }

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {InvalidUserInputException.class, RuntimeException.class})
    public void createAdmin() {
        if (accountDao.getByLogin("admin") == null) {
            Account admin = entityBuilder.buildAccount();
            admin.setPassword("admin");
            admin.setLogin("admin");
            admin.setEmail("admin@admin.com");
            admin.setFio("admin");
            accountDao.save(admin);

            Role r = roleDao.getByName("administrator");
            AccountRole ar = entityBuilder.buildAccountRole(admin, r);
            accountRoleDao.save(ar);
            LOGGER.info("Created admin account");
        } else {
            LOGGER.info("admin is already exist");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getUsers(int row, int count) {
        return accountDao.getAccounts(row, count);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {InvalidUserInputException.class, RuntimeException.class})
    public void changeUserActivity(Long idAccount) {
        accountDao.changeUserActivity(idAccount);
    }
}

