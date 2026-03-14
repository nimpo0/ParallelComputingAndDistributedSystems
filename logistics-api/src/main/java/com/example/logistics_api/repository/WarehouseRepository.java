package com.example.logistics_api.repository;

import com.example.logistics_api.model.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class WarehouseRepository {

    private final List<Warehouse> warehouses = new ArrayList<>();
    private Long nextId = 1L;

    public List<Warehouse> findAll() {
        return new ArrayList<>(warehouses);
    }

    public Optional<Warehouse> findById(Long id) {
        return warehouses.stream()
                .filter(warehouse -> warehouse.getId().equals(id))
                .findFirst();
    }

    public Warehouse save(Warehouse warehouse) {
        warehouse.setId(nextId++);
        warehouses.add(warehouse);
        return warehouse;
    }
}