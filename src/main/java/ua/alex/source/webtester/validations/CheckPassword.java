package ua.alex.source.webtester.validations;

import ua.alex.source.webtester.validations.impl.PasswordConstraintValidator;

import java.lang.annotation.*;

@Documented
@net.sf.oval.configuration.annotation.Constraint(checkWith = PasswordConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPassword {
    String message() default "not.correct";
}
