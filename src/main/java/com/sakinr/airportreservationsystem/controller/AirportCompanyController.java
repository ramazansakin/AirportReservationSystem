package com.sakinr.airportreservationsystem.controller;


import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.service.AirportCompanyService;
import com.sakinr.airportreservationsystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airportcompany")
public class AirportCompanyController {
    @Autowired
    AirportCompanyService airportCompanyService;

    @GetMapping
    String welcome() {
        return "Welcome to Airport Service!";
    }

    @GetMapping(value = "/get")
    List<AirportCompany> getAllAirportCompanies() {
        return airportCompanyService.getAllAirportCompanies();
    }

    @PostMapping(value = "/get")
    AirportCompany getAirportCompany(@RequestParam(value = "id", required = true) Integer id) {
        return airportCompanyService.getAirportCompany(id).get();
    }

    @PostMapping(value = "/save")
    void saveAirportCompany(@RequestBody AirportCompany airportCompany) {
        airportCompanyService.addAirportCompany(airportCompany);
    }

    @PutMapping(value = "/update")
    AirportCompany updateAirportCompany(@RequestBody AirportCompany airportCompany) {
        return airportCompanyService.updateAirportCompany(airportCompany);
    }

    @DeleteMapping(value = "/delete")
    boolean deleteAirportCompany(@RequestParam Integer id) {
        return airportCompanyService.deleteAirportCompany(id);
    }

    @PostMapping(value = "/addnewflight")
    boolean addNewFlight(@RequestBody Flight flight){
        return airportCompanyService.addNewFlight(flight);
    }

    @PostMapping(value = "/buyticket")
    Ticket buyTicket(@RequestParam Integer flight_id, @RequestParam Integer passenger_id) {
        return airportCompanyService.buyTicketForFlight(flight_id, passenger_id);
    }
}
