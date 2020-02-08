package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.repository.AirportCompanyRepository;
import com.sakinr.airportreservationsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportCompanyServiceImpl implements AirportCompanyService {

    @Autowired
    AirportCompanyRepository airportCompanyRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    RouteService routeService;

    @Autowired
    AirportService airportService;

    @Autowired
    TicketService ticketService;

    @Autowired
    PassengerService passengerService;

    @Override
    public List<AirportCompany> getAllAirportCompanies() {
        return airportCompanyRepository.findAll();
    }

    @Override
    public Optional<AirportCompany> getAirportCompany(Integer id) {
        return airportCompanyRepository.findById(id);
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
        Optional<AirportCompany> airportCompany = getAirportCompany(id);
        boolean present = airportCompany.isPresent();
        airportCompanyRepository.delete(airportCompany.get());
        return present;
    }

    @Override
    public Ticket buyTicketForFlight(Integer flight_id, Integer passenger_id) {
        Optional<Passenger> passenger = passengerService.getPassenger(passenger_id);
        if (passenger.isPresent()) {
            Optional<Flight> flight = flightService.getFlight(flight_id);
            if (flight.isPresent()) {
                if (flight.get().getTickets().size() < flight.get().getQuota()) {
                    Ticket newTicket = new Ticket();
                    newTicket.setFlight(flight.get());
                    newTicket.setPassenger(passenger.get());

                    newTicket.setPrice(calculatePrice(flight.get().getPrice(),
                            flight.get().getTickets().size(),
                            flight.get().getQuota()));

                    ticketService.addTicket(newTicket);
                }
            }
        }

        return null;
    }

    private Integer calculatePrice(Integer price, Integer size, Integer quota) {

        Integer rate = (quota * 10) / 100;
        if (size < rate)
            return price;
        return price + (price * ((size / rate) * 10)) / 100;
    }
}
