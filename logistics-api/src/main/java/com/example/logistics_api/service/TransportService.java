package com.example.logistics_api.service;

import com.example.logistics_api.dto.TransportDto;
import com.example.logistics_api.exception.ResourceNotFoundException;
import com.example.logistics_api.model.Transport;
import com.example.logistics_api.repository.TransportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    private final TransportRepository transportRepository;

    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public List<Transport> getAllTransports() {
        return transportRepository.findAll();
    }

    public Transport getTransportById(Long id) {
        return transportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transport with id " + id + " not found"));
    }

    public Transport createTransport(TransportDto transportDto) {
        Transport transport = new Transport();
        transport.setType(transportDto.getType());
        transport.setVehicleNumber(transportDto.getVehicleNumber());

        return transportRepository.save(transport);
    }
}