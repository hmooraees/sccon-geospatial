package com.sccon.geospatial.challenge.service;

import com.sccon.geospatial.challenge.domain.Person;
import com.sccon.geospatial.challenge.exception.PersonAgeOutputException;
import com.sccon.geospatial.challenge.exception.PersonConflictException;
import com.sccon.geospatial.challenge.exception.PersonNotFoundException;
import com.sccon.geospatial.challenge.exception.PersonSalaryOutputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class PersonService {

    private static final List<String> AGE_OUTPUT_TYPE = List.of("days", "months", "years");

    private static final List<String> SALARY_OUTPUT_TYPE = List.of("min", "full");

    private static final BigDecimal INITIAL_SALARY = new BigDecimal("1558");

    private static final BigDecimal MIN_SALARY = new BigDecimal("1302");

    private static final BigDecimal PERCENT_PER_YEAR = new BigDecimal("0.18");

    private static final BigDecimal PLUS_PER_YEAR = new BigDecimal("500");

    private final Map<Long, Person> personListConfig;

    public List<Person> getAllPerson() {
        List<Person> personList = new ArrayList<>(this.personListConfig.values());

        Collections.sort(personList);

        return personList;
    }

    public Person createPerson(Person person) {
        if (Objects.isNull(person.getId())) {
            long id = this.personListConfig.keySet().stream()
                    .max(Long::compareTo)
                    .orElse(0L) + 1L;

            Person newPerson = Person.builder()
                    .id(id)
                    .name(person.getName())
                    .birth(person.getBirth())
                    .admission(person.getAdmission())
                    .build();

            this.personListConfig.put(newPerson.getId(), newPerson);

            return newPerson;
        }

        if (this.validatePersonInMap(person.getId())) {
            throw new PersonConflictException();
        }

        this.personListConfig.put(person.getId(), person);

        return person;
    }

    public Person deletePerson(Long id) {
        this.validateExists(id);

        Person person = this.personListConfig.get(id);

        this.personListConfig.remove(id);

        return person;
    }

    public Person updatePerson(Long id, Person person) {
        this.validateExists(id);

        Person newPerson = Person.builder()
                .id(id)
                .name(person.getName())
                .birth(person.getBirth())
                .admission(person.getAdmission())
                .build();

        this.personListConfig.put(id, newPerson);

        return newPerson;
    }

    public Person updateFieldPerson(Long id, Person person) {
        this.validateExists(id);

        Person newPerson = this.personListConfig.get(id);

        if (Objects.nonNull(person.getName())) {
            newPerson.setName(person.getName());
        }

        if (Objects.nonNull(person.getBirth())) {
            newPerson.setBirth(person.getBirth());
        }

        if (Objects.nonNull(person.getAdmission())) {
            newPerson.setAdmission(person.getAdmission());
        }

        this.personListConfig.put(id, newPerson);

        return newPerson;
    }

    public Person getPerson(Long id) {
        this.validateExists(id);

        return this.personListConfig.get(id);
    }

    public Long getAgePerson(Long id, String output) {
        this.validateExists(id);

        if (!AGE_OUTPUT_TYPE.contains(output)) {
            throw new PersonAgeOutputException();
        }

        Person person = this.personListConfig.get(id);

        return switch (output.toLowerCase(Locale.ROOT)) {
            case "days" -> ChronoUnit.DAYS.between(person.getBirth(), LocalDate.now());
            case "months" -> ChronoUnit.MONTHS.between(person.getBirth(), LocalDate.now());
            case "years" -> ChronoUnit.YEARS.between(person.getBirth(), LocalDate.now());
            default -> 0L;
        };
    }

    public Double getSalaryPerson(Long id, String output) {
        this.validateExists(id);

        if (!SALARY_OUTPUT_TYPE.contains(output)) {
            throw new PersonSalaryOutputException();
        }

        Person person = this.personListConfig.get(id);

        long yearsDiff = ChronoUnit.YEARS.between(person.getAdmission(), LocalDate.now());

        BigDecimal actualSalary = INITIAL_SALARY;

        for (int i = 0; i < yearsDiff; i++) {
            actualSalary = actualSalary.add(actualSalary.multiply(PERCENT_PER_YEAR).add(PLUS_PER_YEAR));
        }

        if (output.toLowerCase(Locale.ROOT).equals("full")) {
            return actualSalary.setScale(2, RoundingMode.CEILING).doubleValue();
        }

        if (output.toLowerCase(Locale.ROOT).equals("min")) {
            return actualSalary.divide(MIN_SALARY, 2, RoundingMode.CEILING).doubleValue();
        }

        return null;
    }

    private boolean validatePersonInMap(Long id) {
        return this.personListConfig.keySet().stream()
                .anyMatch(key -> key.equals(id));
    }

    private void validateExists(Long id) {
        if (!this.validatePersonInMap(id)) {
            throw new PersonNotFoundException();
        }
    }
}
