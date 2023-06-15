package com.dimashevko.playercontrollertest.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeValidator implements ConstraintValidator<AgeConstraint, Integer> {
    private int minAge;
    private int maxAge;

    @Override
    public void initialize(AgeConstraint constraintAnnotation) {
        minAge = constraintAnnotation.min();
        maxAge = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext context) {
        if (age == null) {
            return true;
        }

        LocalDate today = LocalDate.now();
        LocalDate birthDate = today.minusYears(age);

        long yearsBetween = ChronoUnit.YEARS.between(birthDate, today);
        return yearsBetween >= minAge && yearsBetween <= maxAge;
    }
}







