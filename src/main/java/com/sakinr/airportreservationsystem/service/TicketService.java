package com.sakinr.airportreservationsystem.service;

import com.sakinr.airportreservationsystem.entity.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface TicketService {
    List<Ticket> getAllTickets();

    Ticket getTicket(Integer id);

    void addTicket(@RequestBody Ticket ticket);

    Ticket updateTicket(@RequestBody Ticket ticket);

    boolean deleteTicket(Integer id);
}
