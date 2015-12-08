package ua.alex.source.webtester.validations;


import ua.alex.source.webtester.validations.impl.UniqueConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {

    String message() default "{must.be.unique}";

    Class<?>[] groups() default {};

    String fieldName() default "";

    Class<? extends Payload>[] payload() default {};
}
