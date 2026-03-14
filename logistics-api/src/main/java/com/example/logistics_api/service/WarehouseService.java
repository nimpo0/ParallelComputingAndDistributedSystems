package com.example.logistics_api.service;

import com.example.logistics_api.dto.WarehouseDto;
import com.example.logistics_api.exception.ResourceNotFoundException;
import com.example.logistics_api.model.Warehouse;
import com.example.logistics_api.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse with id " + id + " not found"));
    }

    public Warehouse createWarehouse(WarehouseDto warehouseDto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDto.getName());
        warehouse.setLocation(warehouseDto.getLocation());

        return warehouseRepository.save(warehouse);
    }
}