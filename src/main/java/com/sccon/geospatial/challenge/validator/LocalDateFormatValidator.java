package com.sccon.geospatial.challenge.validator;

import com.sccon.geospatial.challenge.annotation.LocalDateIsValidFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LocalDateFormatValidator implements ConstraintValidator<LocalDateIsValidFormat, String> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(date)) {
            return true;
        }

        try {
            LocalDate.parse(date, DATE_FORMATTER);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
