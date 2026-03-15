package com.example.logistics_api.controller;

import com.example.logistics_api.dto.TransportDto;
import com.example.logistics_api.model.Transport;
import com.example.logistics_api.service.TransportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transports")
public class TransportController {

    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping
    public List<Transport> getAllTransports() {
        return transportService.getAllTransports();
    }

    @GetMapping("/{id}")
    public Transport getTransportById(@PathVariable Long id) {
        return transportService.getTransportById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transport createTransport(@Valid @RequestBody TransportDto transportDto) {
        return transportService.createTransport(transportDto);
    }

    @PutMapping("/{id}")
    public Transport updateTransport(@PathVariable Long id,
                                     @Valid @RequestBody TransportDto transportDto) {
        return transportService.updateTransport(id, transportDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransport(@PathVariable Long id) {
        transportService.deleteTransport(id);
    }
}