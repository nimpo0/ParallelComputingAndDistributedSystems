package com.example.logistics_api.controller;

import com.example.logistics_api.dto.RouteDto;
import com.example.logistics_api.model.Route;
import com.example.logistics_api.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable("id") Long id) {
        return routeService.getRouteById(id);
    }

    @GetMapping("/optimal")
    public Route getOptimalRoute(@RequestParam("startPoint") String startPoint,
                                 @RequestParam("endPoint") String endPoint) {
        return routeService.getOptimalRoute(startPoint, endPoint);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Route createRoute(@Valid @RequestBody RouteDto routeDto) {
        return routeService.createRoute(routeDto);
    }
}