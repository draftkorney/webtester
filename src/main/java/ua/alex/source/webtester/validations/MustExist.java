package ua.alex.source.webtester.validations;

import ua.alex.source.webtester.validations.impl.MustExistConstraintValidator;

import java.lang.annotation.*;

@Documented
@net.sf.oval.configuration.annotation.Constraint(checkWith = MustExistConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MustExist {
    String errorCode() default "must.exist";
    String message() default  "must.exist";
}
