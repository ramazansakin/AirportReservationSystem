package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping
    String welcome() {
        return "Welcome to Ticket Service!";
    }

    @GetMapping(value = "/get")
    List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PostMapping(value = "/get")
    Ticket getTicket(@RequestParam(value = "id", required = true) Integer id) {
        return ticketService.getTicket(id).get();
    }

    @PostMapping(value = "/save")
    void saveTicket(@RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
    }

    @PutMapping(value = "/update")
    Ticket updateTicket(@RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping(value = "/delete")
    boolean deleteTicket(@RequestParam Integer id) {
        return ticketService.deleteTicket(id);
    }

}
