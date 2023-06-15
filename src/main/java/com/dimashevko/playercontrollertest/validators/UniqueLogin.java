package com.dimashevko.playercontrollertest.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueLoginValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueLogin {
    String message() default "Login must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
