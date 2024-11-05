package com.sccon.geospatial.challenge.translator;

import com.sccon.geospatial.challenge.domain.Person;
import com.sccon.geospatial.challenge.dto.PersonRequest;
import com.sccon.geospatial.challenge.dto.PersonResponse;

import java.util.List;

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
                .birth(request.getBirth())
                .admission(request.getAdmission())
                .build();
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
