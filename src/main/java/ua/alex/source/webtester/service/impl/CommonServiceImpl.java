package ua.alex.source.webtester.service.impl;

import com.restfb.types.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.alex.source.webtester.components.EntityBuilder;
import ua.alex.source.webtester.dao.AccountDao;
import ua.alex.source.webtester.dao.AccountRegistrationDao;
import ua.alex.source.webtester.dao.AccountRoleDao;
import ua.alex.source.webtester.dao.RoleDao;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.entities.AccountRegistration;
import ua.alex.source.webtester.entities.AccountRole;
import ua.alex.source.webtester.entities.Role;
import ua.alex.source.webtester.exceptions.InvalidUserInputException;
import ua.alex.source.webtester.forms.ForgotPasswordForm;
import ua.alex.source.webtester.forms.SignUpForm;
import ua.alex.source.webtester.service.CommonService;
import ua.alex.source.webtester.service.EmailService;
import ua.alex.source.webtester.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service("commonService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommonServiceImpl implements CommonService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AccountRoleDao accountRoleDao;

    @Autowired
    private AccountRegistrationDao registrationDao;

    @Autowired
    private EntityBuilder entityBuilder;

    @Autowired
    private EmailService emailService;

    public CommonServiceImpl() {
        super();
    }

    @Override
    public Account login(String login, String password, int role) throws InvalidUserInputException {
        Account a = accountDao.getByLogin(login);
        if (a == null) {
            throw new InvalidUserInputException("Bad credentials");
        }
        if (!StringUtils.equals(password, a.getPassword())) {
            throw new InvalidUserInputException("Bad credentials");
        }
        if (!a.getActive()) {
            throw new InvalidUserInputException("Account is not active");
        }
        boolean found = false;
        for (AccountRole ar : a.getAccountRoles()) {
            if (ar.getRole().getIdRole() == role) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidUserInputException("Current account does not have selected role");
        }
        return a;
    }

    protected Account signUp(SignUpForm form, boolean sendVerificationEmail, boolean sendPasswordToEmail) throws InvalidUserInputException {
        Account a = entityBuilder.buildAccount();
        ReflectionUtils.copyByFields(a, form);
        a.setConfirm(false);
        accountDao.save(a);

        Role r = roleDao.getStudentRole();
        AccountRole ar = entityBuilder.buildAccountRole(a, r);
        accountRoleDao.save(ar);

        List<AccountRole> accountRoles = new ArrayList<>();
        accountRoles.add(ar);
        a.setAccountRoles(accountRoles);

        if (sendVerificationEmail) {
            AccountRegistration accountRegistration = entityBuilder.buildAccountRegistration(a);
            registrationDao.save(accountRegistration);
            emailService.sendVerificationEmail(accountRegistration);
        }
        if (sendPasswordToEmail) {
            emailService.sendGeneratedPasswordToEmail();
        }
        return a;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {InvalidUserInputException.class, RuntimeException.class})
    public Account signUp(SignUpForm form) throws InvalidUserInputException {
        return signUp(form, true, false);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {InvalidUserInputException.class, RuntimeException.class})
    public void activateAccount(String hashCode) {
        AccountRegistration accountRegistration = registrationDao.getAccountRegistrationByHash(hashCode);
        Account account = accountRegistration.getAccount();
        account.setConfirm(true);
        accountDao.update(account);
    }

    @Override
    public List<Role> listAllRoles() {
        return roleDao.findAll();
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {InvalidUserInputException.class, RuntimeException.class})
    public Account login(User user) throws InvalidUserInputException {
        Account a = accountDao.getByLogin(user.getEmail());
        if (a != null) {
            return a;
        } else {
            SignUpForm form = new SignUpForm();
            form.setEmail(user.getEmail());
            form.setFio(user.getName());
            form.setLogin(user.getEmail());

            UUID pwd = UUID.randomUUID();//generate temp password
            form.setPassword(pwd.toString());
            form.setConfirmPass(pwd.toString());

            return signUp(form, false, true);
        }
    }

    @Override
    public Account getProfile(long idAccount) {
        return accountDao.getById(idAccount);
    }

    @Override
    public void sendForgotPassword(ForgotPasswordForm email) {
        Account account = accountDao.getByEmail(email.getEmail());
        emailService.sendForgotPassword(account);
    }
}
