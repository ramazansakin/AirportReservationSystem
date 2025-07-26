package com.sakinr.airportreservationsystem.repository.impl;

import com.sakinr.airportreservationsystem.repository.AnalyticsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class AnalyticsRepositoryImpl implements AnalyticsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Map<String, Object>> findBusiestRoutes() {
        String sql = """
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
                """;
        return entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .getResultList();
    }

    @Override
    public List<Map<String, Object>> findMostProfitableFlights() {
        String sql = """
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
                """;
        return entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .getResultList();
    }

    @Override
    public List<Map<String, Object>> findTopAirportsByPassengerTraffic() {
        String sql = """
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
                """;
        return entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .getResultList();
    }

    @Override
    public List<Map<String, Object>> findTopAirlines() {
        String sql = """
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
                """;
        return entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .getResultList();
    }

    @Override
    public List<Map<String, Object>> getDailyFlightStats(Date startDate, Date endDate) {
        String sql = """
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
                """;
        return entityManager.createNativeQuery(sql)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .getResultList();
    }

    @Override
    public List<Map<String, Object>> getPassengerFlightHistory() {
        String sql = """
                SELECT 
                    p.id as passengerId,
                    p.firstname as firstName,
                    p.lastname as lastName,
                    COUNT(t.id) as totalFlights,
                    COALESCE(SUM(f.price), 0) as totalSpent,
                    COALESCE(AVG(f.price), 0) as avgTicketPrice
                FROM passenger p
                LEFT JOIN ticket t ON p.id = t.passenger_id
                LEFT JOIN flight f ON t.flight_id = f.id
                GROUP BY p.id, p.firstname, p.lastname
                HAVING COUNT(t.id) > 0
                ORDER BY totalSpent DESC
                """;
        return entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .getResultList();
    }

    @Override
    public List<Map<String, Object>> findMonthlyRoutePerformanceAnalytics() {
        String sql = """
                SELECT 
                    EXTRACT(YEAR FROM f.departure_date) as year,
                    EXTRACT(MONTH FROM f.departure_date) as month,
                    da.name as departureAirport,
                    aa.name as arrivalAirport,
                    COUNT(DISTINCT f.id) as totalFlights,
                    COUNT(t.id) as ticketsSold,
                    SUM(f.price) as totalRevenue,
                    ROUND(AVG(f.price), 2) as avgFlightPrice
                FROM flight f
                JOIN route r ON f.route_id = r.id
                JOIN airport da ON r.departure_airport_id = da.id
                JOIN airport aa ON r.arrival_airport_id = aa.id
                LEFT JOIN ticket t ON f.id = t.flight_id
                WHERE f.departure_date >= CURRENT_DATE - INTERVAL '6 months'
                GROUP BY EXTRACT(YEAR FROM f.departure_date), EXTRACT(MONTH FROM f.departure_date), da.name, aa.name
                ORDER BY year DESC, month DESC, totalRevenue DESC
                """;
        return entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .getResultList();
    }

    @Override
    public List<Map<String, Object>> findCustomerAnalyticsWithTiers() {
        String sql = """
                SELECT 
                    p.id as passengerId,
                    CONCAT(p.firstname, ' ', p.lastname) as passengerName,
                    p.email,
                    COUNT(t.id) as totalFlights,
                    COALESCE(SUM(f.price), 0) as totalSpent,
                    ROUND(COALESCE(AVG(f.price), 0), 2) as avgFlightCost,
                    MIN(f.departure_date) as firstFlightDate,
                    MAX(f.departure_date) as lastFlightDate,
                    COUNT(DISTINCT CONCAT(da.name, '-', aa.name)) as uniqueRoutes,
                    COUNT(DISTINCT da.id) as uniqueDepartureAirports,
                    CASE 
                        WHEN COALESCE(SUM(f.price), 0) >= 10000 AND COUNT(t.id) >= 15 THEN 'VIP'
                        WHEN COALESCE(SUM(f.price), 0) >= 5000 OR COUNT(t.id) >= 8 THEN 'Premium'
                        WHEN COALESCE(SUM(f.price), 0) >= 2000 OR COUNT(t.id) >= 3 THEN 'Regular'
                        ELSE 'Occasional'
                    END as customerTier
                FROM passenger p
                LEFT JOIN ticket t ON p.id = t.passenger_id
                LEFT JOIN flight f ON t.flight_id = f.id
                LEFT JOIN route r ON f.route_id = r.id
                LEFT JOIN airport da ON r.departure_airport_id = da.id
                LEFT JOIN airport aa ON r.arrival_airport_id = aa.id
                GROUP BY p.id, p.firstname, p.lastname, p.email
                HAVING COUNT(t.id) >= 1
                ORDER BY totalSpent DESC
                """;
        return entityManager.createNativeQuery(sql)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .getResultList();
    }


}
