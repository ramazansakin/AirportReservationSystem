package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.exception.NotFoundException;
import com.sakinr.airportreservationsystem.model.PageableQuery;
import com.sakinr.airportreservationsystem.repository.TicketRepository;
import com.sakinr.airportreservationsystem.service.TicketService;
import com.sakinr.airportreservationsystem.util.PageableRequestBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicket(Integer id) {
        Optional<Ticket> byId = ticketRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Ticket"));
    }

    @Override
    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public boolean deleteTicket(Integer id) {
        Ticket ticket = getTicket(id);
        ticketRepository.delete(ticket);
        return true;
    }

    @Override
    public Page<Ticket> getAllByFlightPagination(PageableQuery pageable, Flight flight) {
        PageRequest p = PageableRequestBuilder.build(pageable);
        return ticketRepository.getAllByFlightPagination(p, flight);
    }
}