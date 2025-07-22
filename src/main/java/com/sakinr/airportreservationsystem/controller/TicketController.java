package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.model.PageableQuery;
import com.sakinr.airportreservationsystem.service.FlightService;
import com.sakinr.airportreservationsystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    private final FlightService flightService;

    @GetMapping
    public String welcome() {
        return "Welcome to Ticket Service!";
    }

    @GetMapping(value = "/all")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping(value = "/{id}")
    public Ticket getTicket(@PathVariable @Min(1) Integer id) {
        return ticketService.getTicket(id);
    }

    @PostMapping(value = "/create")
    public void saveTicket(@Valid @RequestBody Ticket ticket) {
        ticketService.addTicket(ticket);
    }

    @PutMapping(value = "/update")
    public Ticket updateTicket(@Valid @RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteTicket(@RequestParam @Min(1) Integer id) {
        return ticketService.deleteTicket(id);
    }

    @PostMapping(value = "/flight/{id}")
    public Page<Ticket> getAllTicketsByFlightId(
            @Valid @RequestBody PageableQuery query,
            @PathVariable @Min(1) Integer id
    ) {
        Flight flight = flightService.getFlight(id);
        return ticketService.getAllByFlightPagination(query, flight);
    }


}
