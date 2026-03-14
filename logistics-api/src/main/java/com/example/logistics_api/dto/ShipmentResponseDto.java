package com.example.logistics_api.dto;

import com.example.logistics_api.model.Client;
import com.example.logistics_api.model.Route;
import com.example.logistics_api.model.Status;
import com.example.logistics_api.model.Transport;
import com.example.logistics_api.model.Warehouse;

public class ShipmentResponseDto {
    private Long id;
    private String trackingNumber;
    private String description;
    private Double weight;
    private String destinationAddress;

    private Client client;
    private Route route;
    private Transport transport;
    private Status status;
    private Warehouse warehouse;

    public ShipmentResponseDto() {
    }

    public ShipmentResponseDto(Long id, String trackingNumber, String description,
                               Double weight, String destinationAddress,
                               Client client, Route route, Transport transport,
                               Status status, Warehouse warehouse) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.description = description;
        this.weight = weight;
        this.destinationAddress = destinationAddress;
        this.client = client;
        this.route = route;
        this.transport = transport;
        this.status = status;
        this.warehouse = warehouse;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}