package com.example.logistics_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TrackingDto {

    @NotNull(message = "Shipment id must not be null")
    private Long shipmentId;

    @NotBlank(message = "Location must not be blank")
    private String location;

    @NotBlank(message = "Event description must not be blank")
    private String eventDescription;

    @NotNull(message = "Status id must not be null")
    private Long statusId;

    @NotBlank(message = "Timestamp must not be blank")
    private String timestamp;

    public TrackingDto() {
    }

    public TrackingDto(Long shipmentId, String location, String eventDescription, Long statusId, String timestamp) {
        this.shipmentId = shipmentId;
        this.location = location;
        this.eventDescription = eventDescription;
        this.statusId = statusId;
        this.timestamp = timestamp;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}