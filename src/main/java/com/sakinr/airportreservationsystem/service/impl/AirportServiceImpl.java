package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.repository.AirportRepository;
import com.sakinr.airportreservationsystem.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Optional<Airport> getAirport(Integer id) {
        return airportRepository.findById(id);
    }

    @Override
    public void addAirport(Airport airport) {
        airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public boolean deleteAirport(Integer id) {
        Optional<Airport> airport = getAirport(id);
        boolean present = airport.isPresent();
        airportRepository.delete(airport.get());
        return present;
    }
}
