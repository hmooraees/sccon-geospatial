package com.sccon.geospatial.challenge.exception;

import com.sccon.geospatial.challenge.dto.Error;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControlExceptionHandler {

    @ExceptionHandler(PersonConflictException.class)
    public HttpEntity<Error> handlerConflictPerson() {
        var error = Error.builder()
                .error("person_conflict_error")
                .message("Person with the given ID already exists.")
                .status(HttpStatus.CONFLICT.value())
                .build();

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public HttpEntity<Error> handlerNotFountPerson() {
        var error = Error.builder()
                .error("person_not_found_error")
                .message("Person not found.")
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(PersonAgeOutputException.class)
    public HttpEntity<Error> handlerAgeOutputPerson() {
        var error = Error.builder()
                .error("person_age_output_error")
                .message("Age output unrecognized.")
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(PersonSalaryOutputException.class)
    public HttpEntity<Error> handlerSalaryOutputPerson() {
        var error = Error.builder()
                .error("person_salary_output_error")
                .message("Salary output unrecognized.")
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handlerValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(error.getDefaultMessage());
        });

        var error = Error.builder()
                .error("person_field_required_error")
                .message(errors.toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> handlerConstraintViolation(ConstraintViolationException ex) {
        var error = Error.builder()
                .error("person_field_constraint_error")
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
