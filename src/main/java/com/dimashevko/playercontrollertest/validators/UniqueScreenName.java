package com.dimashevko.playercontrollertest.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueScreenNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueScreenName {
    String message() default "Screen name must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
