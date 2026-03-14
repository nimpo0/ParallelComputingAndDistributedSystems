package com.example.logistics_api.repository;

import com.example.logistics_api.model.Route;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RouteRepository {

    private final List<Route> routes = new ArrayList<>();
    private Long nextId = 1L;

    public List<Route> findAll() {
        return new ArrayList<>(routes);
    }

    public Optional<Route> findById(Long id) {
        return routes.stream()
                .filter(route -> route.getId().equals(id))
                .findFirst();
    }

    public List<Route> findByStartPointAndEndPoint(String startPoint, String endPoint) {
        return routes.stream()
                .filter(route -> route.getStartPoint().equalsIgnoreCase(startPoint)
                        && route.getEndPoint().equalsIgnoreCase(endPoint))
                .toList();
    }

    public Route save(Route route) {
        route.setId(nextId++);
        routes.add(route);
        return route;
    }
}