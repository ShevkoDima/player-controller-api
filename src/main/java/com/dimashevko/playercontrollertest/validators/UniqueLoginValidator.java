package com.dimashevko.playercontrollertest.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    @Override
    public void initialize(UniqueLogin constraintAnnotation) {
        // Initialization if needed
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        // Add your logic to check if the login is unique
        // You can use a service or repository to perform the uniqueness check
        // For simplicity, let's assume a static list of logins

        List<String> existingLogins = Arrays.asList("firstPlayer", "secondPlayer", "thirdPlayer");

        return !existingLogins.contains(login);
    }
}
