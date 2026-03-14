package com.example.logistics_api.dto;

public class ShipmentReportDto {
    private int totalShipments;
    private double totalWeight;
    private double averageWeight;
    private int deliveredCount;
    private int inTransitCount;
    private int storedCount;

    public ShipmentReportDto() {
    }

    public ShipmentReportDto(int totalShipments, double totalWeight, double averageWeight,
                             int deliveredCount, int inTransitCount, int storedCount) {
        this.totalShipments = totalShipments;
        this.totalWeight = totalWeight;
        this.averageWeight = averageWeight;
        this.deliveredCount = deliveredCount;
        this.inTransitCount = inTransitCount;
        this.storedCount = storedCount;
    }

    public int getTotalShipments() {
        return totalShipments;
    }

    public void setTotalShipments(int totalShipments) {
        this.totalShipments = totalShipments;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(double averageWeight) {
        this.averageWeight = averageWeight;
    }

    public int getDeliveredCount() {
        return deliveredCount;
    }

    public void setDeliveredCount(int deliveredCount) {
        this.deliveredCount = deliveredCount;
    }

    public int getInTransitCount() {
        return inTransitCount;
    }

    public void setInTransitCount(int inTransitCount) {
        this.inTransitCount = inTransitCount;
    }

    public int getStoredCount() {
        return storedCount;
    }

    public void setStoredCount(int storedCount) {
        this.storedCount = storedCount;
    }
}