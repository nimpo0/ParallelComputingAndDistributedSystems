package com.example.logistics_api.repository;

import com.example.logistics_api.model.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Page<Shipment> findByStatus_Id(Long statusId, Pageable pageable);

    Page<Shipment> findByClient_Id(Long clientId, Pageable pageable);

    Page<Shipment> findByStatus_IdAndClient_Id(Long statusId, Long clientId, Pageable pageable);
}