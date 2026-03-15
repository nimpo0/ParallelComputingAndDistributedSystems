package com.example.logistics_api.service;

import com.example.logistics_api.dto.TrackingDto;
import com.example.logistics_api.dto.TrackingResponseDto;
import com.example.logistics_api.exception.ResourceNotFoundException;
import com.example.logistics_api.model.Shipment;
import com.example.logistics_api.model.Status;
import com.example.logistics_api.model.TrackingRecord;
import com.example.logistics_api.repository.TrackingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return trackingRepository.findByShipment_Id(shipmentId)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TrackingResponseDto createTrackingRecord(TrackingDto trackingDto) {
        Shipment shipment = shipmentService.getShipmentEntityById(trackingDto.getShipmentId());
        Status status = statusService.getStatusById(trackingDto.getStatusId());

        TrackingRecord trackingRecord = new TrackingRecord();
        trackingRecord.setShipment(shipment);
        trackingRecord.setLocation(trackingDto.getLocation());
        trackingRecord.setEventDescription(trackingDto.getEventDescription());
        trackingRecord.setTimestamp(trackingDto.getTimestamp());
        trackingRecord.setStatus(status);

        TrackingRecord savedRecord = trackingRepository.save(trackingRecord);
        return mapToResponseDto(savedRecord);
    }

    @Transactional
    public TrackingResponseDto updateTrackingRecord(Long id, TrackingDto trackingDto) {
        TrackingRecord existingRecord = trackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tracking record with id " + id + " not found"));

        Shipment shipment = shipmentService.getShipmentEntityById(trackingDto.getShipmentId());
        Status status = statusService.getStatusById(trackingDto.getStatusId());

        existingRecord.setShipment(shipment);
        existingRecord.setLocation(trackingDto.getLocation());
        existingRecord.setEventDescription(trackingDto.getEventDescription());
        existingRecord.setTimestamp(trackingDto.getTimestamp());
        existingRecord.setStatus(status);

        TrackingRecord updatedRecord = trackingRepository.save(existingRecord);
        return mapToResponseDto(updatedRecord);
    }

    @Transactional
    public void deleteTrackingRecord(Long id) {
        TrackingRecord record = trackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tracking record with id " + id + " not found"));

        trackingRepository.delete(record);
    }

    private TrackingResponseDto mapToResponseDto(TrackingRecord trackingRecord) {
        return new TrackingResponseDto(
                trackingRecord.getId(),
                trackingRecord.getShipment().getId(),
                trackingRecord.getLocation(),
                trackingRecord.getEventDescription(),
                trackingRecord.getTimestamp(),
                trackingRecord.getStatus()
        );
    }
}