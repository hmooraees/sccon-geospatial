package com.sccon.geospatial.challenge.translator;

import com.sccon.geospatial.challenge.domain.Person;
import com.sccon.geospatial.challenge.dto.PersonPartialRequest;
import com.sccon.geospatial.challenge.dto.PersonRequest;
import com.sccon.geospatial.challenge.dto.PersonResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PersonTranslate {

    public static List<PersonResponse> toResponseList(List<Person> personList) {
        return personList.stream()
                .map(person ->
                        PersonResponse.builder()
                                .id(person.getId())
                                .name(person.getName())
                                .birth(person.getBirth())
                                .admission(person.getAdmission())
                                .build())
                .toList();
    }

    public static Person toDomain(PersonRequest request) {
        return Person.builder()
                .id(request.getId())
                .name(request.getName())
                .birth(LocalDate.parse(request.getBirth()))
                .admission(LocalDate.parse(request.getAdmission()))
                .build();
    }

    public static Person toDomain(PersonPartialRequest request) {
        Person.PersonBuilder builder = Person.builder();

        if (Objects.nonNull(request.getName()) && !request.getName().isEmpty()) {
            builder.name(request.getName());
        }

        if (Objects.nonNull(request.getBirth())) {
            builder.birth(LocalDate.parse(request.getBirth()));
        }

        if (Objects.nonNull(request.getAdmission())) {
            builder.admission(LocalDate.parse(request.getAdmission()));
        }

        return builder.build();
    }

    public static PersonResponse toResponse(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .birth(person.getBirth())
                .admission(person.getAdmission())
                .build();
    }
}
