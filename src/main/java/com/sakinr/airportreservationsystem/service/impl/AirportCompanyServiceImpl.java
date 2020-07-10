package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.exception.NotFoundException;
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
    public boolean addNewFlight(Flight flight) {
        if (flight != null && flight.getRoute() != null && flight.getAirportCompany() != null) {
            if (routeService.getRoute(flight.getRoute().getId()).isPresent()) {
                if (airportCompanyRepository.findById(flight.getAirportCompany().getId()).isPresent()) {
                    flightService.addFlight(flight);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteAirportCompany(Integer id) {
        AirportCompany airportCompany = getAirportCompany(id);
        airportCompanyRepository.delete(airportCompany);
        return true;
    }

    @Override
    public boolean cancelTicket(Integer ticket_id) {
        return ticketService.deleteTicket(ticket_id);
    }

    @Override
    public Ticket searchTicket(Integer ticket_id) {
        if (ticketService.getTicket(ticket_id).isPresent())
            return ticketService.getTicket(ticket_id).get();
        return Optional.of(new Ticket()).get();
    }

    @Override
    public Ticket buyTicketForFlight(Integer flight_id, Integer passenger_id) {
        Optional<Passenger> passenger = passengerService.getPassenger(passenger_id);
        if (passenger.isPresent()) {
            Optional<Flight> flight = flightService.getFlight(flight_id);
            if (flight.isPresent()) {
                if (flight.get().getTickets().size() < flight.get().getQuota()) {
                    Ticket newTicket = new Ticket();
                    newTicket.setPassenger(passenger.get());

                    Flight temp = flight.get();
                    temp.setPrice(calculatePrice(flight.get().getPrice(),
                            flight.get().getTickets().size(),
                            flight.get().getQuota()));
                    newTicket.setFlight(temp);

                    ticketService.addTicket(newTicket);
                }
            }
        }
        return Optional.of(new Ticket()).get();
    }

    private Integer calculatePrice(Integer price, Integer size, Integer quota) {
        Integer rate = (quota * 10) / 100;
        if (size < rate)
            return price;
        return price + (price * ((size / rate) * 10)) / 100;
    }
}
