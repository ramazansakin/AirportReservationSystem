package com.sakinr.airportreservationsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface AnalyticsRepository {

    // 1. Get busiest routes (most flights)
    @Query(value = """
            SELECT 
                da.name as departureAirport, 
                aa.name as arrivalAirport, 
                COUNT(f.id) as flightCount
            FROM flight f
            JOIN route r ON f.route_id = r.id
            JOIN airport da ON r.departure_airport_id = da.id
            JOIN airport aa ON r.arrival_airport_id = aa.id
            GROUP BY da.name, aa.name
            ORDER BY flightCount DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findBusiestRoutes();

    // 2. Get most profitable flights (highest revenue)
    @Query(value = """
            SELECT 
                f.id as flightId,
                f.code as flightCode,
                da.name as departureAirport,
                aa.name as arrivalAirport,
                f.departure_date as departureDate,
                COUNT(t.id) as ticketCount,
                SUM(f.price) as totalRevenue
            FROM flight f
            JOIN route r ON f.route_id = r.id
            JOIN airport da ON r.departure_airport_id = da.id
            JOIN airport aa ON r.arrival_airport_id = aa.id
            JOIN ticket t ON f.id = t.flight_id
            GROUP BY f.id, f.code, da.name, aa.name, f.departure_date
            ORDER BY totalRevenue DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findMostProfitableFlights();

    // 3. Get top airports by passenger traffic
    @Query(value = """
            SELECT 
                a.id as airportId,
                a.name as airportName,
                COUNT(DISTINCT t.passenger_id) as passengerCount
            FROM ticket t
            JOIN flight f ON t.flight_id = f.id
            JOIN route r ON f.route_id = r.id
            JOIN airport a ON r.departure_airport_id = a.id
            GROUP BY a.id, a.name
            ORDER BY passengerCount DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findTopAirportsByPassengerTraffic();

    // 4. Get top airlines by number of flights
    @Query(value = """
            SELECT 
                ac.id as airlineId,
                ac.name as airlineName,
                COUNT(DISTINCT f.id) as flightCount,
                COUNT(DISTINCT t.passenger_id) as passengerCount
            FROM airport_company ac
            LEFT JOIN flight f ON ac.id = f.airport_company_id
            LEFT JOIN ticket t ON f.id = t.flight_id
            GROUP BY ac.id, ac.name
            ORDER BY flightCount DESC, passengerCount DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findTopAirlines();

    // 5. Get daily flight statistics
    @Query(value = """
            SELECT 
                DATE_TRUNC('day', f.departure_date) as day,
                COUNT(DISTINCT f.id) as flightCount,
                COUNT(t.id) as ticketCount,
                COALESCE(AVG(f.price), 0) as avgTicketPrice
            FROM flight f
            LEFT JOIN ticket t ON f.id = t.flight_id
            WHERE f.departure_date BETWEEN :startDate AND :endDate
            GROUP BY DATE_TRUNC('day', f.departure_date)
            ORDER BY day
            """, nativeQuery = true)
    List<Map<String, Object>> getDailyFlightStats(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    // 6. Get passenger flight history with spending
    @Query(value = """
            SELECT 
                p.id as passengerId,
                p.firstname,
                p.lastname,
                COUNT(t.id) as totalFlights,
                COALESCE(SUM(f.price), 0) as totalSpent,
                COALESCE(AVG(f.price), 0) as avgTicketPrice
            FROM passenger p
            LEFT JOIN ticket t ON p.id = t.passenger_id
            LEFT JOIN flight f ON t.flight_id = f.id
            GROUP BY p.id, p.firstname, p.lastname
            HAVING COUNT(t.id) > 0
            ORDER BY totalSpent DESC
            """, nativeQuery = true)
    List<Map<String, Object>> getPassengerFlightHistory();

}
