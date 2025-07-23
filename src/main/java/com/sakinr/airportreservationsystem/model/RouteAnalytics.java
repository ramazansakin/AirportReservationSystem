package com.sakinr.airportreservationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteAnalytics {
    private String departureAirport;
    private String arrivalAirport;
    private Long flightCount;
}
