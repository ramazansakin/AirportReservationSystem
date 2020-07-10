package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.exception.NotFoundException;
import com.sakinr.airportreservationsystem.repository.PassengerRepository;
import com.sakinr.airportreservationsystem.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassenger(Integer id) {
        Optional<Passenger> byId = passengerRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Passenger"));
    }

    @Override
    public void addPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public Passenger updatePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public boolean deletePassenger(Integer id) {
        passengerRepository.delete(getPassenger(id));
        return true;
    }

}
