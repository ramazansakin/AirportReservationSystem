package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.Passenger;
import com.sakinr.airportreservationsystem.exception.NotFoundException;
import com.sakinr.airportreservationsystem.repository.PassengerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class PassengerServiceImplTest {

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerServiceImpl passengerService;

//    @BeforeClass
//    public void setup() {
//
//        // init
//        Passenger expectedPassenger = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");
//
//        // stub - when
//        Mockito.when(passengerRepository.findById(1)).thenReturn(Optional.of(expectedPassenger));
//    }

//    @BeforeEach
//    public void setupForEach() {
//        // init
//        Passenger expectedPassenger = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");
//
//        // stub - when
//        Mockito.when(passengerRepository.findById(1)).thenReturn(Optional.of(expectedPassenger));
//    }

    @Test
    void getAllPassengers() {
        // stub
        Passenger passenger1 = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");
        Passenger passenger2 = new Passenger(2, "Passenger2", "L2", "Female", 35, "05554443311", "p2@mail.com");
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger1);
        passengers.add(passenger2);

        // when
        Mockito.when(passengerRepository.findAll()).thenReturn(passengers);

        // then
        List<Passenger> allPassengers = passengerService.getAllPassengers();

        Assert.assertEquals(passengers.size(), allPassengers.size());
    }

    @Test
    void getPassenger() {
        // init
        Passenger expectedPassenger = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");

        // stub - when
        Mockito.when(passengerRepository.findById(1)).thenReturn(Optional.of(expectedPassenger));

        // then
        Passenger actualPassenger = passengerService.getPassenger(1);

        Assert.assertEquals(expectedPassenger.getAge(), actualPassenger.getAge());
        Assert.assertEquals(expectedPassenger.getGender(), actualPassenger.getGender());
        Assert.assertEquals(expectedPassenger.getFirstname(), actualPassenger.getFirstname());
    }

    @Test
    void getPassenger_notfound() {
        // init
        Passenger expectedPassenger = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");

        // stub - when
        Mockito.when(passengerRepository.findById(1)).thenReturn(Optional.of(expectedPassenger));

        // then
        Passenger actualPassenger = passengerService.getPassenger(1);

        Assert.assertEquals(expectedPassenger.getAge(), actualPassenger.getAge());
        Assert.assertEquals(expectedPassenger.getGender(), actualPassenger.getGender());
        Assert.assertEquals(expectedPassenger.getFirstname(), actualPassenger.getFirstname());
    }

    @Test
    void addPassengerFailurePlayground() {
        // init
        Passenger expectedPassenger = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");

        // stub - when
        Mockito.when(passengerRepository.save(any())).thenReturn(expectedPassenger);

        // then
//        Assert.assertEquals(expectedPassenger.getAge(), actualPassenger.getAge());
//        Assert.assertEquals(expectedPassenger.getGender(), actualPassenger.getGender());
//        Assert.assertEquals(expectedPassenger.getFirstname(), actualPassenger.getFirstname());

        passengerService.addPassenger(expectedPassenger);
        assertThrows(NotFoundException.class,
                () -> {
                    Passenger actualPassenger = passengerService.getPassenger(1);
                }
        );

    }

    @Test
    void addPassenger() {
        // init
        Passenger expectedPassenger = new Passenger(1, "Passenger1", "Lastname1", "Male", 25, "05554443322", "passenger1@mail.com");

        // stub - when
        Mockito.when(passengerRepository.save(any())).thenReturn(expectedPassenger);

        // then
        passengerService.addPassenger(expectedPassenger);

        Mockito.verify(passengerRepository, Mockito.times(1)).save(any());

    }

    @Test
    void updatePassenger() {

    }

    @Test
    void deletePassenger() {

    }

    @Test
    void getPassengersNameStartsWith() {

    }

    @Test
    void getPassengersSortedViaLastNameAsUpperCase() {
    }
}