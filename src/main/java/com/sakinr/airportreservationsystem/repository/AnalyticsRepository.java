package com.sakinr.airportreservationsystem.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface AnalyticsRepository {

    List<Map<String, Object>> findBusiestRoutes();

    List<Map<String, Object>> findMostProfitableFlights();

    List<Map<String, Object>> findTopAirportsByPassengerTraffic();

    List<Map<String, Object>> findTopAirlines();

    List<Map<String, Object>> getDailyFlightStats(@Param("startDate") Date startDate,
                                                  @Param("endDate") Date endDate);

    List<Map<String, Object>> getPassengerFlightHistory();

    List<Map<String, Object>> findMonthlyRoutePerformanceAnalytics();

    List<Map<String, Object>> findCustomerAnalyticsWithTiers();

    List<Map<String, Object>> findFlightPerformanceAnalytics();

}
