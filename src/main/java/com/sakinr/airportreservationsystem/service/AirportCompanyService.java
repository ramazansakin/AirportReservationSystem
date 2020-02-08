package com.sakinr.airportreservationsystem.service;

import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface AirportCompanyService {
    List<AirportCompany> getAllAirportCompanies();

    Optional<AirportCompany> getAirportCompany(Integer id);

    void addAirportCompany(AirportCompany airportCompany);

    AirportCompany updateAirportCompany(AirportCompany airportCompany);

    boolean deleteAirportCompany(Integer id);

    boolean addNewFlight(Flight flight);

    Ticket buyTicketForFlight(Integer flight_id, Integer passenger_id);

    boolean cancelTicket(Integer ticket_id);

    Ticket searchTicket(Integer ticket_id);
}