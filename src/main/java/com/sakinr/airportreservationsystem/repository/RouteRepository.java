package com.sakinr.airportreservationsystem.repository;

import com.sakinr.airportreservationsystem.entity.Route;
import com.sakinr.airportreservationsystem.repository.dynamic.RouteRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>, RouteRepositoryCustom {
}
