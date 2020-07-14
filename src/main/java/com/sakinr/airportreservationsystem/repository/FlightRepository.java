package com.sakinr.airportreservationsystem.repository;

import com.sakinr.airportreservationsystem.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Flight getByCode(String code);

    List<Flight> getAllByDepartureDateBetween(Date startDate, Date endDate);

}