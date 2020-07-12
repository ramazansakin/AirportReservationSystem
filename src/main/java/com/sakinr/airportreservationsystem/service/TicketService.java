package com.sakinr.airportreservationsystem.service;

import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.model.PageableQuery;
import org.springframework.data.domain.Page;
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

    Page<Ticket> getAllByFlightPagination(PageableQuery pageable, Flight flight);
}
