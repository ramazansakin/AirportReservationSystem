package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @GetMapping
    String welcome() {
        return "Welcome to Passenger Service!";
    }

    @GetMapping(value = "/get")
    List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @PostMapping(value = "/get")
    Passenger getPassenger(@RequestParam(value = "id", required = true) Integer id) {
        return passengerService.getPassenger(id).get();
    }

    @PostMapping(value = "/save")
    void savePassenger(@RequestBody Passenger passenger) {
        passengerService.addPassenger(passenger);
    }

    @PutMapping(value = "/update")
    Passenger updatePassenger(@RequestBody Passenger passenger) {
        return passengerService.updatePassenger(passenger);
    }

    @DeleteMapping(value = "/delete")
    boolean deletePassenger(@RequestParam Integer id) {
        return passengerService.deletePassenger(id);
    }

}
