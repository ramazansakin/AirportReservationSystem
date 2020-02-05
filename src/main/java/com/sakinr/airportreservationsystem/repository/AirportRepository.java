package com.sakinr.airportreservationsystem.repository;

import com.sakinr.airportreservationsystem.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
