package com.example.logistics_api.controller;

import com.example.logistics_api.dto.WarehouseDto;
import com.example.logistics_api.model.Warehouse;
import com.example.logistics_api.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable("id") Long id) {
        return warehouseService.getWarehouseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Warehouse createWarehouse(@Valid @RequestBody WarehouseDto warehouseDto) {
        return warehouseService.createWarehouse(warehouseDto);
    }
}