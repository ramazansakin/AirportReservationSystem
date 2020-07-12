package com.sakinr.airportreservationsystem.repository;

import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.entity.Route;
import com.sakinr.airportreservationsystem.repository.dynamic.RouteRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>, RouteRepositoryCustom {

    List<Route> findAllByDeparture_airportAndArrival_airport(Airport departure_airport, Airport arrival_airport);

//    Route findByDeparture_airport(Airport departure_airport);

}
