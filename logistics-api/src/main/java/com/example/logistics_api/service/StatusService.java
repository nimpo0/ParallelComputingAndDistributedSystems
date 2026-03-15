package com.example.logistics_api.service;

import com.example.logistics_api.dto.StatusDto;
import com.example.logistics_api.exception.ResourceNotFoundException;
import com.example.logistics_api.model.Status;
import com.example.logistics_api.repository.StatusRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @PostConstruct
    public void initDefaultStatuses() {
        if (!statusRepository.existsByName("Created")) {
            statusRepository.save(new Status("Created"));
        }
        if (!statusRepository.existsByName("In Transit")) {
            statusRepository.save(new Status("In Transit"));
        }
        if (!statusRepository.existsByName("At Warehouse")) {
            statusRepository.save(new Status("At Warehouse"));
        }
        if (!statusRepository.existsByName("Delivered")) {
            statusRepository.save(new Status("Delivered"));
        }
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Status getStatusById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status with id " + id + " not found"));
    }

    public Status createStatus(StatusDto statusDto) {
        Status status = new Status();
        status.setName(statusDto.getName());
        return statusRepository.save(status);
    }

    public Status updateStatus(Long id, StatusDto statusDto) {
        Status existingStatus = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status with id " + id + " not found"));

        existingStatus.setName(statusDto.getName());
        return statusRepository.save(existingStatus);
    }

    public void deleteStatus(Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status with id " + id + " not found"));

        statusRepository.delete(status);
    }
}