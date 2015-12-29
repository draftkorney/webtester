package ua.alex.source.webtester.validations.impl;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.configuration.annotation.AnnotationCheck;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import org.apache.commons.lang.StringUtils;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.forms.EditAccountForm;
import ua.alex.source.webtester.forms.IUnique;
import ua.alex.source.webtester.service.AccountService;
import ua.alex.source.webtester.utils.SpringUtil;
import ua.alex.source.webtester.validations.CheckPassword;
import ua.alex.source.webtester.validations.Email;

import java.util.Objects;

public class PasswordConstraintValidator extends AbstractAnnotationCheck<CheckPassword> {
    private AccountService accountService;

    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) {
        if (StringUtils.isBlank(String.valueOf(valueToValidate))) return true;

        Long idAccount = (Long) ((EditAccountForm) validatedObject).getId();
        String oldPass = (String) valueToValidate;
        Account account = getAccountService().getById(idAccount);

        return account == null || oldPass.equals(account.getPassword());

    }

    private AccountService getAccountService() {
        if (accountService == null) {
            accountService = (AccountService) SpringUtil.getBean(AccountService.class);
        }
        return accountService;
    }
}
