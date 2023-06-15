package com.dimashevko.playercontrollertest.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class UniqueScreenNameValidator implements ConstraintValidator<UniqueScreenName, String> {

    @Override
    public void initialize(UniqueScreenName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String screenName, ConstraintValidatorContext context) {

        List<String> existingScreenNames = Arrays.asList("John", "Ana", "Borat");

        return !existingScreenNames.contains(screenName);
    }
}
