package com.sakinr.airportreservationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopAirport {
    private Integer airportId;
    private String airportName;
    private Long passengerCount;
}
