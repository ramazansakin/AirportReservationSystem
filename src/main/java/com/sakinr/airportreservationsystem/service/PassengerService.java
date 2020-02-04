package com.sakinr.airportreservationsystem.service;

import com.sakinr.airportreservationsystem.entity.Passenger;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    List<Passenger> getAllPassengers();

    Optional<Passenger> getPassenger(Integer id);

    void addPassenger(@RequestBody Passenger passenger);

    Passenger updatePassenger(@RequestBody Passenger passenger);

    boolean deletePassenger(Integer id);

}
