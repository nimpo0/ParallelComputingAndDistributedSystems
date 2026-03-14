package com.example.logistics_api.repository;

import com.example.logistics_api.model.Transport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TransportRepository {

    private final List<Transport> transports = new ArrayList<>();
    private Long nextId = 1L;

    public List<Transport> findAll() {
        return new ArrayList<>(transports);
    }

    public Optional<Transport> findById(Long id) {
        return transports.stream()
                .filter(transport -> transport.getId().equals(id))
                .findFirst();
    }

    public Transport save(Transport transport) {
        transport.setId(nextId++);
        transports.add(transport);
        return transport;
    }
}