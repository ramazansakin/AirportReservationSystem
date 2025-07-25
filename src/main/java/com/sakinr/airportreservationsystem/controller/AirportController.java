package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.model.dto.AirportDTO;
import com.sakinr.airportreservationsystem.model.mapper.AirportMapper;
import com.sakinr.airportreservationsystem.service.AirportService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/airports")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public List<AirportDTO> getAllAirports() {
        List<Airport> allAirports = airportService.getAllAirports();
        return allAirports.stream().map(AirportMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public Airport getAirport(@PathVariable @Min(1) Integer id) {
        return airportService.getAirport(id);
    }

    @PostMapping(value = "/create")
    public void saveAirport(@Valid @RequestBody Airport airport) {
        airportService.addAirport(airport);
    }

    @PutMapping(value = "/update")
    public Airport updateAirport(@Valid @RequestBody Airport airport) {
        return airportService.updateAirport(airport);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteAirport(@RequestParam @Min(1) Integer id) {
        return airportService.deleteAirport(id);
    }

}
