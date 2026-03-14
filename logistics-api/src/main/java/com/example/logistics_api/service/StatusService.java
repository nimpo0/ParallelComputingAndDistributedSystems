package com.example.logistics_api.service;

import com.example.logistics_api.exception.ResourceNotFoundException;
import com.example.logistics_api.model.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService {

    private final List<Status> statuses = new ArrayList<>();

    public StatusService() {
        statuses.add(new Status(1L, "Created"));
        statuses.add(new Status(2L, "In Transit"));
        statuses.add(new Status(3L, "At Warehouse"));
        statuses.add(new Status(4L, "Delivered"));
    }

    public List<Status> getAllStatuses() {
        return new ArrayList<>(statuses);
    }

    public Status getStatusById(Long id) {
        return statuses.stream()
                .filter(status -> status.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Status with id " + id + " not found"));
    }
}