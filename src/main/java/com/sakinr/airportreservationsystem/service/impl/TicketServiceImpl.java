package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.repository.TicketRepository;
import com.sakinr.airportreservationsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getTicket(Integer id) {
        return ticketRepository.findById(id);
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
        Optional<Ticket> ticket = getTicket(id);
        boolean isPresent = ticket.isPresent();
        ticket.ifPresent(t -> ticketRepository.delete(t));
        return isPresent;
    }
}