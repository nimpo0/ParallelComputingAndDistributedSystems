package com.example.logistics_api.repository;

import com.example.logistics_api.model.Shipment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ShipmentRepository {

    private final List<Shipment> shipments = new ArrayList<>();
    private Long nextId = 1L;

    public List<Shipment> findAll() {
        return new ArrayList<>(shipments);
    }

    public Optional<Shipment> findById(Long id) {
        return shipments.stream()
                .filter(shipment -> shipment.getId().equals(id))
                .findFirst();
    }

    public Shipment save(Shipment shipment) {
        shipment.setId(nextId++);
        shipments.add(shipment);
        return shipment;
    }
}