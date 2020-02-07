package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.repository.AirportCompanyRepository;
import com.sakinr.airportreservationsystem.service.AirportCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportCompanyServiceImpl implements AirportCompanyService {

    @Autowired
    AirportCompanyRepository airportCompanyRepository;

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
    public boolean deleteAirportCompany(Integer id) {
        Optional<AirportCompany> airportCompany = getAirportCompany(id);
        boolean present = airportCompany.isPresent();
        airportCompanyRepository.delete(airportCompany.get());
        return present;
    }
}
