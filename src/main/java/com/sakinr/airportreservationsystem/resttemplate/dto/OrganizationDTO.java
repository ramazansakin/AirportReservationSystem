package com.sakinr.airportreservationsystem.resttemplate.dto;

import lombok.Data;

import javax.persistence.OneToMany;
import java.util.List;

@Data
public class OrganizationDTO {
    private Integer id;
    private String name;
    @OneToMany
    private List<EventDTO> events;
}
