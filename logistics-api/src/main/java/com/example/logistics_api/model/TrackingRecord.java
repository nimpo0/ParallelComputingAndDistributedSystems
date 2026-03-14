package com.example.logistics_api.model;

public class TrackingRecord {
    private Long id;
    private Long shipmentId;
    private String location;
    private String eventDescription;
    private String timestamp;
    private Long statusId;

    public TrackingRecord() {
    }

    public TrackingRecord(Long id, Long shipmentId, String location,
                          String eventDescription, String timestamp, Long statusId) {
        this.id = id;
        this.shipmentId = shipmentId;
        this.location = location;
        this.eventDescription = eventDescription;
        this.timestamp = timestamp;
        this.statusId = statusId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}