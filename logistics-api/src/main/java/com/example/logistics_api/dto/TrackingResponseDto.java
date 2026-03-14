package com.example.logistics_api.dto;

import com.example.logistics_api.model.Status;

public class TrackingResponseDto {
    private Long id;
    private Long shipmentId;
    private String location;
    private String eventDescription;
    private String timestamp;
    private Status status;

    public TrackingResponseDto() {
    }

    public TrackingResponseDto(Long id, Long shipmentId, String location,
                               String eventDescription, String timestamp, Status status) {
        this.id = id;
        this.shipmentId = shipmentId;
        this.location = location;
        this.eventDescription = eventDescription;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public String getLocation() {
        return location;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}