package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    FlightService flightService;

    @GetMapping
    String welcome() {
        return "Welcome to Flight Service!";
    }

    @GetMapping(value = "/get")
    List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping(value = "/get")
    Flight getFlight(@RequestParam(value = "id", required = true) Integer id) {
        return flightService.getFlight(id).get();
    }

    @PostMapping(value = "/save")
    void saveFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
    }

    @PutMapping(value = "/update")
    Flight updateFlight(@RequestBody Flight flight) {
        return flightService.updateFlight(flight);
    }

    @DeleteMapping(value = "/delete")
    boolean deleteFlight(@RequestParam Integer id) {
        return flightService.deleteFlight(id);
    }

    
}
