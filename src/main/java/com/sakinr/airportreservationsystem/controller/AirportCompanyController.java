package com.sakinr.airportreservationsystem.controller;


import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.service.AirportCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport-company")
public class AirportCompanyController {

    private final AirportCompanyService airportCompanyService;

    @GetMapping
    public String welcome() {
        return "Welcome to Airport Service!";
    }

    @GetMapping(value = "/all")
    public List<AirportCompany> getAllAirportCompanies() {
        return airportCompanyService.getAllAirportCompanies();
    }

    @GetMapping(value = "/{id}")
    public AirportCompany getAirportCompany(@PathVariable @Min(1) Integer id) {
        return airportCompanyService.getAirportCompany(id);
    }

    @PostMapping(value = "/create")
    public void saveAirportCompany(@Valid @RequestBody AirportCompany airportCompany) {
        airportCompanyService.addAirportCompany(airportCompany);
    }

    @PutMapping(value = "/update")
    public AirportCompany updateAirportCompany(@RequestBody AirportCompany airportCompany) {
        return airportCompanyService.updateAirportCompany(airportCompany);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteAirportCompany(@RequestParam @Min(1) Integer id) {
        return airportCompanyService.deleteAirportCompany(id);
    }

    @PostMapping(value = "/add-flight")
    public boolean addNewFlight(@Valid @RequestBody Flight flight) {
        return airportCompanyService.addNewFlight(flight);
    }

    @PostMapping(value = "/buy-ticket")
    public Ticket buyTicket(@RequestParam @Min(1) Integer flight_id, @RequestParam @Min(1) Integer passenger_id) {
        return airportCompanyService.buyTicketForFlight(flight_id, passenger_id);
    }

    @PostMapping(value = "/cancel-ticket")
    public boolean cancelTicket(@RequestParam @Min(1) Integer ticket_id) {
        return airportCompanyService.cancelTicket(ticket_id);
    }

    @PostMapping(value = "/search-ticket")
    public Ticket searchTicket(@RequestParam @Min(1) Integer ticket_id) {
        return airportCompanyService.searchTicket(ticket_id);
    }

}
