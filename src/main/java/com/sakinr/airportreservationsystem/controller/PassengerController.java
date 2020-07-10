package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping
    public String welcome() {
        return "Welcome to Passenger Service!";
    }

    @GetMapping(value = "/all")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @PostMapping(value = "/{id}")
    public Passenger getPassenger(@PathVariable(value = "id") Integer id) {
        return passengerService.getPassenger(id);
    }

    @PostMapping(value = "/create")
    public void savePassenger(@RequestBody Passenger passenger) {
        passengerService.addPassenger(passenger);
    }

    @PutMapping(value = "/update")
    public Passenger updatePassenger(@RequestBody Passenger passenger) {
        return passengerService.updatePassenger(passenger);
    }

    @DeleteMapping(value = "/delete")
    public boolean deletePassenger(@RequestParam Integer id) {
        return passengerService.deletePassenger(id);
    }

}
