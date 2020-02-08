package com.sakinr.airportreservationsystem.service;

import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Ticket;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface AirportCompanyService {
    List<AirportCompany> getAllAirportCompanies();

    Optional<AirportCompany> getAirportCompany(Integer id);

    void addAirportCompany(AirportCompany airportCompany);

    AirportCompany updateAirportCompany(AirportCompany airportCompany);

    boolean deleteAirportCompany(Integer id);

    boolean addNewFlight(Flight flight);

    public Ticket buyTicketForFlight(Integer flight_id, Integer passenger_id);
}
