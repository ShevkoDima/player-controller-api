package com.dimashevko.playercontrollertest.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    @Override
    public void initialize(UniqueLogin constraintAnnotation) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {

        List<String> existingLogins = Arrays.asList("firstPlayer", "secondPlayer", "thirdPlayer");

        return !existingLogins.contains(login);
    }
}
