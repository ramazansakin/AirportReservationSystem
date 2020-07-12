package com.sakinr.airportreservationsystem.repository.dynamic;

import com.sakinr.airportreservationsystem.entity.Route;

import java.util.List;

public interface RouteRepositoryCustom {
    // sample interfaces to create and  test dynamic queries via JPA
    List<Route> getRoutesByDepartureAirportAndArrivalAirport(Integer departure_airport_id, Integer arrival_airport_id);

    Route getFirstRouteByDepartureAirport(Integer departure_airport_id);
}
