package ua.alex.source.webtester.validations.impl;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import ua.alex.source.webtester.validations.Email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailConstraintValidator extends AbstractAnnotationCheck<Email> {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext oValContext, Validator validator) throws OValException {
        if (valueToValidate == null) return true;
        Matcher matcher = pattern.matcher((String) valueToValidate);
        return matcher.matches();
    }
}
