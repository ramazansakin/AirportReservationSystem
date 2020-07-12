package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Route;
import com.sakinr.airportreservationsystem.model.PageableQuery;
import com.sakinr.airportreservationsystem.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/route")
public class RouteController {

    private final RouteService routeService;

    @GetMapping(value = "/all")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "/{id}")
    public Route getRoute(@PathVariable(value = "id") Integer id) {
        return routeService.getRoute(id);
    }

    @PostMapping(value = "/create")
    public void saveRoute(@RequestBody Route route) {
        routeService.addRoute(route);
    }

    @PutMapping(value = "/update")
    public Route updateRoute(@RequestBody Route route) {
        return routeService.updateRoute(route);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteRoute(@RequestParam Integer id) {
        return routeService.deleteRoute(id);
    }

    // You can all both of them, the v1s and v2s needs to be equal
    // just see that you can create dynamic queries
    // actually, you dont need to create any method if you have with one via hibernate
    // but you dont have, you can create and customize one in relax
    @GetMapping(value = "/v1/departure-airport/{dep_id}/arrival-airport/{arr_id}")
    public ResponseEntity<List<Route>> getRoutesByDepartureAndArrivalAirportV1(
            @PathVariable Integer dep_id,
            @PathVariable Integer arr_id
    ) {
        List<Route> routes = routeService.getRoutesByDepartureAirportAndArrivalAirportByCustomRepo(dep_id, arr_id);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @PostMapping(value = "/v2/departure-airport/{dep_id}/arrival-airport/{arr_id}")
    public ResponseEntity<Page<Route>> getRoutesByDepartureAndArrivalAirportV2(
            @RequestBody PageableQuery query,
            @PathVariable Integer dep_id,
            @PathVariable Integer arr_id
    ) {
        Page<Route> routes = routeService.getRoutesByDepartureAirportAndArrivalAirportByDefault(query, dep_id, arr_id);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/departure-airport/{dep_id}")
    public ResponseEntity<Route> getOneByDepartureIdV1(
            @PathVariable Integer dep_id
    ) {
        Route route = routeService.getFirstRouteByDepartureAirportByCustomRepo(dep_id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

//    @GetMapping(value = "/v2/departure-airport/{dep_id}")
//    public ResponseEntity<Route> getOneByDepartureIdV2(
//            @PathVariable Integer dep_id
//    ) {
//        Route route = routeService.getFirstRouteByDepartureAirportByDefault(dep_id);
//        return new ResponseEntity<>(route, HttpStatus.OK);
//    }

}


