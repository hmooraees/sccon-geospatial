package com.sccon.geospatial.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@JsonSerialize
@Data
public class PersonRequest {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth")
    private LocalDate birth;

    @JsonProperty("admission")
    private LocalDate admission;
}
