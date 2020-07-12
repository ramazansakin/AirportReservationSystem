package com.sakinr.airportreservationsystem.repository;

import com.sakinr.airportreservationsystem.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    // JPA -Hibernate self-created query apis
    List<Ticket> getTicketsByPassengerId(Integer passengerId);

    List<Ticket> getTicketsByFlightId(Integer flightId);

    List<Ticket> getAllByFlight_Departure_dateBetween(Date startDate, Date endDate);

    //JPQL - Java Persistence Query Language
    @Query(
            value = "SELECT * FROM ticket t where   ORDER BY id",
            countQuery = "SELECT count(*) FROM Users",
            nativeQuery = true)
    Page<Ticket> getAllByFlightIdPagination(
            @Param("flightId")Integer flightId,
            Pageable pageable);



}
