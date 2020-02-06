package com.sakinr.airportreservationsystem.service;

import com.sakinr.airportreservationsystem.entity.Airport;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    List<Airport> getAllAirports();

    Optional<Airport> getAirport(Integer id);

    void addAirport(@RequestBody Airport airport);

    Airport updateAirport(@RequestBody Airport airport);

    boolean deleteAirport(Integer id);
}
