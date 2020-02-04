package com.sakinr.airportreservationsystem.service;

import com.sakinr.airportreservationsystem.entity.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public interface TicketService {
    List<Ticket> getAllTickets();

    Optional<Ticket> getTicket(Integer id);

    void addTicket(@RequestBody Ticket ticket);

    Ticket updateTicket(@RequestBody Ticket ticket);

    boolean deleteTicket(Integer id);
}
