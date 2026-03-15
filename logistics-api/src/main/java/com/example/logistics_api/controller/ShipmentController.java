package com.example.logistics_api.controller;

import com.example.logistics_api.dto.ShipmentDto;
import com.example.logistics_api.dto.ShipmentResponseDto;
import com.example.logistics_api.dto.ShipmentStatusUpdateDto;
import com.example.logistics_api.service.ShipmentService;
import com.example.logistics_api.service.ShipmentTrackingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;
    private final ShipmentTrackingService shipmentTrackingService;

    public ShipmentController(ShipmentService shipmentService, ShipmentTrackingService shipmentTrackingService) {
        this.shipmentService = shipmentService;
        this.shipmentTrackingService = shipmentTrackingService;
    }

    @PatchMapping("/{id}/status-with-tracking")
    public ShipmentResponseDto updateShipmentStatusWithTracking(
            @PathVariable Long id,
            @Valid @RequestBody ShipmentStatusUpdateDto dto
    ) {
        return shipmentTrackingService.updateShipmentStatusWithTracking(id, dto);
    }

    @GetMapping
    public Page<ShipmentResponseDto> getAllShipments(
            @RequestParam(required = false) Long statusId,
            @RequestParam(required = false) Long clientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        PageRequest pageable = PageRequest.of(page, size, sort);

        return shipmentService.getAllShipments(statusId, clientId, pageable);
    }

    @GetMapping("/{id}")
    public ShipmentResponseDto getShipmentById(@PathVariable Long id) {
        return shipmentService.getShipmentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShipmentResponseDto createShipment(@Valid @RequestBody ShipmentDto shipmentDto) {
        return shipmentService.createShipment(shipmentDto);
    }

    @PutMapping("/{id}")
    public ShipmentResponseDto updateShipment(@PathVariable Long id,
                                              @Valid @RequestBody ShipmentDto shipmentDto) {
        return shipmentService.updateShipment(id, shipmentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
    }
}