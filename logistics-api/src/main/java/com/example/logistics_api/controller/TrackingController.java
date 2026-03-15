package com.example.logistics_api.controller;

import com.example.logistics_api.dto.TrackingDto;
import com.example.logistics_api.dto.TrackingResponseDto;
import com.example.logistics_api.service.TrackingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    private final TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping
    public List<TrackingResponseDto> getAllTrackingRecords() {
        return trackingService.getAllTrackingRecords();
    }

    @GetMapping("/{id}")
    public TrackingResponseDto getTrackingRecordById(@PathVariable Long id) {
        return trackingService.getTrackingRecordById(id);
    }

    @GetMapping("/shipment/{shipmentId}")
    public List<TrackingResponseDto> getTrackingByShipmentId(@PathVariable Long shipmentId) {
        return trackingService.getTrackingByShipmentId(shipmentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackingResponseDto createTrackingRecord(@Valid @RequestBody TrackingDto trackingDto) {
        return trackingService.createTrackingRecord(trackingDto);
    }

    @PutMapping("/{id}")
    public TrackingResponseDto updateTrackingRecord(@PathVariable Long id,
                                                    @Valid @RequestBody TrackingDto trackingDto) {
        return trackingService.updateTrackingRecord(id, trackingDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackingRecord(@PathVariable Long id) {
        trackingService.deleteTrackingRecord(id);
    }
}