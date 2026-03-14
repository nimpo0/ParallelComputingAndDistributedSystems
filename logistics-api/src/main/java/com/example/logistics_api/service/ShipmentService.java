package com.example.logistics_api.service;

import com.example.logistics_api.dto.ShipmentDto;
import com.example.logistics_api.dto.ShipmentResponseDto;
import com.example.logistics_api.exception.ResourceNotFoundException;
import com.example.logistics_api.model.Client;
import com.example.logistics_api.model.Route;
import com.example.logistics_api.model.Shipment;
import com.example.logistics_api.model.Status;
import com.example.logistics_api.model.Transport;
import com.example.logistics_api.model.Warehouse;
import com.example.logistics_api.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ClientService clientService;
    private final RouteService routeService;
    private final TransportService transportService;
    private final StatusService statusService;
    private final WarehouseService warehouseService;

    public ShipmentService(ShipmentRepository shipmentRepository,
                           ClientService clientService,
                           RouteService routeService,
                           TransportService transportService,
                           StatusService statusService,
                           WarehouseService warehouseService) {
        this.shipmentRepository = shipmentRepository;
        this.clientService = clientService;
        this.routeService = routeService;
        this.transportService = transportService;
        this.statusService = statusService;
        this.warehouseService = warehouseService;
    }

    public List<ShipmentResponseDto> getAllShipments() {
        return shipmentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public ShipmentResponseDto getShipmentById(Long id) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with id " + id + " not found"));

        return mapToResponseDto(shipment);
    }

    public ShipmentResponseDto createShipment(ShipmentDto shipmentDto) {
        Shipment shipment = new Shipment();
        shipment.setTrackingNumber(shipmentDto.getTrackingNumber());
        shipment.setDescription(shipmentDto.getDescription());
        shipment.setWeight(shipmentDto.getWeight());
        shipment.setDestinationAddress(shipmentDto.getDestinationAddress());

        shipment.setClientId(shipmentDto.getClientId());
        shipment.setRouteId(shipmentDto.getRouteId());
        shipment.setTransportId(shipmentDto.getTransportId());
        shipment.setStatusId(shipmentDto.getStatusId());
        shipment.setWarehouseId(shipmentDto.getWarehouseId());

        Shipment savedShipment = shipmentRepository.save(shipment);
        return mapToResponseDto(savedShipment);
    }

    private ShipmentResponseDto mapToResponseDto(Shipment shipment) {
        Client client = clientService.getClientById(shipment.getClientId());
        Route route = routeService.getRouteById(shipment.getRouteId());
        Transport transport = transportService.getTransportById(shipment.getTransportId());
        Status status = statusService.getStatusById(shipment.getStatusId());
        Warehouse warehouse = warehouseService.getWarehouseById(shipment.getWarehouseId());

        return new ShipmentResponseDto(
                shipment.getId(),
                shipment.getTrackingNumber(),
                shipment.getDescription(),
                shipment.getWeight(),
                shipment.getDestinationAddress(),
                client,
                route,
                transport,
                status,
                warehouse
        );
    }

    public Shipment getShipmentEntityById(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with id " + id + " not found"));
    }
}