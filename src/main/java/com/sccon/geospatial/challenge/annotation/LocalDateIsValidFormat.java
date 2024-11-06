package com.sccon.geospatial.challenge.annotation;

import com.sccon.geospatial.challenge.validator.LocalDateFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalDateFormatValidator.class)
public @interface LocalDateIsValidFormat {

    String message() default "Invalid date format. The valid format is yyyy-MM-dd.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
