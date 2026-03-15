package com.example.logistics_api.service;

import com.example.logistics_api.dto.RouteDto;
import com.example.logistics_api.exception.ResourceNotFoundException;
import com.example.logistics_api.model.Route;
import com.example.logistics_api.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes(String startPoint, String endPoint) {
        if (startPoint != null && endPoint != null) {
            return routeRepository.findByStartPointIgnoreCaseAndEndPointIgnoreCase(startPoint, endPoint);
        }

        if (startPoint != null) {
            return routeRepository.findByStartPointIgnoreCase(startPoint);
        }

        if (endPoint != null) {
            return routeRepository.findByEndPointIgnoreCase(endPoint);
        }

        return routeRepository.findAll();
    }

    public Route getRouteById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route with id " + id + " not found"));
    }

    public Route createRoute(RouteDto routeDto) {
        Route route = new Route();
        applyDtoToRoute(route, routeDto);
        return routeRepository.save(route);
    }

    public Route updateRoute(Long id, RouteDto routeDto) {
        Route existingRoute = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route with id " + id + " not found"));

        applyDtoToRoute(existingRoute, routeDto);
        return routeRepository.save(existingRoute);
    }

    public void deleteRoute(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route with id " + id + " not found"));

        routeRepository.delete(route);
    }

    public Route getOptimalRoute(String startPoint, String endPoint) {
        List<Route> matchingRoutes =
                routeRepository.findByStartPointIgnoreCaseAndEndPointIgnoreCase(startPoint, endPoint);

        if (matchingRoutes.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No routes found from " + startPoint + " to " + endPoint
            );
        }

        return matchingRoutes.stream()
                .min(Comparator.comparing(Route::getDistanceKm))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No optimal route found from " + startPoint + " to " + endPoint
                ));
    }

    private void applyDtoToRoute(Route route, RouteDto routeDto) {
        route.setStartPoint(routeDto.getStartPoint());
        route.setEndPoint(routeDto.getEndPoint());
        route.setDistanceKm(routeDto.getDistanceKm());
    }
}