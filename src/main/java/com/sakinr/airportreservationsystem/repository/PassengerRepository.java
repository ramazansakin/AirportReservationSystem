package com.sakinr.airportreservationsystem.repository;

import com.sakinr.airportreservationsystem.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
