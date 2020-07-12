package com.sakinr.airportreservationsystem.repository.dynamic.impl;

import com.sakinr.airportreservationsystem.entity.Route;
import com.sakinr.airportreservationsystem.repository.dynamic.RouteRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteRepositoryCustomImpl implements RouteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Route> getRoutesByDepartureAirportAndArrivalAirport(Integer departure_airport_id, Integer arrival_airport_id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Route> query = cb.createQuery(Route.class);
        Root<Route> route = query.from(Route.class);

        Path<Integer> departure_id = route.get("departure_airport_id");

        Path<Integer> arrival_id = route.get("arrival_airport_id");

        List<Predicate> predicates = new ArrayList<>();
        cb.equal(departure_id, departure_airport_id);
        cb.equal(arrival_id, arrival_airport_id);

        query.select(route)
                .where(cb.or(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query)
                .getResultList();
    }

    @Override
    public Route getFirstRouteByDepartureAirport(Integer departure_airport_id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Route> query = cb.createQuery(Route.class);
        Root<Route> route = query.from(Route.class);

        Path<Integer> departure_id = route.get("departure_airport_id");

        List<Predicate> predicates = new ArrayList<>();
        cb.equal(departure_id, departure_airport_id);

        query.select(route)
                .where(cb.or(predicates.toArray(new Predicate[0])));

        Optional<Route> result = Optional.of(entityManager.createQuery(query)
                .getResultList().get(0));

        return result.orElseGet(null);
    }
}
