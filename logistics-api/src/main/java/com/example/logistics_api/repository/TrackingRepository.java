package com.example.logistics_api.repository;

import com.example.logistics_api.model.TrackingRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TrackingRepository {

    private final List<TrackingRecord> trackingRecords = new ArrayList<>();
    private Long nextId = 1L;

    public List<TrackingRecord> findAll() {
        return new ArrayList<>(trackingRecords);
    }

    public Optional<TrackingRecord> findById(Long id) {
        return trackingRecords.stream()
                .filter(record -> record.getId().equals(id))
                .findFirst();
    }

    public List<TrackingRecord> findByShipmentId(Long shipmentId) {
        return trackingRecords.stream()
                .filter(record -> record.getShipmentId().equals(shipmentId))
                .collect(Collectors.toList());
    }

    public TrackingRecord save(TrackingRecord trackingRecord) {
        trackingRecord.setId(nextId++);
        trackingRecords.add(trackingRecord);
        return trackingRecord;
    }
}