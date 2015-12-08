package ua.alex.source.webtester.service.impl;

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
import ua.alex.source.webtester.forms.NewAccount;
import ua.alex.source.webtester.service.AdminService;
import ua.alex.source.webtester.service.EmailService;
import ua.alex.source.webtester.utils.ReflectionUtils;

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
    public void addNewAccount(NewAccount newAccount) {
        Account account = entityBuilder.buildAccount();
        ReflectionUtils.copyByFields(account, newAccount);
        account.setPassword(UUID.randomUUID().toString().substring(0, 7));
        accountDao.save(account);

        List<Role> roles = roleDao.getListByIds(newAccount.getRoles());
        for (Role role : roles) {
            AccountRole ar = entityBuilder.buildAccountRole(account, role);
            accountRoleDao.save(ar);
        }

        emailService.confirmNewUser(account);
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
        }

        LOGGER.info("admin is already exist");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getUsers(int row, int count) {
        return accountDao.getAccounts(row, count);
    }


}

