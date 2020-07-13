package com.sakinr.airportreservationsystem.controller;

import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passenger")
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

    @GetMapping(value = "/{id}")
    public Passenger getPassenger(@PathVariable @Min(1) Integer id) {
        return passengerService.getPassenger(id);
    }

    @PostMapping(value = "/create")
    public void savePassenger(@Valid @RequestBody Passenger passenger) {
        passengerService.addPassenger(passenger);
    }

    @PutMapping(value = "/update")
    public Passenger updatePassenger(@Valid @RequestBody Passenger passenger) {
        return passengerService.updatePassenger(passenger);
    }

    @DeleteMapping(value = "/delete")
    public boolean deletePassenger(@RequestParam @Min(1) Integer id) {
        return passengerService.deletePassenger(id);
    }

}
