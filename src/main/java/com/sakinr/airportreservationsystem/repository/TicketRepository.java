package com.sakinr.airportreservationsystem.repository;

import com.sakinr.airportreservationsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket getTicketsByPassengerId(Integer passengerId);

    List<Ticket> getTickersByFlightId(Integer flightId);

}
