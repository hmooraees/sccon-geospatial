package com.sccon.geospatial.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Locale;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Person implements Comparable<Person>{
    private Long id;
    private String name;
    private LocalDate birth;
    private LocalDate admission;

    @Override
    public int compareTo(Person employee) {
        return this.getName().toLowerCase(Locale.ROOT).compareTo(employee.getName().toLowerCase(Locale.ROOT));
    }
}
