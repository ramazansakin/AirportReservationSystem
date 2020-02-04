package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.Passenger;
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
    public Optional<Passenger> getPassenger(Integer id) {
        return passengerRepository.findById(id);
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
        Optional<Passenger> pass = getPassenger(id);
        boolean present = pass.isPresent();
        pass.ifPresent(p -> passengerRepository.delete(p));
        return present;
    }

}
