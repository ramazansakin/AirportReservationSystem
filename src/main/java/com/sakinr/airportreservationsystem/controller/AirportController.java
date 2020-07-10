package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public String welcome() {
        return "Welcome to Airport Service!";
    }

    @GetMapping(value = "/all")
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping(value = "/{id}")
    public Airport getAirport(@PathVariable(value = "id") Integer id) {
        return airportService.getAirport(id);
    }

    @PostMapping(value = "/create")
    public void saveAirport(@RequestBody Airport airport) {
        airportService.addAirport(airport);
    }

    @PutMapping(value = "/update")
    public Airport updateAirport(@RequestBody Airport airport) {
        return airportService.updateAirport(airport);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteAirport(@RequestParam Integer id) {
        return airportService.deleteAirport(id);
    }

}
