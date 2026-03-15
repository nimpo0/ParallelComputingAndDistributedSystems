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
        applyDtoToTransport(transport, transportDto);
        return transportRepository.save(transport);
    }

    public Transport updateTransport(Long id, TransportDto transportDto) {
        Transport existingTransport = transportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transport with id " + id + " not found"));

        applyDtoToTransport(existingTransport, transportDto);
        return transportRepository.save(existingTransport);
    }

    public void deleteTransport(Long id) {
        Transport transport = transportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transport with id " + id + " not found"));

        transportRepository.delete(transport);
    }

    private void applyDtoToTransport(Transport transport, TransportDto transportDto) {
        transport.setType(transportDto.getType());
        transport.setVehicleNumber(transportDto.getVehicleNumber());
    }
}