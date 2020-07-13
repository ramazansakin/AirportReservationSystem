package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public String welcome() {
        return "Welcome to Flight Service!";
    }

    @GetMapping(value = "/all")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping(value = "/{id}")
    public Flight getFlight(@PathVariable(value = "id") Integer id) {
        return flightService.getFlight(id);
    }

    @PostMapping(value = "/create")
    public void saveFlight(@Valid @RequestBody Flight flight) {
        flightService.addFlight(flight);
    }

    @PutMapping(value = "/update")
    public Flight updateFlight(@Valid @RequestBody Flight flight) {
        return flightService.updateFlight(flight);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteFlight(@RequestParam Integer id) {
        return flightService.deleteFlight(id);
    }

}
