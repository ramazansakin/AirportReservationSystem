package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.exception.NotFoundException;
import com.sakinr.airportreservationsystem.exception.QuotaIsFullException;
import com.sakinr.airportreservationsystem.repository.AirportCompanyRepository;
import com.sakinr.airportreservationsystem.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AirportCompanyServiceImpl implements AirportCompanyService {

    private final AirportCompanyRepository airportCompanyRepository;

    private final FlightService flightService;

    private final RouteService routeService;

    private final AirportService airportService;

    private final TicketService ticketService;

    private final PassengerService passengerService;

    @Override
    public List<AirportCompany> getAllAirportCompanies() {
        return airportCompanyRepository.findAll();
    }

    @Override
    public AirportCompany getAirportCompany(Integer id) {
        Optional<AirportCompany> byId = airportCompanyRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Airport"));
    }

    @Override
    public void addAirportCompany(AirportCompany airportCompany) {
        airportCompanyRepository.save(airportCompany);
    }

    @Override
    public AirportCompany updateAirportCompany(AirportCompany airportCompany) {
        return airportCompanyRepository.save(airportCompany);
    }

    @Override
    public boolean addNewFlight(Integer airport_company_id, Integer flight_id) {
        AirportCompany one = airportCompanyRepository.getOne(airport_company_id);
        Flight flight = flightService.getFlight(flight_id);
        List<Flight> flights = one.getFlights();
        flights.add(flight);
        airportCompanyRepository.save(one);
        return true;
    }

    @Override
    public boolean deleteAirportCompany(Integer id) {
        AirportCompany airportCompany = getAirportCompany(id);
        airportCompanyRepository.delete(airportCompany);
        return true;
    }

    @Override
    public boolean cancelTicket(Integer ticket_id) {
        Ticket ticket = ticketService.getTicket(ticket_id);
        Flight flight = flightService.getFlight(ticket.getFlight().getId());
        Ticket ticket1 = flight.getTickets().stream()
                .filter(t -> t.getId().equals(ticket.getId()))
                .findFirst().orElseThrow(() -> new NotFoundException("Ticket"));
        flight.getTickets().remove(ticket1);
        flightService.updateFlight(flight);
        ticketService.deleteTicket(ticket.getId());
        return true;
    }

    @Override
    public Ticket searchTicket(Integer ticket_id) {
        return ticketService.getTicket(ticket_id);
    }

    @Override
    public Ticket buyTicketForFlight(Integer flight_id, Integer passenger_id) {
        Passenger passenger = passengerService.getPassenger(passenger_id);
        Flight flight = flightService.getFlight(flight_id);

        if (flight.getTickets().size() < flight.getQuota()) {
            Ticket newTicket = new Ticket();
            newTicket.setPassenger(passenger);
            newTicket.setFlight(flight);

            ticketService.addTicket(newTicket);

            int rate = (flight.getQuota() * 10) / 100;
            if (flight.getTickets().size() > rate) {
                int newPrice = flight.getPrice() + (flight.getPrice() * ((flight.getTickets().size() / rate) * 10)) / 100;
                flight.setPrice(newPrice);
                flightService.updateFlight(flight);
            }
        }

        throw new QuotaIsFullException(flight.getCode());
    }

    @Override
    public List<Flight> getAllFlightByAirportCompany(Integer airport_company_id) {
        AirportCompany airportCompany = getAirportCompany(airport_company_id);
        return airportCompany.getFlights();
    }

}
