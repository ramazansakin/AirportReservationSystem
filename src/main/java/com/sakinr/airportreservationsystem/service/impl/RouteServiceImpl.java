package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.entity.Route;
import com.sakinr.airportreservationsystem.exception.NotFoundException;
import com.sakinr.airportreservationsystem.model.PageableQuery;
import com.sakinr.airportreservationsystem.repository.RouteRepository;
import com.sakinr.airportreservationsystem.service.AirportService;
import com.sakinr.airportreservationsystem.service.RouteService;
import com.sakinr.airportreservationsystem.util.PageableRequestBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    private final AirportService airportService;

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRoute(Integer id) {
        Optional<Route> byId = routeRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Route"));
    }

    @Override
    public void addRoute(Route route) {
        routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public boolean deleteRoute(Integer id) {
        routeRepository.delete(getRoute(id));
        return true;
    }

    @Override
    public List<Route> getRoutesByDepartureAirportAndArrivalAirportByCustomRepo(Integer departure_airport_id, Integer arrival_airport_id) {
        Airport dep_airport = airportService.getAirport(departure_airport_id);
        Airport arr_airport = airportService.getAirport(arrival_airport_id);
        return routeRepository.getRoutesByDepartureAirportAndArrivalAirport(dep_airport.getId(), arr_airport.getId());
    }

    @Override
    public Page<Route> getRoutesByDepartureAirportAndArrivalAirportByDefault(PageableQuery query, Integer departure_airport_id, Integer arrival_airport_id) {
        Airport dep_airport = airportService.getAirport(departure_airport_id);
        Airport arr_airport = airportService.getAirport(arrival_airport_id);
        PageRequest p = PageableRequestBuilder.build(query);
        return routeRepository.findByDeparture_airportAndArrival_airport(p, dep_airport, arr_airport);
    }

    @Override
    public Route getFirstRouteByDepartureAirportByCustomRepo(Integer departure_airport_id) {
        Airport dep_airport = airportService.getAirport(departure_airport_id);
        return routeRepository.getFirstRouteByDepartureAirport(dep_airport.getId());
    }

    @Override
    public Route getFirstRouteByDepartureAirportByDefault(Integer departure_airport_id) {
        Airport dep_airport = airportService.getAirport(departure_airport_id);
        Optional<Route> byDepartureAirport = Optional.ofNullable(routeRepository.findByDepartureAirport(dep_airport));
        return byDepartureAirport.orElseThrow(() -> new NotFoundException("FirstRouteByDepartureAirport"));
    }

}
