package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Route;
import com.sakinr.airportreservationsystem.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/route")
public class RouteController {

    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping(value = "/get")
    List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @PostMapping(value = "/get")
    Route getRoute(@RequestParam(value = "id", required = true) Integer id) {
        return routeService.getRoute(id).get();
    }

    @PostMapping(value = "/save")
    void saveRoute(@RequestBody Route route) {
        routeService.addRoute(route);
    }

    @PutMapping(value = "/update")
    Route updateRoute(@RequestBody Route route) {
        return routeService.updateRoute(route);
    }

    @DeleteMapping(value = "/delete")
    boolean deleteRoute(@RequestParam Integer id) {
        return routeService.deleteRoute(id);
    }

}
