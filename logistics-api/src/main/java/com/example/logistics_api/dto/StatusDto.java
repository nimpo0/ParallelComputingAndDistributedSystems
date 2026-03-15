package com.example.logistics_api.dto;

import jakarta.validation.constraints.NotBlank;

public class StatusDto {

    @NotBlank(message = "Status name must not be blank")
    private String name;

    public StatusDto() {
    }

    public StatusDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}