package ua.alex.source.webtester.validations.impl;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import org.springframework.stereotype.Component;
import ua.alex.source.webtester.entities.Account;
import ua.alex.source.webtester.forms.AccountForm;
import ua.alex.source.webtester.service.AccountService;
import ua.alex.source.webtester.utils.SpringUtil;
import ua.alex.source.webtester.validations.Unique;

import java.util.Objects;

@Component
public class UniqueConstraintValidator extends AbstractAnnotationCheck<Unique> {

    private AccountService accountService;

    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) {
        if (valueToValidate == null) return true;

        Long idAccount = ((AccountForm) validatedObject).getIdAccount();
        String uniqueValue = (String) valueToValidate;
        String uniqueField = ((FieldContext) context).getField().getName();
        Account account = getAccountService().getByUniqueField(uniqueValue, uniqueField);

        if (account == null) return true;

        Long idFromDB = account.getIdAccount();

        return (idFromDB == null || (Objects.equals(idAccount, idFromDB)));
    }

    private AccountService getAccountService() {
        if (accountService == null) {
            accountService = (AccountService) SpringUtil.getBean(AccountService.class);
        }
        return accountService;
    }
}
