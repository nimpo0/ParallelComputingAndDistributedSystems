package com.example.logistics_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ShipmentDto {

    @NotBlank(message = "Tracking number must not be blank")
    private String trackingNumber;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotNull(message = "Weight must not be null")
    @Positive(message = "Weight must be greater than 0")
    private Double weight;

    @NotBlank(message = "Destination address must not be blank")
    private String destinationAddress;

    @NotNull(message = "Client id must not be null")
    private Long clientId;

    @NotNull(message = "Route id must not be null")
    private Long routeId;

    @NotNull(message = "Transport id must not be null")
    private Long transportId;

    @NotNull(message = "Status id must not be null")
    private Long statusId;

    @NotNull(message = "Warehouse id must not be null")
    private Long warehouseId;

    public ShipmentDto() {
    }

    public ShipmentDto(String trackingNumber, String description, Double weight,
                       String destinationAddress, Long clientId, Long routeId,
                       Long transportId, Long statusId, Long warehouseId) {
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