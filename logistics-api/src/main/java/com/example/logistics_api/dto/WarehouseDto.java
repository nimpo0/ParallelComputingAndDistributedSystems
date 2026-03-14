package com.example.logistics_api.dto;

import jakarta.validation.constraints.NotBlank;

public class WarehouseDto {

    @NotBlank(message = "Warehouse name must not be blank")
    private String name;

    @NotBlank(message = "Location must not be blank")
    private String location;

    public WarehouseDto() {
    }

    public WarehouseDto(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}