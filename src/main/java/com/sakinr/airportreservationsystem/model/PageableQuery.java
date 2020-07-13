package com.sakinr.airportreservationsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableQuery {

    @NotNull
    @Min(1)
    private int page;
    @NotNull
    @Min(1)
    private int pageSize;
    private String sortBy;
    private String sortDirection;
}
