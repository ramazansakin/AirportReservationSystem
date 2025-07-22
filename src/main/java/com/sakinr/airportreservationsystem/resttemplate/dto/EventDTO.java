package com.sakinr.airportreservationsystem.resttemplate.dto;

import lombok.Data;

import jakarta.persistence.ManyToOne;

@Data
public class EventDTO {
    private Integer id;
    private String subject;
    private Integer duration;
    @ManyToOne
    private OrganizationDTO organization;
}
