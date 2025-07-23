package com.sakinr.airportreservationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightAnalytics {
    private Integer flightId;
    private String flightCode;
    private String departureAirport;
    private String arrivalAirport;
    private Date departureDate;
    private Long ticketCount;
    private BigDecimal totalRevenue;
}
