package ua.alex.source.webtester.validations;


import ua.alex.source.webtester.validations.impl.EmailConstraintValidator;

import java.lang.annotation.*;

@Documented
@net.sf.oval.configuration.annotation.Constraint(checkWith = EmailConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    String errorCode() default "not.correct";
    String message() default "not.correct";

}
