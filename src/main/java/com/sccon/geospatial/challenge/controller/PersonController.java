package com.sccon.geospatial.challenge.controller;

import com.sccon.geospatial.challenge.adapter.PersonAdapter;
import com.sccon.geospatial.challenge.dto.PersonRequest;
import com.sccon.geospatial.challenge.dto.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonAdapter adapter;

    @GetMapping
    public HttpEntity<List<PersonResponse>> getAllPerson() {
        List<PersonResponse> response = this.adapter.getAllPerson();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public HttpEntity<PersonResponse> create(@RequestBody PersonRequest request) {
        PersonResponse response = this.adapter.createPerson(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<PersonResponse> delete(@PathVariable Long id) {
        PersonResponse response = this.adapter.deletePerson(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<PersonResponse> update(@PathVariable Long id, @RequestBody PersonRequest request) {
        PersonResponse response = this.adapter.updatePerson(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}")
    public HttpEntity<PersonResponse> updateField(@PathVariable Long id, @RequestBody PersonRequest request) {
        PersonResponse response = this.adapter.updateFieldPerson(id, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<PersonResponse> get(@PathVariable Long id) {
        PersonResponse response = this.adapter.getPerson(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}/age")
    public HttpEntity<Long> getAge(@PathVariable Long id, @RequestParam String output) {
        Long response = this.adapter.getAgePerson(id, output);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}/salary")
    public HttpEntity<Double> getSalary(@PathVariable Long id, @RequestParam String output) {
        Double response = this.adapter.getSalaryPerson(id, output);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
