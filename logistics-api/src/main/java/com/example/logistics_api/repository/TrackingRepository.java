package com.example.logistics_api.repository;

import com.example.logistics_api.model.TrackingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<TrackingRecord, Long> {
    List<TrackingRecord> findByShipment_Id(Long shipmentId);
}