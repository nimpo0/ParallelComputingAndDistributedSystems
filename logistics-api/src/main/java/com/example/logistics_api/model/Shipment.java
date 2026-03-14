package com.example.logistics_api.model;

public class Shipment {
    private Long id;
    private String trackingNumber;
    private String description;
    private Double weight;
    private String destinationAddress;

    private Long clientId;
    private Long routeId;
    private Long transportId;
    private Long statusId;
    private Long warehouseId;

    public Shipment() {
    }

    public Shipment(Long id, String trackingNumber, String description, Double weight,
                    String destinationAddress, Long clientId, Long routeId,
                    Long transportId, Long statusId, Long warehouseId) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.description = description;
        this.weight = weight;
        this.destinationAddress = destinationAddress;
        this.clientId = clientId;
        this.routeId = routeId;
        this.transportId = transportId;
        this.statusId = statusId;
        this.warehouseId = warehouseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}