package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.Flight;
import com.sakinr.airportreservationsystem.exception.NotFoundException;
import com.sakinr.airportreservationsystem.functionalint.Printable;
import com.sakinr.airportreservationsystem.repository.FlightRepository;
import com.sakinr.airportreservationsystem.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    
    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlight(Integer id) {
        Optional<Flight> byId = flightRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Flight"));
    }

    @Override
    public void addFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public boolean deleteFlight(Integer id) {
        flightRepository.delete(getFlight(id));
        return true;
    }

    private void printFlightBasicInfos(Integer flight_id) {
        Flight flight = flightRepository.getOne(flight_id);

        Printable printable = (object) -> {
            StringBuilder sb = new StringBuilder();
            Flight tempFlight = (Flight) object;
            sb.append("Flight Info : ")
                    .append("Flight Code  : ")
                    .append(tempFlight.getCode())
                    .append("Flight Price : ")
                    .append(tempFlight.getPrice())
                    .append("Flight Airport Company : ")
                    .append(tempFlight.getAirportCompany().getName());

            System.out.println(sb.toString());
        };
        printable.print(flight);
    }

    @Override
    public List<Flight> getAllFlightsDepartureDateBetween(Date start, Date end) {
        return flightRepository.getAllByDepartureDateBetween(start, end);
    }

    @Override
    public Flight getFlightByCode(String code) {
        return flightRepository.getByCode(code);
    }


}
