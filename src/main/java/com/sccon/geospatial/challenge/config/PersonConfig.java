package com.sccon.geospatial.challenge.config;

import com.sccon.geospatial.challenge.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class PersonConfig {

    private Map<Long, Person> person;

    @Bean
    public Map<Long, Person> personListConfig() {
        this.person = new HashMap<>();

        this.getPersonList().forEach(p -> this.person.put(p.getId(), p));

        return this.person;
    }

    private List<Person> getPersonList() {
        List<Person> initialPerson = new ArrayList<>();

        initialPerson.add(
                Person.builder()
                .id(1L)
                .name("Henrique Moraes")
                .birth(LocalDate.of(1992, 10, 5))
                .admission(LocalDate.of(2010, 5, 15))
                .build()
        );

        initialPerson.add(
                Person.builder()
                        .id(2L)
                        .name("José da Silva")
                        .birth(LocalDate.of(2000, 4, 6))
                        .admission(LocalDate.of(2020, 5, 10))
                        .build()
        );

        initialPerson.add(
                Person.builder()
                        .id(3L)
                        .name("Ana Beatriz")
                        .birth(LocalDate.of(1990, 12, 31))
                        .admission(LocalDate.of(2022, 10, 24))
                        .build()
        );

        return initialPerson;
    }
}
