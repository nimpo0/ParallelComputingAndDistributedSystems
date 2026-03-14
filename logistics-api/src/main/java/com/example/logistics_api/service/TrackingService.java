package com.example.logistics_api.service;

import com.example.logistics_api.dto.TrackingDto;
import com.example.logistics_api.dto.TrackingResponseDto;
import com.example.logistics_api.exception.ResourceNotFoundException;
import com.example.logistics_api.model.Shipment;
import com.example.logistics_api.model.Status;
import com.example.logistics_api.model.TrackingRecord;
import com.example.logistics_api.repository.TrackingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackingService {

    private final TrackingRepository trackingRepository;
    private final ShipmentService shipmentService;
    private final StatusService statusService;

    public TrackingService(TrackingRepository trackingRepository,
                           ShipmentService shipmentService,
                           StatusService statusService) {
        this.trackingRepository = trackingRepository;
        this.shipmentService = shipmentService;
        this.statusService = statusService;
    }

    public List<TrackingResponseDto> getAllTrackingRecords() {
        return trackingRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public TrackingResponseDto getTrackingRecordById(Long id) {
        TrackingRecord record = trackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tracking record with id " + id + " not found"));

        return mapToResponseDto(record);
    }

    public List<TrackingResponseDto> getTrackingByShipmentId(Long shipmentId) {
        shipmentService.getShipmentById(shipmentId);

        return trackingRepository.findByShipmentId(shipmentId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public TrackingResponseDto createTrackingRecord(TrackingDto trackingDto) {
        Shipment shipment = shipmentService.getShipmentEntityById(trackingDto.getShipmentId());
        Status status = statusService.getStatusById(trackingDto.getStatusId());

        TrackingRecord trackingRecord = new TrackingRecord();
        trackingRecord.setShipmentId(shipment.getId());
        trackingRecord.setLocation(trackingDto.getLocation());
        trackingRecord.setEventDescription(trackingDto.getEventDescription());
        trackingRecord.setTimestamp(trackingDto.getTimestamp());
        trackingRecord.setStatusId(status.getId());

        TrackingRecord savedRecord = trackingRepository.save(trackingRecord);

        return mapToResponseDto(savedRecord);
    }

    private TrackingResponseDto mapToResponseDto(TrackingRecord trackingRecord) {
        Status status = statusService.getStatusById(trackingRecord.getStatusId());

        return new TrackingResponseDto(
                trackingRecord.getId(),
                trackingRecord.getShipmentId(),
                trackingRecord.getLocation(),
                trackingRecord.getEventDescription(),
                trackingRecord.getTimestamp(),
                status
        );
    }
}