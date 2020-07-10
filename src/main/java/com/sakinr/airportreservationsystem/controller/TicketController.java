package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public String welcome() {
        return "Welcome to Ticket Service!";
    }

    @GetMapping(value = "/all")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PostMapping(value = "/{id}")
    public Ticket getTicket(@PathVariable(value = "id") Integer id) {
        return ticketService.getTicket(id);
    }

    @PostMapping(value = "/create")
    public void saveTicket(@RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
    }

    @PutMapping(value = "/update")
    public Ticket updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteTicket(@RequestParam Integer id) {
        return ticketService.deleteTicket(id);
    }

}
