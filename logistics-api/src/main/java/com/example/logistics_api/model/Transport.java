package com.example.logistics_api.model;

public class Transport {
    private Long id;
    private String type;
    private String vehicleNumber;

    public Transport() {
    }

    public Transport(Long id, String type, String vehicleNumber) {
        this.id = id;
        this.type = type;
        this.vehicleNumber = vehicleNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}