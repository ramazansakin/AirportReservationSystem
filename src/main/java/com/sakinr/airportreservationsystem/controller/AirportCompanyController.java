package com.sakinr.airportreservationsystem.controller;


import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.service.AirportCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/airportcompany")
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

    @PostMapping(value = "/{id}")
    public AirportCompany getAirportCompany(@PathVariable(value = "id") Integer id) {
        return airportCompanyService.getAirportCompany(id);
    }

    @PostMapping(value = "/create")
    public void saveAirportCompany(@RequestBody AirportCompany airportCompany) {
        airportCompanyService.addAirportCompany(airportCompany);
    }

    @PutMapping(value = "/update")
    public AirportCompany updateAirportCompany(@RequestBody AirportCompany airportCompany) {
        return airportCompanyService.updateAirportCompany(airportCompany);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteAirportCompany(@RequestParam Integer id) {
        return airportCompanyService.deleteAirportCompany(id);
    }

    @PostMapping(value = "/add-flight")
    public boolean addNewFlight(@RequestBody @Valid Flight flight) {
        return airportCompanyService.addNewFlight(flight);
    }

    @PostMapping(value = "/buy-ticket")
    public Ticket buyTicket(@RequestParam Integer flight_id, @RequestParam Integer passenger_id) {
        return airportCompanyService.buyTicketForFlight(flight_id, passenger_id);
    }

    @PostMapping(value = "/cancel-ticket")
    public boolean cancelTicket(@RequestParam Integer ticket_id) {
        return airportCompanyService.cancelTicket(ticket_id);
    }

    @PostMapping(value = "/search-ticket")
    public Ticket searchTicket(@RequestParam Integer ticket_id) {
        return airportCompanyService.searchTicket(ticket_id);
    }

}
