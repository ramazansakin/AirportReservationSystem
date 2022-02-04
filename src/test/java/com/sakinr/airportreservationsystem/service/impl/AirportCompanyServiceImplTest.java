package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.AirportCompany;
import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.entity.Ticket;
import com.sakinr.airportreservationsystem.repository.AirportCompanyRepository;
import com.sakinr.airportreservationsystem.service.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AirportCompanyServiceImplTest {

    @Mock
    private AirportCompanyRepository airportCompanyRepository;

    @Mock
    private FlightService flightService;

    @Mock
    private RouteService routeService;

    @Mock
    private AirportService airportService;

    @Mock
    private TicketService ticketService;

    @Mock
    private PassengerService passengerService;


    @InjectMocks
    private AirportCompanyServiceImpl airportCompanyService;


    @Test
    void getAllAirportCompanies() {
        // expected initial datas
        List<AirportCompany> airportCompanies = Arrays.asList(
                new AirportCompany(1, "Airport X", null),
                new AirportCompany(2, "Airport Y", null),
                new AirportCompany(3, "Airport Z", null)
        );

        // stub - when
        Mockito.when(airportCompanyRepository.findAll()).thenReturn(airportCompanies);

        // then test
        List<AirportCompany> allAirportCompanies = airportCompanyService.getAllAirportCompanies();

        // validate
        Assert.assertEquals(airportCompanies.size(), allAirportCompanies.size());
    }

    @Test
    void getAirportCompany() {
    }

    @Test
    void addAirportCompany() {
    }

    @Test
    void updateAirportCompany() {
    }

    @Test
    void addNewFlight() {
        // expected initial datas
        AirportCompany airport_x = new AirportCompany(1, "Airport X", new ArrayList<>()); // new ArrayList<>();
        Flight expectedFlight = new Flight(1, "Flight-XYZ", 50, 300, new Date(), new Date(), null, null, airport_x);
        AirportCompany airport_y = new AirportCompany(1, "Airport X", Arrays.asList(expectedFlight));

        // stub - when
        Mockito.when(airportCompanyRepository.getOne(1)).thenReturn(airport_x);
        Mockito.when(flightService.getFlight(1)).thenReturn(expectedFlight);
        Mockito.when(airportCompanyRepository.save(airport_y)).thenReturn(airport_y);

        // then test
        boolean flightAddStatus = airportCompanyService.addNewFlight(1, 1);

        // validate
        Assert.assertEquals(true, flightAddStatus);

    }

    @Test
    void deleteAirportCompany() {
    }

    @Test
    void cancelTicket() {
        // expected initial datas
        Ticket ticket1 = new Ticket(2, null, null);
        Ticket ticket2 = new Ticket(3, null, null);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);

        Flight testFlight = new Flight(1, "Flight-XYZ", 50, 300, new Date(), new Date(),
                tickets, null, null);
        ticket1.setFlight(testFlight);
        ticket2.setFlight(testFlight);

        testFlight.getTickets().remove(1);

        Flight updatedTestFlight = testFlight;


        // stub - when
        Mockito.when(ticketService.getTicket(any())).thenReturn(ticket1);
        Mockito.when(flightService.getFlight(any())).thenReturn(testFlight);
        Mockito.when(flightService.updateFlight(testFlight)).thenReturn(updatedTestFlight);
        Mockito.when(ticketService.deleteTicket(2)).thenReturn(true);

        // then test
        boolean actualCancelStatus = airportCompanyService.cancelTicket(2);

        // validate
        Assert.assertEquals(true, actualCancelStatus);

    }

    @Test
    void searchTicket() {
    }

    @Test
    void buyTicketForFlight() {
    }

    @Test
    void getAllFlightByAirportCompany() {
    }
}