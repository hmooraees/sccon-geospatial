package com.sccon.geospatial.challenge.adapter;

import com.sccon.geospatial.challenge.domain.Person;
import com.sccon.geospatial.challenge.dto.PersonRequest;
import com.sccon.geospatial.challenge.dto.PersonResponse;
import com.sccon.geospatial.challenge.service.PersonService;
import com.sccon.geospatial.challenge.translator.PersonTranslate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonAdapter {

    private final PersonService service;

    public List<PersonResponse> getAllPerson() {
        return PersonTranslate.toResponseList(this.service.getAllPerson());
    }

    public PersonResponse createPerson(PersonRequest request) {
        Person person = PersonTranslate.toDomain(request);

        return PersonTranslate.toResponse(this.service.createPerson(person));
    }

    public PersonResponse deletePerson(Long id) {
        return PersonTranslate.toResponse(this.service.deletePerson(id));
    }

    public PersonResponse updatePerson(Long id, PersonRequest request) {
        Person person = PersonTranslate.toDomain(request);

        return PersonTranslate.toResponse(this.service.updatePerson(id, person));
    }

    public PersonResponse updateFieldPerson(Long id, PersonRequest request) {
        Person person = PersonTranslate.toDomain(request);

        return PersonTranslate.toResponse(this.service.updateFieldPerson(id, person));
    }

    public PersonResponse getPerson(Long id) {
        return PersonTranslate.toResponse(this.service.getPerson(id));
    }

    public Long getAgePerson(Long id, String output) {
        return this.service.getAgePerson(id, output);
    }

    public Double getSalaryPerson(Long id, String output) {
        return this.service.getSalaryPerson(id, output);
    }
}
