package com.sccon.geospatial.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sccon.geospatial.challenge.annotation.LocalDateIsValidFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@JsonSerialize
@Data
public class PersonPartialRequest {

    @JsonProperty("name")
    private String name;

    @LocalDateIsValidFormat(message = "Field birth with invalid date format. The valid format is yyyy-MM-dd.")
    @JsonProperty("birth")
    private String birth;

    @LocalDateIsValidFormat(message = "Field admission with invalid date format. The valid format is yyyy-MM-dd.")
    @JsonProperty("admission")
    private String admission;
}
