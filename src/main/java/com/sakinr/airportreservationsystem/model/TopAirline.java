package com.sakinr.airportreservationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopAirline {
    private Integer airlineId;
    private String airlineName;
    private Long flightCount;
    private Long passengerCount;
}
