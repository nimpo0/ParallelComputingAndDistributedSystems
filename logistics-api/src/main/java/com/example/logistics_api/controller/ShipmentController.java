package com.example.logistics_api.controller;

import com.example.logistics_api.dto.ShipmentDto;
import com.example.logistics_api.dto.ShipmentResponseDto;
import com.example.logistics_api.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public List<ShipmentResponseDto> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/{id}")
    public ShipmentResponseDto getShipmentById(@PathVariable("id") Long id) {
        return shipmentService.getShipmentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShipmentResponseDto createShipment(@Valid @RequestBody ShipmentDto shipmentDto) {
        return shipmentService.createShipment(shipmentDto);
    }
}