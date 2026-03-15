package com.example.logistics_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ShipmentStatusUpdateDto {

    @NotNull(message = "Status id must not be null")
    private Long statusId;

    @NotBlank(message = "Location must not be blank")
    private String location;

    @NotBlank(message = "Event description must not be blank")
    private String eventDescription;

    @NotBlank(message = "Timestamp must not be blank")
    private String timestamp;

    public ShipmentStatusUpdateDto() {
    }

    public ShipmentStatusUpdateDto(Long statusId, String location, String eventDescription, String timestamp) {
        this.statusId = statusId;
        this.location = location;
        this.eventDescription = eventDescription;
        this.timestamp = timestamp;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}