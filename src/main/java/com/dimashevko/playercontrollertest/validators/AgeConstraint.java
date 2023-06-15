package com.dimashevko.playercontrollertest.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeConstraint {
    String message() default "Invalid age";
    int min() default 16;
    int max() default 60;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}



