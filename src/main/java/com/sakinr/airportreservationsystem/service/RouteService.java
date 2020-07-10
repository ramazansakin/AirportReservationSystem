package com.sakinr.airportreservationsystem.service;

import com.sakinr.airportreservationsystem.entity.Route;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RouteService {
    List<Route> getAllRoutes();

    Route getRoute(Integer id);

    void addRoute(@RequestBody Route route);

    Route updateRoute(@RequestBody Route route);

    boolean deleteRoute(Integer id);

}
