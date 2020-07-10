package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Route;
import com.sakinr.airportreservationsystem.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/{id}")
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

}


