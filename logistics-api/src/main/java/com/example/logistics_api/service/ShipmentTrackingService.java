package com.example.logistics_api.service;

import com.example.logistics_api.dto.ShipmentResponseDto;
import com.example.logistics_api.dto.ShipmentStatusUpdateDto;
import com.example.logistics_api.exception.ResourceNotFoundException;
import com.example.logistics_api.model.Shipment;
import com.example.logistics_api.model.Status;
import com.example.logistics_api.model.TrackingRecord;
import com.example.logistics_api.repository.ShipmentRepository;
import com.example.logistics_api.repository.TrackingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShipmentTrackingService {

    private final ShipmentRepository shipmentRepository;
    private final TrackingRepository trackingRepository;
    private final StatusService statusService;
    private final ShipmentService shipmentService;

    public ShipmentTrackingService(ShipmentRepository shipmentRepository,
                                   TrackingRepository trackingRepository,
                                   StatusService statusService,
                                   ShipmentService shipmentService) {
        this.shipmentRepository = shipmentRepository;
        this.trackingRepository = trackingRepository;
        this.statusService = statusService;
        this.shipmentService = shipmentService;
    }

    @Transactional
    public ShipmentResponseDto updateShipmentStatusWithTracking(Long shipmentId,
                                                                ShipmentStatusUpdateDto dto) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment with id " + shipmentId + " not found"));

        Status status = statusService.getStatusById(dto.getStatusId());

        shipment.setStatus(status);
        shipmentRepository.save(shipment);

        TrackingRecord trackingRecord = new TrackingRecord();
        trackingRecord.setShipment(shipment);
        trackingRecord.setLocation(dto.getLocation());
        trackingRecord.setEventDescription(dto.getEventDescription());
        trackingRecord.setTimestamp(dto.getTimestamp());
        trackingRecord.setStatus(status);

        trackingRepository.save(trackingRecord);

        return shipmentService.getShipmentById(shipment.getId());
    }
}