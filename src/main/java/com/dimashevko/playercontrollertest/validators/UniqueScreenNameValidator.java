package com.dimashevko.playercontrollertest.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class UniqueScreenNameValidator implements ConstraintValidator<UniqueScreenName, String> {

    @Override
    public void initialize(UniqueScreenName constraintAnnotation) {
        // Initialization if needed
    }

    @Override
    public boolean isValid(String screenName, ConstraintValidatorContext context) {
        // Add your logic to check if the screen name is unique
        // You can use a service or repository to perform the uniqueness check
        // For simplicity, let's assume a static list of screen names

        List<String> existingScreenNames = Arrays.asList("John", "Ana", "Borat");

        return !existingScreenNames.contains(screenName);
    }
}
