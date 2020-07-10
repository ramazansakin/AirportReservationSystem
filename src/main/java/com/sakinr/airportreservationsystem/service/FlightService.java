package com.sakinr.airportreservationsystem.service;

import com.sakinr.airportreservationsystem.entity.Flight;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getFlight(Integer id);

    void addFlight(@RequestBody Flight flight);

    Flight updateFlight(@RequestBody Flight flight);

    boolean deleteFlight(Integer id);

}
