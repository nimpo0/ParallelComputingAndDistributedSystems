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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<ShipmentResponseDto> getAllShipments(Long statusId, Long clientId, Pageable pageable) {
        Page<Shipment> shipments;

        if (statusId != null && clientId != null) {
            shipments = shipmentRepository.findByStatus_IdAndClient_Id(statusId, clientId, pageable);
        } else if (statusId != null) {
            shipments = shipmentRepository.findByStatus_Id(statusId, pageable);
        } else if (clientId != null) {
            shipments = shipmentRepository.findByClient_Id(clientId, pageable);
        } else {
            shipments = shipmentRepository.findAll(pageable);
        }

        return shipments.map(this::mapToResponseDto);
    }

    public ShipmentResponseDto getShipmentById(Long id) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with id " + id + " not found"));

        return mapToResponseDto(shipment);
    }

    @Transactional
    public ShipmentResponseDto createShipment(ShipmentDto shipmentDto) {
        Shipment shipment = new Shipment();
        applyDtoToShipment(shipment, shipmentDto);

        Shipment savedShipment = shipmentRepository.save(shipment);
        return mapToResponseDto(savedShipment);
    }

    @Transactional
    public ShipmentResponseDto updateShipment(Long id, ShipmentDto shipmentDto) {
        Shipment existingShipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with id " + id + " not found"));

        applyDtoToShipment(existingShipment, shipmentDto);

        Shipment updatedShipment = shipmentRepository.save(existingShipment);
        return mapToResponseDto(updatedShipment);
    }

    @Transactional
    public void deleteShipment(Long id) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with id " + id + " not found"));

        shipmentRepository.delete(shipment);
    }

    public Shipment getShipmentEntityById(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with id " + id + " not found"));
    }

    private void applyDtoToShipment(Shipment shipment, ShipmentDto shipmentDto) {
        Client client = clientService.getClientById(shipmentDto.getClientId());
        Route route = routeService.getRouteById(shipmentDto.getRouteId());
        Transport transport = transportService.getTransportById(shipmentDto.getTransportId());
        Status status = statusService.getStatusById(shipmentDto.getStatusId());
        Warehouse warehouse = warehouseService.getWarehouseById(shipmentDto.getWarehouseId());

        shipment.setTrackingNumber(shipmentDto.getTrackingNumber());
        shipment.setDescription(shipmentDto.getDescription());
        shipment.setWeight(shipmentDto.getWeight());
        shipment.setDestinationAddress(shipmentDto.getDestinationAddress());
        shipment.setClient(client);
        shipment.setRoute(route);
        shipment.setTransport(transport);
        shipment.setStatus(status);
        shipment.setWarehouse(warehouse);
    }

    private ShipmentResponseDto mapToResponseDto(Shipment shipment) {
        return new ShipmentResponseDto(
                shipment.getId(),
                shipment.getTrackingNumber(),
                shipment.getDescription(),
                shipment.getWeight(),
                shipment.getDestinationAddress(),
                shipment.getClient(),
                shipment.getRoute(),
                shipment.getTransport(),
                shipment.getStatus(),
                shipment.getWarehouse()
        );
    }
}