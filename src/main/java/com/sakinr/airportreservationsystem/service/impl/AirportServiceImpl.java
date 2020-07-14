package com.sakinr.airportreservationsystem.service.impl;

import com.sakinr.airportreservationsystem.entity.Airport;
import com.sakinr.airportreservationsystem.exception.NotFoundException;
import com.sakinr.airportreservationsystem.model.Address;
import com.sakinr.airportreservationsystem.repository.AirportRepository;
import com.sakinr.airportreservationsystem.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirport(Integer id) {
        Optional<Airport> byId = airportRepository.findById(id);
        return byId.orElseThrow(() -> new NotFoundException("Airport"));
    }

    @Override
    public void addAirport(Airport airport) {
        airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public boolean deleteAirport(Integer id) {
        airportRepository.delete(getAirport(id));
        return true;
    }

    // java8 playground again :)
    private List<Address> getAddressCityStartsWith(String prefix) {
        List<Airport> allAirports = getAllAirports();
        return allAirports.stream()
                .map(Airport::getAddresses)
                .flatMap(Collection::stream)
                .distinct()
                .filter(a -> a.getCityName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    private void printAllAdressCityStartsWith(String prefix) {
        List<Address> addressCityStartsWith = getAddressCityStartsWith(prefix);
        List<String> openAddressList = addressCityStartsWith.stream()
                .map(address -> address.getCityName() + "/" + address.getStreetCode() + "/" + address.getBuildingNo())
                .distinct()
                .collect(Collectors.toList());

        openAddressList.forEach(System.out::println);
    }

    private void reduceAddressListToCityNameAndStreetCode() {
        List<Airport> allAirports = getAllAirports();
        String reducedAddressList = allAirports.stream()
                .map(Airport::getAddresses)
                .flatMap(Collection::stream)
                .map(address -> address.getCityName() + " " + address.getStreetCode())
                .reduce("", (s1, s2) -> s1 + s2);

        System.out.println("Reduced address List : " + reducedAddressList);
    }


}
