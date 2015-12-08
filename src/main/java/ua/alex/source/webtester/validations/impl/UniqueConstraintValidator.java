package ua.alex.source.webtester.validations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.alex.source.webtester.service.AccountService;
import ua.alex.source.webtester.validations.Unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueConstraintValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private AccountService accountService;

    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return accountService.isExistField(value, this.fieldName);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Model does not have " + this.fieldName + " field");
        }
    }
}
