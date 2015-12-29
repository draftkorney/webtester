package ua.alex.source.webtester.validations;


import ua.alex.source.webtester.validations.impl.UniqueConstraintValidator;

import java.lang.annotation.*;

@Documented
@net.sf.oval.configuration.annotation.Constraint(checkWith = UniqueConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
    String message() default "must.be.unique";
}
