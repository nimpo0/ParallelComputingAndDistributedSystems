package com.example.logistics_api.dto;

import jakarta.validation.constraints.NotBlank;

public class TransportDto {

    @NotBlank(message = "Transport type must not be blank")
    private String type;

    @NotBlank(message = "Vehicle number must not be blank")
    private String vehicleNumber;

    public TransportDto() {
    }

    public TransportDto(String type, String vehicleNumber) {
        this.type = type;
        this.vehicleNumber = vehicleNumber;
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