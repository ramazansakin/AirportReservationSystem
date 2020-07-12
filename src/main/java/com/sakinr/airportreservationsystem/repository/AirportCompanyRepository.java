package com.sakinr.airportreservationsystem.repository;

import com.sakinr.airportreservationsystem.entity.AirportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportCompanyRepository extends JpaRepository<AirportCompany, Integer> {

    AirportCompany getByName(String name);
}
