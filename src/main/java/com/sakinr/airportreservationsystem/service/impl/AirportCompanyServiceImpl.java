package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.repository.AirportCompanyRepository;
import com.sakinr.airportreservationsystem.service.AirportCompanyService;
import com.sakinr.airportreservationsystem.service.AirportService;
import com.sakinr.airportreservationsystem.service.FlightService;
import com.sakinr.airportreservationsystem.service.RouteService;
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
}
