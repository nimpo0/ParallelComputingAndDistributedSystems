package com.example.logistics_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class RouteDto {

    @NotBlank(message = "Start point must not be blank")
    private String startPoint;

    @NotBlank(message = "End point must not be blank")
    private String endPoint;

    @Positive(message = "Distance must be greater than 0")
    private Double distanceKm;

    public RouteDto() {
    }

    public RouteDto(String startPoint, String endPoint, Double distanceKm) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distanceKm = distanceKm;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }
}