package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping
    String welcome() {
        return "Welcome to Airport Service!";
    }

    @GetMapping(value = "/get")
    List<Airport> getAllTickets() {
        return airportService.getAllAirports();
    }

    @PostMapping(value = "/get")
    Airport getAirport(@RequestParam(value = "id", required = true) Integer id) {
        return airportService.getAirport(id).get();
    }

    @PostMapping(value = "/save")
    void saveAirport(@RequestBody Airport airport) {
        airportService.addAirport(airport);
    }

    @PutMapping(value = "/update")
    Airport updateAirport(@RequestBody Airport airport) {
        return airportService.updateAirport(airport);
    }

    @DeleteMapping(value = "/delete")
    boolean deleteAirport(@RequestParam Integer id) {
        return airportService.deleteAirport(id);
    }

}
