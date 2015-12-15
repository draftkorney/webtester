package ua.alex.source.webtester.validations.impl;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.service.AccountService;
import ua.alex.source.webtester.utils.SpringUtil;
import ua.alex.source.webtester.validations.MustExist;


public class MustExistConstraintValidator extends AbstractAnnotationCheck<MustExist> {

    private AccountService accountService;

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) {
        String email = (String) valueToValidate;
        Account account = getAccountService().getByEmail(email);

        return account != null;

    }

    private AccountService getAccountService() {
        if (accountService == null) {
            accountService = (AccountService) SpringUtil.getBean(AccountService.class);
        }
        return accountService;
    }
}
