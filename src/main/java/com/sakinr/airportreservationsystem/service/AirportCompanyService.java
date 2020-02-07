package com.sakinr.airportreservationsystem.service;

import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.entity.AirportCompany;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface AirportCompanyService {
    List<AirportCompany> getAllAirportCompanies();

    Optional<AirportCompany> getAirportCompany(Integer id);

    void addAirportCompany(@RequestBody AirportCompany airportCompany);

    AirportCompany updateAirportCompany(@RequestBody AirportCompany airportCompany);

    boolean deleteAirportCompany(Integer id);
}
