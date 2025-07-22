package com.sakinr.airportreservationsystem.repository.dynamic.impl;

import com.sakinr.airportreservationsystem.entity.Route;
import com.sakinr.airportreservationsystem.repository.dynamic.RouteRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteRepositoryImpl implements RouteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Route> getRoutesByDepartureAirportAndArrivalAirport(Integer departure_airport_id, Integer arrival_airport_id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Route> query = cb.createQuery(Route.class);
        Root<Route> route = query.from(Route.class);

        List<Predicate> predicates = new ArrayList<>();
        Predicate departure_predicate = cb.equal(route.get("departure_airport").<Integer>get("id"), departure_airport_id);
        predicates.add(departure_predicate);
        Predicate arrival_predicate = cb.equal(route.get("arrival_airport").<Integer>get("id"), arrival_airport_id);
        predicates.add(arrival_predicate);

        query.select(route)
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public Route getFirstRouteByDepartureAirport(Integer departure_airport_id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Route> query = cb.createQuery(Route.class);
        Root<Route> route = query.from(Route.class);

        query.select(route)
                .where(cb.equal(route.get("departure_airport").<Integer>get("id"), departure_airport_id));

        Optional<List<Route>> resultList = Optional.of(entityManager.createQuery(query)
                .getResultList());

        if (!resultList.get().isEmpty()) {
            List<Route> routes = resultList.get();
            return routes.get(0);
        }
        return null;
    }
}
