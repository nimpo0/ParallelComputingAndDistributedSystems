package com.example.logistics_api.controller;

import com.example.logistics_api.dto.StatusDto;
import com.example.logistics_api.model.Status;
import com.example.logistics_api.service.StatusService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<Status> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @GetMapping("/{id}")
    public Status getStatusById(@PathVariable Long id) {
        return statusService.getStatusById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Status createStatus(@Valid @RequestBody StatusDto statusDto) {
        return statusService.createStatus(statusDto);
    }

    @PutMapping("/{id}")
    public Status updateStatus(@PathVariable Long id,
                               @Valid @RequestBody StatusDto statusDto) {
        return statusService.updateStatus(id, statusDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
    }
}